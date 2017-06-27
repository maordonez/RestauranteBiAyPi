/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.interfaces;

import co.edu.ufps.modelo.entidad.Plato;
import java.util.List;


/**
 *
 * @author miuguel
 */
public interface PlatoDAO {
    
    public List<Plato> listar();
   
}
