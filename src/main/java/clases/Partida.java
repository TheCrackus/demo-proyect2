
package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Partida")
public class Partida implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idPartida;
    private int vida;
    private int eliminados;
    private int puntaje;
    private int tiempoSegundos;
    private int tiempoMinutos;
    private int tiempoHoras;
    private int idPuntoControl;
    private int idObjetos;
    
    public Partida(){
    
    }
    
    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getEliminados() {
        return eliminados;
    }

    public void setEliminados(int eliminados) {
        this.eliminados = eliminados;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }

    public void setTiempoSegundos(int tiempoSegundos) {
        this.tiempoSegundos = tiempoSegundos;
    }

    public int getTiempoMinutos() {
        return tiempoMinutos;
    }

    public void setTiempoMinutos(int tiempoMinutos) {
        this.tiempoMinutos = tiempoMinutos;
    }

    public int getTiempoHoras() {
        return tiempoHoras;
    }

    public void setTiempoHoras(int tiempoHoras) {
        this.tiempoHoras = tiempoHoras;
    }

    public int getIdPuntoControl() {
        return idPuntoControl;
    }

    public void setIdPuntoControl(int idPuntoControl) {
        this.idPuntoControl = idPuntoControl;
    }

    public int getIdObjetos() {
        return idObjetos;
    }

    public void setIdObjetos(int idObjetos) {
        this.idObjetos = idObjetos;
    }
    
    @Override
    public String toString(){
        String result = "";
        result = "\n id: "+idPartida
                +"\n vida: "+vida
                +"\n elimnados: "+eliminados
                +"\n puntaje: "+puntaje
                +"\n tiempoSegundos: "+tiempoSegundos
                +"\n tiempoMinutos: "+tiempoMinutos
                +"\n tiempoHoras: "+tiempoHoras
                +"\n idPuntoControl: "+idPuntoControl
                +"\n idObjetos: " +idObjetos;
        return result;
    }
}
