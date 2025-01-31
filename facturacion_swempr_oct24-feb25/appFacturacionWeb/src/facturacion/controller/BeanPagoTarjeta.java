package facturacion.controller;

import java.math.BigDecimal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
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

	private static final long serialVersionUID = 1L;
	private String apiUrl = "https://127.0.0.1:3000";
	private String apiKey = "token123";

	@Inject
	private BeanSupervisor beanSupervisor;

	private String numeroTarjeta;
	private Integer mesVencimiento;
	private Integer anioVencimiento;
	private String cvv;
	private String titular;
	private PedidoCab pedidoCabTmp;

	private String metodoPago = "TARJETA";
	private String mensajeRespuesta; // Propiedad para almacenar la respuesta de la API
	private String tipoMensaje; // Para mostrar mensajes de error o éxito
	
	private ConsumeAPI api;

	public BeanPagoTarjeta() {
		// Constructor vacío
	}

	@PostConstruct
	public void inicializar() {
		api = new ConsumeAPI(apiUrl, apiKey);
	}
	
	public void actualizarEstadoTransaccion(PedidoCab pedidoCab) {
	    this.actionCargarPedido(pedidoCab);
	    String orderId = pedidoCabTmp.getNumeroPedido().toString();

	    try {
	        JsonObject jsonObject = api.getData("/transaction/status/" + orderId);

	        String status = jsonObject.getString("status", "No se encontró el estado.");
	        String numeroTarjeta = jsonObject.getString("cardNumber", "No se encontró el tarjeta.");
	        System.out.println("Status: " + status);

	        if(status.equals("pending")) {
	            JSFUtil.crearMensajeWARN("Transacción pendiente!");
	            return;
	        }

	        if(status.equals("failure")) {
	            JSFUtil.crearMensajeERROR("Transacción fallida!");
	            return;
	        }

	        if(status.equals("approved")) {
	            this.actualizarCreditoAPI(numeroTarjeta, pedidoCabTmp.getSubtotal());
	            beanSupervisor.actionDespacharPedido(pedidoCabTmp);
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
	                    "Resultado de la transaccion: " + status, "Transacción aprobada"));
	        }
	    } catch (Exception e) {
	    	JSFUtil.crearMensajeERROR("Error: "+e.getMessage());
	        e.printStackTrace();
	        
	    }
	}


	private void actualizarCreditoAPI(String numeroTarjeta, BigDecimal monto) {
		try {
			JsonObject jsonPayload = Json.createObjectBuilder().add("cardNumber", numeroTarjeta)
					.add("amount", pedidoCabTmp.getSubtotal()).build();

			
			JsonObject jsonObject = this.api.postData(this.apiUrl+"/card/consume", jsonPayload);			

			// Extraer los valores "message" y "status"
			String message = jsonObject.getString("card_id", "No se encontró el mensaje.");
			String status = jsonObject.getString("owner", "No se encontró el estado.");

			// Mostrar en consola los valores extraídos
			System.out.println("Message: " + message);
			System.out.println("Status: " + status);

		} catch (Exception e) {
			e.printStackTrace();
			mensajeRespuesta = "Error al procesar el pago." + e.getMessage();
			addMessage(FacesMessage.SEVERITY_ERROR, "Error", mensajeRespuesta);
		}
	}

	public void PagoTarjetaConAPI() {
		System.out.println("Entrando al método PagoTarjetaConAPI()...");

		try {
			if (pedidoCabTmp == null) {
				mensajeRespuesta = "Error: No hay pedido seleccionado.";
				addMessage(FacesMessage.SEVERITY_ERROR, "Error", mensajeRespuesta);
				return;
			}

			JsonObject jsonPayload = Json.createObjectBuilder().add("cardNumber", numeroTarjeta)
					.add("expMonth", mesVencimiento).add("expYear", anioVencimiento).add("owner", titular)
					.add("amount", pedidoCabTmp.getSubtotal())
					.add("order_id", pedidoCabTmp.getNumeroPedido().toString()).add("cvv", cvv).build();

			JsonObject jsonObject = api.postData("/transaction", jsonPayload);

			String message = jsonObject.getString("message", "No se encontró el mensaje.");
			String status = jsonObject.getString("status", "No se encontró el estado.");

			System.out.println("Message: " + message);
			System.out.println("Status: " + status);

			if (status.equals("approved") || status.equals("pending")) {
				tipoMensaje = status;
				if (status.equals("approved")) {
					beanSupervisor.actionDespacharPedido(pedidoCabTmp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Resultado de la transaccion: " + status, message));
					FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
							.add("PF('dlgPago').hide();");
				} else {
//					Estado pendiente
					beanSupervisor.actionCambiarEstadoApendiente(pedidoCabTmp);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Resultado de la transaccion: " + status, message));
					FacesContext.getCurrentInstance().getPartialViewContext().getEvalScripts()
							.add("PF('dlgPago').hide();");
				}
			} else {
				tipoMensaje = "error";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Resultado de la transaccion: " + status, message));
			}

		} catch (Exception e) {
			e.printStackTrace();
			mensajeRespuesta = "Error al procesar el pago." + e.getMessage();
			addMessage(FacesMessage.SEVERITY_ERROR, "Error", mensajeRespuesta);
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
