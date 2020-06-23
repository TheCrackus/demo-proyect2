/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.AdministradorDAO;
import dao.PartidaDAO;
import dao.UsuarioDAO;
import dto.AdministradorDTO;
import dto.UsuarioDTO;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import utilerias.Sesion;


public class BaseBean {

    protected static final String ACC_CREAR = "CREAR";
    protected static final String ACC_ACTUALIZAR = "ACTUALIZAR";
    protected static boolean isRoot = false;
    protected static boolean isAdmin = false;    
    private static UsuarioDTO dtoS = new UsuarioDTO();
    private static UsuarioDAO daoS = new UsuarioDAO();
    private static AdministradorDTO dtoS1 = new AdministradorDTO();
    private static AdministradorDAO daoS1 = new AdministradorDAO();
    
    BaseBean(){
        
    }
    
    protected String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public UsuarioDTO getDtoS(){
        return dtoS;
    }
    
    public void setDtoS(){
        PartidaDAO daoP = new PartidaDAO();
        if(Sesion.getEstadoSesion()){
            dtoS.getUsuario().setIdUsuario(Sesion.getSesionId());
            dtoS = daoS.TraeUsuarioConfiguracion(dtoS);
            dtoS = daoP.TraePartidasUsuario(dtoS);    
            System.out.println(dtoS);
        }else{
            dtoS = new UsuarioDTO();
        }
    }
    public AdministradorDTO getDtoS1(){
        return dtoS1;
    }
    
    public void setDtoS1(){
        if(Sesion.getEstadoSesion()){
            dtoS1.getAdministrador().setIdAdministrador(Sesion.getSesionId());
            dtoS1 = daoS1.leerUno(dtoS1);
        }else{
            dtoS = new UsuarioDTO();
        }
    }

    protected void error(String idMensaje, String mensaje) {
        FacesContext.getCurrentInstance().addMessage(idMensaje,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, mensaje, idMensaje));
    }

    public boolean isModoCrear() {
        if (accion != null) {
            return accion.equals(ACC_CREAR);
        } else {
            return false;
        }
    }

    public boolean isModoActualizar() {
        if (accion != null) {
            return accion.equals(ACC_ACTUALIZAR);
        } else {
            return false;
        }
    }
    
    public boolean isRoot(){
        if(Sesion.getIsAdmin())
            if(Sesion.getSesionId() == 1 )
                isRoot = true;
            else
                isRoot = false;
        return isRoot;            
    }
    
    public void info() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Registro realizado Correctamente"));
    }
     
    public void warn() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Se elimino correctamente"));
    }
     
    public void error() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Fallo en la operacion"));
    }
    
}
