package minimarketdemo.controller.libreria;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.controller.JSFUtil;
import minimarketdemo.model.core.entities.LibAutor;
import minimarketdemo.model.core.entities.LibLibro;
import minimarketdemo.model.libreria.managers.ManagerLibreria;

@Named
@SessionScoped
public class BeanLibreriaLibros implements Serializable {

	@EJB
	private ManagerLibreria mLibreria;
	private List<LibAutor> listaAutores;
	private List<LibLibro> listaLibros;
	private LibLibro nuevoLibro;
	private LibLibro edicionLibro;
	private int idLibAutor;
	private int edicionIdLibAutor;

	
	public BeanLibreriaLibros() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanLibLibros inicializado...");
		nuevoLibro=new LibLibro();
	}
	
	public String actionCargaMenuLibros() {
		listaAutores = mLibreria.findAllLibAutor();
		listaLibros = mLibreria.findAllLibLibro();
		return "libros?faces-redirect=true";
	}
	
	public void actionListenerInsertarLibro() {
		try {
			mLibreria.insertarLibLibro(nuevoLibro, idLibAutor);
			listaLibros=mLibreria.findAllLibLibro();
			JSFUtil.crearMensajeINFO("Libro creado.");
			nuevoLibro=new LibLibro();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarLibro(LibLibro libro) {
		edicionLibro=libro;
		edicionIdLibAutor=libro.getLibAutor().getId();
	}
	
	public void actionListenerGuardarEdicionLibro() {
		try {
			mLibreria.actualizarLibLibro(edicionLibro, edicionIdLibAutor);
			JSFUtil.crearMensajeINFO("Libro editado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarLibro(int idLibLibro) {
		try {
			mLibreria.eliminarLibro(idLibLibro);
			listaLibros=mLibreria.findAllLibLibro();
			JSFUtil.crearMensajeINFO("Libro eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public int getIdLibAutor() {
		return idLibAutor;
	}

	public void setIdLibAutor(int idLibAutor) {
		this.idLibAutor = idLibAutor;
	}

	public int getEdicionIdLibAutor() {
		return edicionIdLibAutor;
	}

	public void setEdicionIdLibAutor(int edicionIdLibAutor) {
		this.edicionIdLibAutor = edicionIdLibAutor;
	}

	public List<LibAutor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<LibAutor> listaAutores) {
		this.listaAutores = listaAutores;
	}

	public List<LibLibro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(List<LibLibro> listaLibros) {
		this.listaLibros = listaLibros;
	}

	public LibLibro getNuevoLibro() {
		return nuevoLibro;
	}

	public void setNuevoLibro(LibLibro nuevoLibro) {
		this.nuevoLibro = nuevoLibro;
	}

	public LibLibro getEdicionLibro() {
		return edicionLibro;
	}

	public void setEdicionLibro(LibLibro edicionLibro) {
		this.edicionLibro = edicionLibro;
	}

	
	
}
