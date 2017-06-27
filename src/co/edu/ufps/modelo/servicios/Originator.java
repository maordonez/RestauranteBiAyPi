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
public class Originator {
    List<Captura> listado; 
    
    public Originator(List<Captura> listado){
        this.listado=listado;
    }
    public Memento crearMemento(){
        return new Memento(listado);
    }
    public void setMemento(Memento m){
        this.listado=m.getEstados();
    }
}
