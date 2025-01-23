package minimarketdemo.utils;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.api.rest.notificaciones.NotificacionesService;
import minimarketdemo.controller.JSFUtil;

@Named
@SessionScoped
public class BeanNotificaciones implements Serializable {

	private static final long serialVersionUID = 1L;

	public BeanNotificaciones() {
		// TODO Auto-generated constructor stub
	}
	
	public void mostrarNotificacionEnJSF() {
		if (NotificacionesService.isNotified()) {
			JSFUtil.crearMensajeINFO("API WORKS!");
			NotificacionesService.resetNotification();
		}
	}
}
