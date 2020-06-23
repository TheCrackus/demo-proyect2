
package dto;

import clases.puntoControl;
import java.io.Serializable;

public class puntoControlDTO implements Serializable {
    private puntoControl puntoControl;
    
    public puntoControlDTO(){
        puntoControl = new puntoControl();
    }
    
    public puntoControlDTO(puntoControl puntoControl){
        this.puntoControl = puntoControl;
    }

    public puntoControl getPuntoControl() {
        return puntoControl;
    }

    public void setPuntoControl(puntoControl puntoControl) {
        this.puntoControl = puntoControl;
    }
}
