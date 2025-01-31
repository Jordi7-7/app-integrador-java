package facturacion.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URI;
import java.net.URL;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.net.ssl.HttpsURLConnection;

public class ConsumeAPI {
    private static final Logger logger = Logger.getLogger(ConsumeAPI.class.getName());
    private final String apiUrl;
    private final String apiKey;

    public ConsumeAPI(String apiUrl, String apiKey) {
        if (apiUrl == null || apiUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("La URL de la API no puede ser nula o vacía.");
        }
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("La API key no puede ser nula o vacía.");
        }
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
    }

    public JsonObject getData(String endpoint) throws Exception {
        HttpsURLConnection conn = createConnection(endpoint, "GET");
        try {
            int responseCode = conn.getResponseCode();
            logger.info("Código de respuesta GET: " + responseCode);

            if (responseCode >= 400) {
            	String errorResponse = readErrorResponse(conn);
            	JsonObject jsonError = Json.createReader(new StringReader(errorResponse)).readObject();
            	String message = jsonError.getString("message", "No se encontró el estado.");
                throw new ManageException("Error en la solicitud: " + message, responseCode);
            }

            String response = readResponse(conn);
            return Json.createReader(new StringReader(response)).readObject();
        } finally {
            conn.disconnect();
        }
    }

    public JsonObject postData(String endpoint, JsonObject jsonPayload) throws Exception {
        HttpsURLConnection conn = createConnection(endpoint, "POST");
        conn.setDoOutput(true);
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonPayload.toString().getBytes("UTF-8"));
            os.flush();
        }

        int responseCode = conn.getResponseCode();
        logger.info("Código de respuesta POST: " + responseCode);

        if (responseCode >= 400) {
        	String errorResponse = readErrorResponse(conn);
        	JsonObject jsonError = Json.createReader(new StringReader(errorResponse)).readObject();
        	String message = jsonError.getString("message", "No se encontró el estado.");
            throw new ManageException("Error en la solicitud - " + message, responseCode);
        }

        String response = readResponse(conn);
        return Json.createReader(new StringReader(response)).readObject();
    }

    private HttpsURLConnection createConnection(String endpoint, String method) throws Exception {
        URL url = new URI(this.apiUrl + endpoint).toURL();
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", this.apiKey);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        return conn;
    }

    private String readResponse(HttpsURLConnection conn) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    private String readErrorResponse(HttpsURLConnection conn) throws IOException {
        StringBuilder errorResponse = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getErrorStream(), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                errorResponse.append(line);
            }
        }
        return errorResponse.toString();
    }
}