package minimarketdemo.model.libreria.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.LibAutor;
import minimarketdemo.model.core.entities.LibLibro;
import minimarketdemo.model.core.entities.ThmCargo;
import minimarketdemo.model.core.entities.ThmEmpleado;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.seguridades.managers.ManagerSeguridades;

/**
 * Session Bean implementation class ManagerBiblioteca
 */
@Stateless
@LocalBean
public class ManagerLibreria {
	
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerSeguridades mSeguridades;

    /**
     * Default constructor. 
     */
    public ManagerLibreria() {
        // TODO Auto-generated constructor stub
    }
    
    //LIBROS:
    @SuppressWarnings("unchecked")
	public List<LibLibro> findAllLibLibro(){
    	return mDAO.findAll(LibLibro.class);
    }
    
    public LibLibro insertarLibLibro(LibLibro nuevoLibro , int idLibAutor) throws Exception{
    	nuevoLibro.setLibAutor(findByIdLibAutor(idLibAutor));
    	mDAO.insertar(nuevoLibro);
    	return nuevoLibro;
    }
    
    public void actualizarLibLibro(LibLibro libro , int idLibAutor) throws Exception{
    	LibLibro edicionLibro = (LibLibro) mDAO.findById(LibLibro.class, libro.getId());
    	
        // Encuentra el autor relacionado desde la base de datos
        LibAutor nuevoAutor = findByIdLibAutor(idLibAutor);
    	
        // Actualiza las propiedades del libro
        edicionLibro.setTitulo(libro.getTitulo());
        edicionLibro.setGenero(libro.getGenero());
        edicionLibro.setAnioPublicacion(libro.getAnioPublicacion());
        
        // Establece la nueva relación con el autor
        edicionLibro.setLibAutor(nuevoAutor);
    	
    	mDAO.actualizar(edicionLibro);
    	
    }
    
    public void eliminarLibro(int idLibLibro) throws Exception {
    	mDAO.eliminar(LibLibro.class, idLibLibro);
    }
    
    //AUTORES
    public LibAutor findByIdLibAutor(int idLibAutor) throws Exception {
    	return (LibAutor) mDAO.findById(LibAutor.class, idLibAutor);
    }
    
    @SuppressWarnings("unchecked")
    public List<LibAutor> findAllLibAutor(){
    	return mDAO.findAll(LibAutor.class);
    }
    
    public LibAutor insertarLibAutor(LibAutor nuevoAutor) throws Exception{
    	mDAO.insertar(nuevoAutor);
    	return nuevoAutor;
    }
    
    public void actualizarAutor(LibAutor autor) throws Exception {
    	LibAutor edicionAutor=(LibAutor) mDAO.findById(LibAutor.class, autor.getId());
    	edicionAutor.setNombre(autor.getNombre());
    	edicionAutor.setNacionalidad(autor.getNacionalidad());
    	mDAO.actualizar(edicionAutor);
    }
    
    public void eliminarAutor(int idLibAutor) throws Exception {
    	mDAO.eliminar(LibAutor.class, idLibAutor);
    }

}
