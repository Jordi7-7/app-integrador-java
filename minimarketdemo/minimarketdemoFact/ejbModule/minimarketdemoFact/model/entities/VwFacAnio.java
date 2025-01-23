package minimarketdemoFact.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

import javax.persistence.Id;


/**
 * The persistent class for the vw_fac_anios database table.
 * 
 */
@Entity
@Table(name="vw_fac_anios")
@NamedQuery(name="VwFacAnio.findAll", query="SELECT v FROM VwFacAnio v")
public class VwFacAnio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private BigDecimal anio;

	@Column(name="fac_emitidas")
	private Long facEmitidas;

	@Column(name="valores_totales")
	private BigDecimal valoresTotales;

	public VwFacAnio() {
	}

	public BigDecimal getAnio() {
		return this.anio;
	}

	public void setAnio(BigDecimal anio) {
		this.anio = anio;
	}

	public Long getFacEmitidas() {
		return this.facEmitidas;
	}

	public void setFacEmitidas(Long facEmitidas) {
		this.facEmitidas = facEmitidas;
	}

	public BigDecimal getValoresTotales() {
		return this.valoresTotales;
	}

	public void setValoresTotales(BigDecimal valoresTotales) {
		this.valoresTotales = valoresTotales;
	}

}