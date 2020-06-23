package clases;

import java.io.InputStream;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table (name = "Usuario")
public class Usuario implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idUsuario;
    private String UsuarioNombre;
    private String UsuarioPaterno;
    private String UsuarioMaterno;
    private String UsuarioCorreo;
    private String Usuario;
    private String Contrasena;
    @javax.persistence.Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] UsuarioImagen;
    
    public Usuario(){
        
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuarioNombre() {
        return UsuarioNombre;
    }

    public void setUsuarioNombre(String UsuarioNombre) {
        this.UsuarioNombre = UsuarioNombre;
    }

    public String getUsuarioPaterno() {
        return UsuarioPaterno;
    }

    public void setUsuarioPaterno(String UsuarioPaterno) {
        this.UsuarioPaterno = UsuarioPaterno;
    }

    public String getUsuarioMaterno() {
        return UsuarioMaterno;
    }

    public void setUsuarioMaterno(String UsuarioMaterno) {
        this.UsuarioMaterno = UsuarioMaterno;
    }

    public String getUsuarioCorreo() {
        return UsuarioCorreo;
    }

    public void setUsuarioCorreo(String UsuarioCorreo) {
        this.UsuarioCorreo = UsuarioCorreo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public byte[] getUsuarioImagen() {
        return UsuarioImagen;
    }

    public void setUsuarioImagen(byte[] UsuarioImagen) {
        this.UsuarioImagen = UsuarioImagen;
    }
    
    @Override
    public String toString(){
        String result = "";
        result = "\n id: " + idUsuario
                + "\n Nombre: " + UsuarioNombre
                + "\n Apellido Paterno: " + UsuarioPaterno
                + "\n Apellido Materno: " + UsuarioPaterno
                + "\n Correo: " + UsuarioCorreo
                + "\n Usuario: "+ Usuario
                + "\n Contrasena: " + Contrasena;
        return result;
    }
}