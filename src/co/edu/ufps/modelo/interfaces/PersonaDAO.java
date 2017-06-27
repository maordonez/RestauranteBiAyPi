/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.interfaces;

import co.edu.ufps.modelo.entidad.Persona;
import java.util.List;

import java.util.Map;

/**
 *
 * @author miuguel
 */
public interface PersonaDAO {
    public List<Persona> findBy(Map<String,Object> filtro);
    
}
