package minimarketdemo.api.rest.notificaciones;

import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import minimarketdemo.model.core.entities.Pago;
import minimarketdemo.model.pagos.managers.ManagerPagos;

@RequestScoped
@Path("noti")
@Produces("application/json")
public class Notificaciones {

	@EJB
	private ManagerPagos manager;

	@GET
	@Path("enviar")
	public String sendNotification() {
		NotificacionesService.sendNotification("API consumida exitosamente.");
		return "Notificación enviada automáticamente.";
	}

	@POST
	@Path("guardar/transaccion")
	public Response guardarTransaccion(Pago data) {
		try {
			manager.insertarPago(data);
			Map<String, Object> response = Map.of("resultado", "Transacción Exitosa.", "data", data);
			NotificacionesService.sendNotification("ID Transacción:"+data.getTransaccionId()+" Monto:"+data.getMonto());
			return Response.ok(response).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity("Error insertar transacción : " + e.getMessage())
					.build();
		}
	}

	@GET
	@Path("status")
	public String getNotificationStatus() {
		if (NotificacionesService.isNotified()) {
			return "Última notificación: " + NotificacionesService.getMessage();
		}
		return "No se han enviado notificaciones.";
	}
}