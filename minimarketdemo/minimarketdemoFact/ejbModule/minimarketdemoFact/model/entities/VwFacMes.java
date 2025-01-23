package minimarketdemoFact.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


/**
 * The persistent class for the vw_fac_meses database table.
 * 
 */
@Entity
@Table(name="vw_fac_meses")
@NamedQuery(name="VwFacMes.findAll", query="SELECT v FROM VwFacMes v")
public class VwFacMes implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal anio;

	@Column(name="fac_emitidas")
	private Long facEmitidas;

	@Id
	private Long id;

	private int mes;

	@Column(name="valores_totales")
	private BigDecimal valoresTotales;

	public VwFacMes() {
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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMes() {
		return this.mes;
	}
	
	public String getMesString() {
		
	    List<String> meses = Arrays.asList(
	            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
	            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
	        );
	    
	    return meses.get(this.mes-1);
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public BigDecimal getValoresTotales() {
		return this.valoresTotales;
	}

	public void setValoresTotales(BigDecimal valoresTotales) {
		this.valoresTotales = valoresTotales;
	}

}