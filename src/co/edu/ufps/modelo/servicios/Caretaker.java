/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.servicios;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miuguel
 */
public class Caretaker {
    private List<Memento> listado;
    
    public Caretaker(){
        listado=new ArrayList<>();
    }
    
    public void addMemento(Memento m){
        listado.add(m);
    }
    public Memento getMemento(int o){
        return listado.get(o);
    }
    public int getSize(){
        return listado.size();
    }
    
}
