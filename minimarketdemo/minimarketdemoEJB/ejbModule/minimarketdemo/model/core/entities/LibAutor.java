package minimarketdemo.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lib_autor database table.
 * 
 */
@Entity
@Table(name="lib_autor")
@NamedQuery(name="LibAutor.findAll", query="SELECT l FROM LibAutor l")
public class LibAutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=100)
	private String nacionalidad;

	@Column(nullable=false, length=255)
	private String nombre;

	//bi-directional many-to-one association to LibLibro
	@OneToMany(mappedBy="libAutor")
	private List<LibLibro> libLibros;

	public LibAutor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<LibLibro> getLibLibros() {
		return this.libLibros;
	}

	public void setLibLibros(List<LibLibro> libLibros) {
		this.libLibros = libLibros;
	}

	public LibLibro addLibLibro(LibLibro libLibro) {
		getLibLibros().add(libLibro);
		libLibro.setLibAutor(this);

		return libLibro;
	}

	public LibLibro removeLibLibro(LibLibro libLibro) {
		getLibLibros().remove(libLibro);
		libLibro.setLibAutor(null);

		return libLibro;
	}

}