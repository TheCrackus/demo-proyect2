
package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table (name = "Administrador")
public class Administrador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAdministrador;
    private String adminNombre;
    private String adminPaterno;
    private String adminMaterno;
    private int idMunicipio;
    private String adminCorreo;
    private String adminUsuario;
    private String adminContrasena;
    @javax.persistence.Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] adminImagen;
    
    public Administrador(){
    
    }
    
    public int getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(int idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getAdminNombre() {
        return adminNombre;
    }

    public void setAdminNombre(String adminNombre) {
        this.adminNombre = adminNombre;
    }

    public String getAdminPaterno() {
        return adminPaterno;
    }

    public void setAdminPaterno(String adminPaterno) {
        this.adminPaterno = adminPaterno;
    }

    public String getAdminMaterno() {
        return adminMaterno;
    }

    public void setAdminMaterno(String adminMaterno) {
        this.adminMaterno = adminMaterno;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getAdminCorreo() {
        return adminCorreo;
    }

    public void setAdminCorreo(String adminCorreo) {
        this.adminCorreo = adminCorreo;
    }

    public String getAdminUsuario() {
        return adminUsuario;
    }

    public void setAdminUsuario(String adminUsuario) {
        this.adminUsuario = adminUsuario;
    }

    public String getAdminContrasena() {
        return adminContrasena;
    }

    public void setAdminContrasena(String adminContrasena) {
        this.adminContrasena = adminContrasena;
    }

    public byte[] getAdminImagen() {
        return adminImagen;
    }

    public void setAdminImagen(byte[] adminImagen) {
        this.adminImagen = adminImagen;
    }
    
    @Override
    public String toString() {
        return "Administrador{ " 
                + "idAdministrador = " + getIdAdministrador() + "\n"
                + "adminNombre = " + getAdminNombre() + "\n"
                + "adminPaterno =" + getAdminPaterno() + "\n"
                + "adminMaterno = " + getAdminMaterno() + "\n"
                + "idMunicipio = " + getIdMunicipio() + "\n"
                + "adminCorreo = " + getAdminCorreo() + "\n"
                + "adminUsuario = " + getAdminUsuario() + "\n" 
                + "adminContrasena = " + getAdminContrasena() + "\n";}
    
}
