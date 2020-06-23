
package dto;

import clases.Objetos;
import java.io.Serializable;

public class ObjetosDTO implements Serializable {

    public void setObjetos(Objetos objetos) {
        this.objetos = objetos;
    }
    private Objetos objetos;
    
    public ObjetosDTO(){
        objetos = new Objetos();
    }
    
    public ObjetosDTO(Objetos objetos){
        this.objetos = objetos;
    }
    
    public Objetos getObjetos() {
        return objetos;
    }
}
