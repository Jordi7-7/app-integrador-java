package minimarketdemo.controller.facturacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.charts.bar.BarChartModel;

import minimarketdemo.utils.BarCharts;
import minimarketdemoFact.model.entities.VwFacAnio;
import minimarketdemoFact.model.entities.VwFacMes;
import minimarketdemoFact.model.entities.VwPedido;
import minimarketdemoFact.model.factura.manager.ManagerFactura;
import minimarketdemoFact.model.pedido.manager.ManagerPedido;

@Named
@SessionScoped
public class BeanFacturas implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerFactura mFactura;
	
	@EJB
	private ManagerPedido mPedido;
	
	
	private List<VwFacAnio> listaFacturaAnio;
	private List<VwFacMes> listaFacturaMes;
	private List<VwPedido> listaPedido;
	
	private BarChartModel barModel;
	

	public BeanFacturas() {
		// TODO Auto-generated constructor stub
	}
	
	public String actionCargarMenuFacturas() {
		listaFacturaAnio = mFactura.findAllFacturabyAnio();
		listaFacturaMes = mFactura.findAllFacturabyMes();
		listaPedido = mPedido.findAllPedido();
		
		barModel = BarCharts.crearChart(this.listaFacturaMes, VwFacMes::getValoresTotales,VwFacMes::getMesString);
		
		
		return "facturas?faces-redirect=true";
	}

	
	public List<VwFacAnio> getListaFacturaAnio() {
		return listaFacturaAnio;
	}

	public void setListaFacturaAnio(List<VwFacAnio> listaFacturaAnio) {
		this.listaFacturaAnio = listaFacturaAnio;
	}

	public List<VwFacMes> getListaFacturaMes() {
		return listaFacturaMes;
	}

	public void setListaFacturaMes(List<VwFacMes> listaFacturaMes) {
		this.listaFacturaMes = listaFacturaMes;
	}

	public List<VwPedido> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<VwPedido> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}


	
	

}
