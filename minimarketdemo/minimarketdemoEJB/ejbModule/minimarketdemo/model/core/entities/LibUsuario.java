package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lib_usuario database table.
 * 
 */
@Entity
@Table(name="lib_usuario")
@NamedQuery(name="LibUsuario.findAll", query="SELECT l FROM LibUsuario l")
public class LibUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=255)
	private String correo;

	@Column(nullable=false, length=255)
	private String nombre;

	//bi-directional many-to-one association to LibPrestamo
	@OneToMany(mappedBy="libUsuario")
	private List<LibPrestamo> libPrestamos;

	public LibUsuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<LibPrestamo> getLibPrestamos() {
		return this.libPrestamos;
	}

	public void setLibPrestamos(List<LibPrestamo> libPrestamos) {
		this.libPrestamos = libPrestamos;
	}

	public LibPrestamo addLibPrestamo(LibPrestamo libPrestamo) {
		getLibPrestamos().add(libPrestamo);
		libPrestamo.setLibUsuario(this);

		return libPrestamo;
	}

	public LibPrestamo removeLibPrestamo(LibPrestamo libPrestamo) {
		getLibPrestamos().remove(libPrestamo);
		libPrestamo.setLibUsuario(null);

		return libPrestamo;
	}

}