package minimarketdemoFact.model.evento.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemoFact.model.core.managers.ManagerDAO;
import minimarketdemoFact.model.entities.VwEvento;

/**
 * Session Bean implementation class ManagerBiblioteca
 */
@Stateless
@LocalBean
public class ManagerEvento {
	
	@EJB
	private ManagerDAO mDAO; 
    /**
     * Default constructor. 
     */
    public ManagerEvento() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public List<VwEvento> findAllEvento(){
    	return mDAO.findAll(VwEvento.class); 	
    }
    

}
