package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the lib_prestamo database table.
 * 
 */
@Entity
@Table(name="lib_prestamo")
@NamedQuery(name="LibPrestamo.findAll", query="SELECT l FROM LibPrestamo l")
public class LibPrestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_devolucion")
	private Date fechaDevolucion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_prestamo", nullable=false)
	private Date fechaPrestamo;

	//bi-directional many-to-one association to LibLibro
	@ManyToOne
	@JoinColumn(name="id_libro")
	private LibLibro libLibro;

	//bi-directional many-to-one association to LibUsuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private LibUsuario libUsuario;

	public LibPrestamo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaDevolucion() {
		return this.fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Date getFechaPrestamo() {
		return this.fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LibLibro getLibLibro() {
		return this.libLibro;
	}

	public void setLibLibro(LibLibro libLibro) {
		this.libLibro = libLibro;
	}

	public LibUsuario getLibUsuario() {
		return this.libUsuario;
	}

	public void setLibUsuario(LibUsuario libUsuario) {
		this.libUsuario = libUsuario;
	}

}