package facturacion.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;

import facturacion.model.dao.entities.PedidoCab;

@Named
@SessionScoped
public class BeanPagoTarjeta implements Serializable {

    private String numeroTarjeta;
    private String mesVencimiento;
    private String anioVencimiento;
    private String cvv;
    private String titular;
    private PedidoCab pedidoCabTmp;

    private String apiKey = "aqui va la api";
    
    private String metodoPago = "TARJETA";

    public BeanPagoTarjeta() {
        // Constructor vacío
    }

    // Método privado para validar el pago con la API
    public String PagoTarjetaConAPI() {
        HttpURLConnection conn = null;
        try {
        	
            // Crear el JSON utilizando javax.json
            JsonObject jsonPayload = Json.createObjectBuilder()
                    .add("numeroTarjeta", numeroTarjeta)
                    .add("mesVencimiento", mesVencimiento)
                    .add("anioVencimiento", anioVencimiento)
                    .add("titular", titular)
                    .add("monto", pedidoCabTmp.getSubtotal().toString())
                    .add("idPedido", pedidoCabTmp.getNumeroPedido().toString())
                    .add("cvv", cvv)
                    .add("apiKey", apiKey)
                    .build();
            
            System.out.println(jsonPayload);
        	
            String apiUrl = "https://api.ejemplo.com/validarPago";
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();

            // Configuración de la conexión
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Enviar la solicitud
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonPayload.toString().getBytes("UTF-8"));
                os.flush();
            }

            // Leer la respuesta
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    // Interpretar la respuesta
                    return response.toString();
                }
            } else {
                throw new RuntimeException("Error HTTP: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al consumir la API", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    
	public String actionCargarPedido(PedidoCab pedidoCab){
		try {
			//capturamos el valor enviado desde el DataTable:
			this.pedidoCabTmp=pedidoCab;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

    // Getters y setters
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getMesVencimiento() {
        return mesVencimiento;
    }

    public void setMesVencimiento(String mesVencimiento) {
        this.mesVencimiento = mesVencimiento;
    }

    public String getAnioVencimiento() {
        return anioVencimiento;
    }

    public void setAnioVencimiento(String anioVencimiento) {
        this.anioVencimiento = anioVencimiento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public PedidoCab getPedidoCabTmp() {
        return pedidoCabTmp;
    }

    public void setPedidoCabTmp(PedidoCab pedidoCabTmp) {
        this.pedidoCabTmp = pedidoCabTmp;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
