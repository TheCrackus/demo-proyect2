
package clases;

import java.io.Serializable;

public class puntajes implements Serializable {//clase paticular que no corresponde a ninguna tabla
    private String nombre;
    private String correo;
    private int eliminados;
    private int puntaje;
    private int segundos;
    private int minutos;
    private int horas;
    
    public puntajes(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
    @Override
    public String toString(){
        String result = "";
        result = "\n Nombre: "+nombre
                +"\n Correo: "+correo
                +"\n eliminados: "+eliminados
                +"\n puntaje: "+puntaje
                +"\n segundos: "+segundos
                +"\n minutos: "+minutos
                +"\n horas: "+horas;
        return result;
    }
}
