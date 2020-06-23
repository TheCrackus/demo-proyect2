/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import clases.Administrador;
import clases.Usuario;
import dao.AdministradorDAO;
import dao.UsuarioDAO;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utilerias.Sesion;

/**
 *
 * @author Erick Montes
 */
@Named(value = "loginMB")
@RequestScoped
@Data
@AllArgsConstructor
@NoArgsConstructor
@ManagedBean
public class LoginMB extends BaseBean implements Serializable {

    private Administrador admin;
    private Usuario user;
    private AdministradorDAO Adao = new AdministradorDAO();
    private UsuarioDAO Udao = new UsuarioDAO();
    private String Usuario = "";
    private String Contrasena = "";

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    @PostConstruct
    public void init() {
        admin = new Administrador();
        user = new Usuario();
    }

    public String login() {
        if (Sesion.getEstadoSesion()) {
            return "/index.xhtml?faces-redirect=true";
        }
        System.out.println(Usuario + Contrasena);
        String redireccion = null;
        try {
            user.setUsuario(Usuario);
            user.setContrasena(Contrasena);
            admin.setAdminUsuario(Usuario);
            admin.setAdminContrasena(Contrasena);
            admin = Adao.iniciarSesion(admin);
            user = Udao.iniciarSesion(user);
            if (admin.getAdminPaterno() != null) {
                Sesion.iniciarSesion(FacesContext.getCurrentInstance());
                Sesion.setDatosSesion(admin.getAdminUsuario(),admin.getIdAdministrador());
                setDtoS1();
                isAdmin = true;
                Sesion.setIsAdmin(isAdmin);
                setAccion(ACC_ACTUALIZAR);
                redireccion = "/admin/perfilAdmin?faces-redirect=true";
            } else if (user.getUsuarioPaterno() != null) {
                Sesion.iniciarSesion(FacesContext.getCurrentInstance());
                Sesion.setDatosSesion(user.getUsuario(),user.getIdUsuario());
                setDtoS();
                isAdmin = false;
                Sesion.setIsAdmin(isAdmin);
                setAccion(ACC_ACTUALIZAR);
                redireccion = "/usuario/perfilUsuario?faces-redirect=true";
            } else {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
            }
        } catch (Exception e) {
            Usuario = "";
            Contrasena = "";
            redireccion = "/index.xhtml?faces-redirect=true";
        }
        return redireccion;
    }

    public String cerrarSesion() {
        try {
            Sesion.iniciarSesion(FacesContext.getCurrentInstance());
            Sesion.cerrarSesion();
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
