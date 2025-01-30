package facturacion.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;

import facturacion.model.dao.entities.PedidoCab;

@Named
@SessionScoped
public class BeanPagoTarjeta implements Serializable {

	@Inject
	private BeanSupervisor beanSupervisor;
	
	private String numeroTarjeta;
	private Integer mesVencimiento;
	private Integer anioVencimiento;
	private String cvv;
	private String titular;
	private PedidoCab pedidoCabTmp;

	private String apiKey = "token123";

	private String metodoPago = "TARJETA";
	private String mensajeRespuesta; // Propiedad para almacenar la respuesta de la API
	private String tipoMensaje; // Para mostrar mensajes de error o éxito

	public BeanPagoTarjeta() {
		// Constructor vacío
	}

	public void PagoTarjetaConAPI() {
		System.out.println("Entrando al método PagoTarjetaConAPI()...");

		HttpURLConnection conn = null;
		try {
			if (pedidoCabTmp == null) {
				mensajeRespuesta = "Error: No hay pedido seleccionado.";
				addMessage(FacesMessage.SEVERITY_ERROR, "Error", mensajeRespuesta);
				return;
			}

			// Construir JSON del cuerpo de la solicitud
			JsonObject jsonPayload = Json.createObjectBuilder().add("cardNumber", numeroTarjeta)
					.add("expMonth", mesVencimiento).add("expYear", anioVencimiento).add("owner", titular)
					.add("amount", pedidoCabTmp.getSubtotal())
					.add("order_id", pedidoCabTmp.getNumeroPedido().toString()).add("cvv", cvv).build();

			System.out.println("JSON Enviado: " + jsonPayload);

			// URL del servicio de pago
			String apiUrl = "http://127.0.0.1:3000/transaction";
			URL url = new URL(apiUrl);
			conn = (HttpURLConnection) url.openConnection();

			// Configurar conexión HTTP
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", apiKey);
			conn.setDoOutput(true);

			// Enviar solicitud
			try (OutputStream os = conn.getOutputStream()) {
				os.write(jsonPayload.toString().getBytes("UTF-8"));
				os.flush();
			}

			// Obtener código de respuesta HTTP
			int responseCode = conn.getResponseCode();
			System.out.println("Código de respuesta: " + responseCode);

			// Leer la respuesta de la API
			StringBuilder response = new StringBuilder();
			try (BufferedReader br = new BufferedReader(
					new InputStreamReader(responseCode < 400 ? conn.getInputStream() : conn.getErrorStream()))) {
				String line;
				while ((line = br.readLine()) != null) {
					response.append(line);
				}
			}

			// Guardar la respuesta de la API
			mensajeRespuesta = response.toString();
			System.out.println("Respuesta de la API: " + mensajeRespuesta);

			// Parsear el JSON de la respuesta
			JsonObject jsonObject = Json.createReader(new StringReader(mensajeRespuesta)).readObject();

			// Extraer los valores "message" y "status"
			String message = jsonObject.getString("message", "No se encontró el mensaje.");
			String status = jsonObject.getString("status", "No se encontró el estado.");

			// Mostrar en consola los valores extraídos
			System.out.println("Message: " + message);
			System.out.println("Status: " + status);

			if (status.equals("approved")) {
				System.out.println("dentre a approved: " + status);
				beanSupervisor.actionDespacharPedido(pedidoCabTmp);

				tipoMensaje = "approved";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Resultado de la transaccion: " + status, message));
		        FacesContext.getCurrentInstance()
	            .getPartialViewContext()
	            .getEvalScripts()
	            .add("PF('dlgPago').hide();");
			} else {
				tipoMensaje = "error";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Resultado de la transaccion: " + status, message));
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensajeRespuesta = "Error al procesar el pago.";
			addMessage(FacesMessage.SEVERITY_ERROR, "Error", mensajeRespuesta);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public String actionCargarPedido(PedidoCab pedidoCab) {
		try {
			// Capturamos el valor enviado desde el DataTable:
			this.pedidoCabTmp = pedidoCab;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	// Método para agregar mensajes a la interfaz JSF
	private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}

	// Getters y setters
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	// Getters y setters
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Integer getMesVencimiento() {
		return mesVencimiento;
	}

	public void setMesVencimiento(Integer mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}

	public Integer getAnioVencimiento() {
		return anioVencimiento;
	}

	public void setAnioVencimiento(Integer anioVencimiento) {
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

	public String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}
}
