package utilerias;
import java.util.Enumeration;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Clase encargada de gestionar la sesión de un usuario a nivel de JSF.
 */
public class Sesion{
	private static HttpSession httpSession;
        private static Boolean sesionActiva;
        private static Boolean isAdmin;

    public static void iniciarSesion(FacesContext fc) {
        httpSession = (HttpSession) fc.getExternalContext().getSession(false);
    }
    public static void setDatosSesion(String nomUsuario, int idUsuario) {
        try {
            if (httpSession.getId() != null && !httpSession.getId().isEmpty()) {
                sesionActiva = true;
                httpSession.setAttribute("Usuario", nomUsuario);
                httpSession.setAttribute("idUsuario", idUsuario );
                httpSession.setAttribute("sesionActiva", sesionActiva);
            } else {
                sesionActiva = false;
                httpSession.setAttribute("sesionActiva", sesionActiva);
                throw new Exception("Error en el inicio de sesión");
            }
        } catch (Exception e) {
            httpSession.invalidate();
            e.printStackTrace();
        }
    }

    /**
     * Método encargado de cerrar una sesión actual.
     */
    public static void cerrarSesion() {
    	Enumeration<String> atributos = null;
    	
        try {
            if (httpSession != null && httpSession.getId() != null && !httpSession.getId().isEmpty()) {
                sesionActiva = false;
                
                atributos = httpSession.getAttributeNames();
                
                while (atributos.hasMoreElements()) {
                	String atributo = atributos.nextElement();
                	
                	httpSession.removeAttribute(atributo);
                }

                httpSession.invalidate();
                isAdmin = false;
            } else {
                throw new Exception("Error en el cierre de sesión");
                // TODO Agregar excepciones de sistema
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método encargado de iniciar una sesión para el usuario que se está
     * autenticando en el sistema.
     */
    public static String getDatosSesion() {
        if (httpSession != null && httpSession.getId() != null && !httpSession.getId().isEmpty()) {
            httpSession.setAttribute("sesionActiva", sesionActiva);
            
            return (String) httpSession.getAttribute("Usuario");
        } else {
            return null;
        }
    }

    /**
     * Método encargado de verificar el estado de una sesión de usuario.
    */
    public static Boolean getEstadoSesion() {
        if (httpSession != null 
        && httpSession.getId() != null 
        && !httpSession.getId().isEmpty() 
        && httpSession.getAttribute("sesionActiva") != null) {
            return Boolean.parseBoolean(httpSession.getAttribute("sesionActiva").toString());
        } else {
        	return false;
        }
    }
    
    public static void setIsAdmin(boolean is){
        isAdmin = is;
    }
    
    public static boolean getIsAdmin(){
        return isAdmin;
    }
    
    public static int getSesionId(){
        if (httpSession != null && httpSession.getId() != null && !httpSession.getId().isEmpty()) {
            httpSession.setAttribute("sesionActiva", sesionActiva);
            
            return (int) httpSession.getAttribute("idUsuario");
        } else {
            return -1;
        }
    }
}
