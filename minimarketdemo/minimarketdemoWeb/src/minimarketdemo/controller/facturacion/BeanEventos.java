package minimarketdemo.controller.facturacion;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemoFact.model.entities.VwEvento;
import minimarketdemoFact.model.evento.manager.ManagerEvento;

@Named
@SessionScoped
public class BeanEventos implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerEvento mEvento;
	private List<VwEvento> listaEventos;
	
	public BeanEventos() {
		// TODO Auto-generated constructor stub
	}
	
	public String actionCargarMenuEventos() {
		listaEventos = mEvento.findAllEvento();
		return "eventos?faces-redirect=true";
	}

	public List<VwEvento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<VwEvento> listaEventos) {
		this.listaEventos = listaEventos;
	}
	
	
	
}
