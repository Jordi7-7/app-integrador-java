package minimarketdemoFact.model.pedido.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemoFact.model.core.managers.ManagerDAO;
import minimarketdemoFact.model.entities.VwPedido;

/**
 * Session Bean implementation class ManagerBiblioteca
 */
@Stateless
@LocalBean
public class ManagerPedido {
	
	@EJB
	private ManagerDAO mDAO; 
    /**
     * Default constructor. 
     */
    public ManagerPedido() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public List<VwPedido> findAllPedido(){
    	return mDAO.findAll(VwPedido.class); 	
    }
     

}