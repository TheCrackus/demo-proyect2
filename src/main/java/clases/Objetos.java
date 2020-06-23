
package clases;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Objetos")
public class Objetos implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idObjetos;
    private String armaPrimaria;
    private String armaSecundaria;
    private String tipoMunicion1;
    private int municiona1;
    private int municion1;
    private String tipoMunicion2;
    private int municiona2;
    private int municion2;
    private int comida;
    
    public Objetos(){
    
    }
    
    public int getIdObjetos() {
        return idObjetos;
    }

    public void setIdObjetos(int idObjetos) {
        this.idObjetos = idObjetos;
    }

    public String getArmaPrimaria() {
        return armaPrimaria;
    }

    public void setArmaPrimaria(String armaPrimaria) {
        this.armaPrimaria = armaPrimaria;
    }

    public String getArmaSecundaria() {
        return armaSecundaria;
    }

    public void setArmaSecundaria(String armaSecundaria) {
        this.armaSecundaria = armaSecundaria;
    }

    public String getTipoMunicion1() {
        return tipoMunicion1;
    }

    public void setTipoMunicion1(String tipoMunicion1) {
        this.tipoMunicion1 = tipoMunicion1;
    }

    public int getMuniciona1() {
        return municiona1;
    }

    public void setMuniciona1(int municiona1) {
        this.municiona1 = municiona1;
    }

    public int getMunicion1() {
        return municion1;
    }

    public void setMunicion1(int municion1) {
        this.municion1 = municion1;
    }

    public String getTipoMunicion2() {
        return tipoMunicion2;
    }

    public void setTipoMunicion2(String tipoMunicion2) {
        this.tipoMunicion2 = tipoMunicion2;
    }

    public int getMuniciona2() {
        return municiona2;
    }

    public void setMuniciona2(int municiona2) {
        this.municiona2 = municiona2;
    }

    public int getMunicion2() {
        return municion2;
    }

    public void setMunicion2(int municion2) {
        this.municion2 = municion2;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }
    
    @Override
    public String toString(){
        String result = "";
        result = "\n idObjetos: "+idObjetos
                +"\n armaPrimaria: "+armaPrimaria
                +"\n armaSecundaria: "+armaSecundaria
                +"\n tipoMunicion1: "+tipoMunicion1
                +"\n municiona1: "+municiona1
                +"\n municion1: "+municion1
                +"\n tipoMunicion2: "+tipoMunicion2
                +"\n municiona2: "+municiona2
                +"\n municion2: "+municion2
                +"\n comida: "+comida;
        return result;
    }
}
