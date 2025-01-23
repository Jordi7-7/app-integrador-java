package minimarketdemo.api.rest.notificaciones;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@RequestScoped
@Path("notificaciones")
@Produces("application/json")
public class Notificaciones {

	@GET
    @Path("enviar")
    public String sendNotification() {
        NotificacionesService.sendNotification("API consumida exitosamente.");
        return "Notificación enviada automáticamente.";
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