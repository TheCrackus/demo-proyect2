
package clases;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "puntoControl")
public class puntoControl {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idPuntoControl;
    private int x;
    private int y;
    
    public puntoControl(){
        
    }

    public int getIdPuntoControl() {
        return idPuntoControl;
    }

    public void setIdPuntoControl(int idPuntoControl) {
        this.idPuntoControl = idPuntoControl;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    @Override
    public String toString(){
        String result = "";
        result = "\n idPuntoControl: "+idPuntoControl
                +"\n x: "+x
                +"\n y: "+y;
        return result;
    }
}
