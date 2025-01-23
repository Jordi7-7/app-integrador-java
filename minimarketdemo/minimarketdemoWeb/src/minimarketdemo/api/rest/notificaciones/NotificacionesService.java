package minimarketdemo.api.rest.notificaciones;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificacionesService {
	private static boolean flag = false;
	private static String message;

	public static void sendNotification(String msg) {
		message = msg;
		flag = true;
		System.out.println("Notificación enviada: " + message);
	}

	public static boolean isNotified() {
		return flag;
	}

	public static String getMessage() {
		return message;
	}

	public static void resetNotification() {
		flag = false;
		message = null;
	}
}