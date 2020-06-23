
package dto;

import clases.Configuracion;
import java.io.Serializable;

public class ConfiguracionDTO implements Serializable {
    private Configuracion configuracion;
    
    public ConfiguracionDTO(){
        configuracion = new Configuracion();
    }
    
    public ConfiguracionDTO(Configuracion configuracion){
        this.configuracion = configuracion;
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(Configuracion configuracion) {
        this.configuracion = configuracion;
    }
}
