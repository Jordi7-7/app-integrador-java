package minimarketdemo.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.api.rest.notificaciones.NotificacionesService;
import minimarketdemo.controller.JSFUtil;

@Named
@SessionScoped
public class BeanNotificaciones implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> listTmpMgs;
	private boolean hasNotification;
	
	public BeanNotificaciones() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar() {
		listTmpMgs = new ArrayList<String>();
	}
	
	public void mostrarNotificacionEnJSF() {
		if (NotificacionesService.isNotified()) {
			JSFUtil.crearMensajeINFO("¡Se ha ejecutado exitosamente una transacción!");
			this.listTmpMgs.add(NotificacionesService.getMessage());
			hasNotification = true;
			NotificacionesService.resetNotification();
		}
	}

	public List<String> getListTmpMgs() {
		return listTmpMgs;
	}
	
	public void limpiarNotificaciones() {
	    hasNotification = false;
	}

	public boolean hasNotification() {
		return hasNotification;
	}

public int getNotificationCount() {
	return this.listTmpMgs.size();
}
}
