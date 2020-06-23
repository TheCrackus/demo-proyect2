/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import clases.Estado;
import java.io.Serializable;

/**
 *
 * @author Erick Montes
 */
public class EstadoDTO implements Serializable {
    private Estado estado;

    public EstadoDTO() {
    estado = new Estado();
    }

    public EstadoDTO(Estado estado) {
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
        
}
