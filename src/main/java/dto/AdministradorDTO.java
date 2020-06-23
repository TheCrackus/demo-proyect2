
package dto;

import clases.Administrador;
import java.io.Serializable;

public class AdministradorDTO implements Serializable {
    private Administrador administrador;
    
    public AdministradorDTO(){
        administrador = new Administrador();
    }
    
    public AdministradorDTO(Administrador administrador){
        this.administrador = administrador;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "AdministradorDTO{" + "administrador=" + administrador + '}';
    }
    
}
