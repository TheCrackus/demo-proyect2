
package dto;

import clases.Configuracion;
import dto.PartidaDTO;
import clases.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO implements Serializable{

    private Usuario usuario;
    private Configuracion configuracion;
    private List<PartidaDTO> partidas;
    
    public UsuarioDTO(){
        usuario = new Usuario();
        configuracion = new Configuracion();
        partidas = new ArrayList<PartidaDTO>();
    }
    
    public UsuarioDTO(Usuario usuario, Configuracion configuracion, ArrayList<PartidaDTO> partidas){
        this.usuario = usuario;
        this.configuracion = configuracion;
        this.partidas = partidas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
    
    public List<PartidaDTO> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<PartidaDTO> partidas) {
        this.partidas = partidas;
    }
 
}
