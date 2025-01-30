package minimarketdemo.controller.pago;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.core.entities.Pago;
import minimarketdemo.model.pagos.managers.ManagerPagos;

@Named
@SessionScoped
public class BeanPago implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerPagos manager;
	private List<Pago> listaPagos; 
	
	public BeanPago() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializar(){
		listaPagos = manager.findDTOPagos();
	}
	
	public String actionMenuTransacciones() {
		return "transacciones";
	}

	public List<Pago> getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(List<Pago> listaPagos) {
		this.listaPagos = listaPagos;
	}
}
