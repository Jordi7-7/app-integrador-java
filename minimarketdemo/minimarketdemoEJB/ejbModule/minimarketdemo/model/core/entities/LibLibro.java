package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lib_libro database table.
 * 
 */
@Entity
@Table(name="lib_libro")
@NamedQuery(name="LibLibro.findAll", query="SELECT l FROM LibLibro l")
public class LibLibro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="anio_publicacion")
	private Integer anioPublicacion;

	@Column(length=100)
	private String genero;

	@Column(nullable=false, length=255)
	private String titulo;

	//bi-directional many-to-one association to LibAutor
	@ManyToOne
	@JoinColumn(name="id_autor")
	private LibAutor libAutor;

	//bi-directional many-to-one association to LibPrestamo
	@OneToMany(mappedBy="libLibro")
	private List<LibPrestamo> libPrestamos;

	public LibLibro() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnioPublicacion() {
		return this.anioPublicacion;
	}

	public void setAnioPublicacion(Integer anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LibAutor getLibAutor() {
		return this.libAutor;
	}

	public void setLibAutor(LibAutor libAutor) {
		this.libAutor = libAutor;
	}

	public List<LibPrestamo> getLibPrestamos() {
		return this.libPrestamos;
	}

	public void setLibPrestamos(List<LibPrestamo> libPrestamos) {
		this.libPrestamos = libPrestamos;
	}

	public LibPrestamo addLibPrestamo(LibPrestamo libPrestamo) {
		getLibPrestamos().add(libPrestamo);
		libPrestamo.setLibLibro(this);

		return libPrestamo;
	}

	public LibPrestamo removeLibPrestamo(LibPrestamo libPrestamo) {
		getLibPrestamos().remove(libPrestamo);
		libPrestamo.setLibLibro(null);

		return libPrestamo;
	}

}