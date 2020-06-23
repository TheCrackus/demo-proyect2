package utilerias;
import java.io.IOException;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Listener de autorización. Esta clase se encarga de verificar si la sesión 
 * esta activa para que el usuario pueda navegar por una aplicación. Si el 
 * usuario no tiene una sesión activa, la clase lo redirige a la página de 
 * inicio de la aplicacón.
*/
public class AutorizacionListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		try {
			String paginaActual = event.getFacesContext().getViewRoot().getViewId();
			Sesion.iniciarSesion(event.getFacesContext());
			
			if (!paginaActual.contains("index.xhtml") && Sesion.getEstadoSesion() == false && !paginaActual.contains("userForm.xhtml")) {				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "index");
			}
                        else if (paginaActual.contains("index.xhtml") && Sesion.getEstadoSesion() == true && Sesion.getIsAdmin()) {				
				FacesContext.getCurrentInstance().getExternalContext().redirect("admin/perfilAdmin.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "admin");
			}else if (paginaActual.contains("index.xhtml") && Sesion.getEstadoSesion() == true && !Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("usuario/perfilUsuario.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "usuarioP");
			}
                        else if (paginaActual.contains("listaAdmin.xhtml") && Sesion.getEstadoSesion() == true && !Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/perfilUsuario.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "usarioP1");
			}
                        else if (paginaActual.contains("adminForm.xhtml") && Sesion.getEstadoSesion() == true && !Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/perfilUsuario.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "usarioP1");
			}
                        else if (paginaActual.contains("listaUser.xhtml") && Sesion.getEstadoSesion() == true && !Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../usuario/perfilUsuario.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "usarioP1");
			}
                        else if (paginaActual.contains("perfilUsuario.xhtml") && Sesion.getEstadoSesion() == true && Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/perfilAdmin.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "admin1");
			}
                        else if (paginaActual.contains("configuracionJ.xhtml") && Sesion.getEstadoSesion() == true && Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/perfilAdmin.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "admin1");
			}else if (paginaActual.contains("objetos.xhtml") && Sesion.getEstadoSesion() == true && Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/perfilAdmin.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "admin1");
			}else if (paginaActual.contains("partidas.xhtml") && Sesion.getEstadoSesion() == true && Sesion.getIsAdmin()){				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/perfilAdmin.xhtml?faces-redirect=true");
	
				NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
				nh.handleNavigation(event.getFacesContext(), null, "admin1");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
        
        }

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
