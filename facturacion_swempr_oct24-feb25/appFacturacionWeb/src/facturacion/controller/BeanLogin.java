package facturacion.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import facturacion.model.manager.ManagerLogin;
import java.io.Serializable;

@Named
@SessionScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	private String codigoUsuario;
	private String clave;
	private String tipoUsuario;
	private boolean acceso;
	@EJB
	private ManagerLogin managerLogin;

	public BeanLogin() {
		managerLogin=new ManagerLogin();
	}
	/**
	 * Action que permite el acceso al sistema.
	 * @return
	 */
	public String accederSistema(){
		acceso=false;
		try {
			//verificamos el acceso del usuario:
			tipoUsuario=managerLogin.accederSistema(codigoUsuario, clave);
			//por seguridad borramos la clave en la sesion:
			clave="";
			//dependiendo del tipo de usuario, direccionamos:
			if(tipoUsuario.equals("SP")){
				acceso=true;
				return "supervisor/index";
			}
			if(tipoUsuario.equals("VD")){
				acceso=true;
				return "vendedor/facturacion";
			}
			//caso contrario, ocurrio un error con el tipo de usuario:
			JSFUtil.crearMensajeERROR("Error al acceder (tipo de usuario).");
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeERROR(e.getMessage());
		}
		return "";
	}
	
	/**
	 * Finaliza la sesion web del usuario.
	 * @return
	 */
	public String salirSistema(){
		String redirectPage = "/clientes/index.xhtml?faces-redirect=true";
		
		if (tipoUsuario != null) {
		    redirectPage = "/login.xhtml?faces-redirect=true";
		}
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return redirectPage;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isAcceso() {
		return acceso;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
