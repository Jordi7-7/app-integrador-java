package minimarketdemoFact.model.factura.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemoFact.model.core.managers.ManagerDAO;
import minimarketdemoFact.model.entities.VwEvento;
import minimarketdemoFact.model.entities.VwFacAnio;
import minimarketdemoFact.model.entities.VwFacMes;

/**
 * Session Bean implementation class ManagerBiblioteca
 */
@Stateless
@LocalBean
public class ManagerFactura {
	
	@EJB
	private ManagerDAO mDAO; 
    /**
     * Default constructor. 
     */
    public ManagerFactura() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings("unchecked")
	public List<VwFacAnio> findAllFacturabyAnio(){
    	return mDAO.findAll(VwFacAnio.class); 	
    }
    
    @SuppressWarnings("unchecked")
	public List<VwFacMes> findAllFacturabyMes(){
    	return mDAO.findAll(VwFacMes.class); 	
    }
    
   public List<VwFacMes> findMesbyAnio(Integer anio){
	   
	   if(anio!=null) {
	   		String consulta="anio="+anio;

	 	   return mDAO.findWhere(VwFacMes.class, consulta, "mes");
	    }
	   
   
   	return this.findAllFacturabyMes();
   	
}

    

}