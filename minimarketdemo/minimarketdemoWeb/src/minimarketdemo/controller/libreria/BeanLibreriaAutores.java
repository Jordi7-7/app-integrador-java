package minimarketdemo.controller.libreria;

import java.io.Serializable;
import java.util.List;

import minimarketdemo.controller.JSFUtil;
import  minimarketdemo.model.core.entities.LibAutor;
import  minimarketdemo.model.core.entities.LibLibro;
import minimarketdemo.model.core.entities.SegModulo;
import minimarketdemo.model.core.entities.SegPerfil;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import minimarketdemo.model.libreria.managers.ManagerLibreria;

@Named
@SessionScoped
public class BeanLibreriaAutores implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerLibreria mLibreria;
	private List<LibAutor> listaAutores;
	private List<LibLibro> listaLibros;
	private LibAutor nuevoAutor;
	private LibAutor edicionAutor;
	
	
	public BeanLibreriaAutores() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanLibAutores inicializado...");
		nuevoAutor=new LibAutor();
	}
	
	public String actionCargarMenuAutores() {
		listaAutores = mLibreria.findAllLibAutor();
		return "autores?faces-redirect=true";
	}

	public void actionListenerInsertarAutor() {
		try {
			mLibreria.insertarLibAutor(nuevoAutor);
			listaAutores=mLibreria.findAllLibAutor();
			JSFUtil.crearMensajeINFO("Autor creado.");
			nuevoAutor=new LibAutor();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public void actionListenerCargarAutor(LibAutor autor) {
		edicionAutor=autor;
	}
	
	public void actionListenerGuardarEdicionAutor() {
		try {
			mLibreria.actualizarAutor(edicionAutor);
			JSFUtil.crearMensajeINFO("Autor editado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarAutor(int idLibAutor) {
		try {
			mLibreria.eliminarAutor(idLibAutor);
			listaAutores=mLibreria.findAllLibAutor();
			JSFUtil.crearMensajeINFO("Autor eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
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


	public LibAutor getNuevoAutor() {
		return nuevoAutor;
	}


	public void setNuevoAutor(LibAutor nuevoAutor) {
		this.nuevoAutor = nuevoAutor;
	}


	public LibAutor getEdicionAutor() {
		return edicionAutor;
	}

	public void setEdicionAutor(LibAutor edicionAutor) {
		this.edicionAutor = edicionAutor;
	}


}
