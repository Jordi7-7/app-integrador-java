package facturacion.controller;

public class ManageException extends Exception {
	private static final long serialVersionUID = 1L;
	private int statusCode = 0;

    public ManageException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
