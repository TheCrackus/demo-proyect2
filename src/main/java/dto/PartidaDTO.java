
package dto;

import clases.Objetos;
import clases.Partida;
import clases.puntoControl;
import java.io.Serializable;

public class PartidaDTO implements Serializable {

    private Partida partida;
    private Objetos objetos;
    private puntoControl puntoControl;
    
    public PartidaDTO(){
        partida = new Partida();
        objetos = new Objetos();
        puntoControl = new puntoControl();
    }
    
    public PartidaDTO(Partida partida, Objetos objetos, puntoControl puntoControl){
        this.partida = partida;
        this.objetos = objetos;
        this.puntoControl = puntoControl;
    }
    
    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public Objetos getObjetos() {
        return objetos;
    }

    public void setObjetos(Objetos objetos) {
        this.objetos = objetos;
    }

    public puntoControl getPuntoControl() {
        return puntoControl;
    }

    public void setPuntoControl(puntoControl puntoControl) {
        this.puntoControl = puntoControl;
    }
}
