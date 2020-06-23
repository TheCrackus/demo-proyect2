/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import clases.Municipio;
import java.io.Serializable;

/**
 *
 * @author Erick Montes
 */
public class MunicipioDTO implements Serializable {
    private Municipio municipio;

    public MunicipioDTO() {
        municipio = new Municipio();
    }

    public MunicipioDTO(Municipio municipio) {
        this.municipio = municipio;
    }

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
    
    
}
