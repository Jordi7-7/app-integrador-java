package minimarketdemo.model.pagos.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import minimarketdemo.model.core.entities.Pago;
import minimarketdemo.model.core.entities.PryProyecto;
import minimarketdemo.model.core.managers.ManagerDAO;
import minimarketdemo.model.seguridades.dtos.LoginDTO;

/**
 * Session Bean implementation class ManagerPagos
 */
@Stateless
@LocalBean
public class ManagerPagos {

	@EJB
	private ManagerDAO mDAO;
	
    public ManagerPagos() {
        
    }

    public List<Pago> findDTOPagos(){
    	return this.mDAO.findAll(Pago.class);
    }
    
    public void insertarPago(Pago pago) throws Exception{
    	mDAO.insertar(pago);
    }
}
