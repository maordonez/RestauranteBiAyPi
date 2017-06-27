/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.servicios;

import java.util.List;

/**
 *
 * @author miuguel
 */
public class Memento {
    private List<Captura> estados;
    public Memento(List<Captura> estados){
        this.estados=estados;
    }

    public List<Captura> getEstados() {
        return estados;
    }

    public void setEstados(List<Captura> estados) {
        this.estados = estados;
    }
    
}
