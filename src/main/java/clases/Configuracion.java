
package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Configuracion")
public class Configuracion implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idConfiguracion;
    private float volumen;
    private String resolucion;
    private int idUsuarioC;
    
    public Configuracion(){
    
    }
    
    public int getIdConfiguracion() {
        return idConfiguracion;
    }

    public void setIdConfiguracion(int idConfiguracion) {
        this.idConfiguracion = idConfiguracion;
    }

    public float getVolumen() {
        return volumen;
    }

    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public int getIdUsuarioC() {
        return idUsuarioC;
    }

    public void setIdUsuarioC(int idUsuarioC) {
        this.idUsuarioC = idUsuarioC;
    }
    
    @Override
    public String toString(){
        String result = "";
        result = "\n id: " + idConfiguracion
                +"\n volumen: " + volumen
                +"\n resolucion: " + resolucion
                +"\n idU: " + idUsuarioC; 
        return result;
    }
}
