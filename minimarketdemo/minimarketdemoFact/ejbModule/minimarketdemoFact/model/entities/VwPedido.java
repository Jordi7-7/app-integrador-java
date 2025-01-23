package minimarketdemoFact.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import javax.persistence.Id;


/**
 * The persistent class for the vw_pedidos database table.
 * 
 */
@Entity
@Table(name="vw_pedidos")
@NamedQuery(name="VwPedido.findAll", query="SELECT v FROM VwPedido v")
public class VwPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal anio;

	@Id
	private Long id;

	private BigDecimal mes;

	@Column(name="pedidos_despachados")
	private Long pedidosDespachados;

	@Column(name="pedidos_pendientes")
	private Long pedidosPendientes;

	@Column(name="valores_totales")
	private BigDecimal valoresTotales;

	public VwPedido() {
	}

	public BigDecimal getAnio() {
		return this.anio;
	}

	public void setAnio(BigDecimal anio) {
		this.anio = anio;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMes() {
		return this.mes;
	}

	public void setMes(BigDecimal mes) {
		this.mes = mes;
	}

	public Long getPedidosDespachados() {
		return this.pedidosDespachados;
	}

	public void setPedidosDespachados(Long pedidosDespachados) {
		this.pedidosDespachados = pedidosDespachados;
	}

	public Long getPedidosPendientes() {
		return this.pedidosPendientes;
	}

	public void setPedidosPendientes(Long pedidosPendientes) {
		this.pedidosPendientes = pedidosPendientes;
	}

	public BigDecimal getValoresTotales() {
		return this.valoresTotales;
	}

	public void setValoresTotales(BigDecimal valoresTotales) {
		this.valoresTotales = valoresTotales;
	}

}