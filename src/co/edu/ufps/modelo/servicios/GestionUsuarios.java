/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.servicios;

import co.edu.ufps.modelo.entidad.Persona;
import co.edu.ufps.modelo.fabrica.Fabrica;
import co.edu.ufps.modelo.interfaces.PersonaDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author miuguel
 */
public class GestionUsuarios {
    
    private  static GestionUsuarios instance;
    private Persona persona;
    
    public static GestionUsuarios getInstance(){
        if(instance==null){
            instance= new GestionUsuarios();
        }
        return instance;
    }
    public boolean validarIngreso(String usuario,String clave){
        Fabrica fabrica = Fabrica.getSubFabrica(Fabrica.MYSQL);
        PersonaDAO dao=fabrica.getPersonaDAO();
        Map<String,Object> filtro =new HashMap();
        filtro.put("documento",usuario);
        List<Persona>listado= dao.findBy(filtro);
        for(Persona p:listado){
            if(p.getClave().equals(clave)){
                this.persona=p;
                return true;
            }
            
        }
        
        return false;
    }

    public Persona getPersona() {
        return persona;
    }
    
    
    public String getTipoUsuario(){
        return this.persona.getTipo();
    }
    public String datosSesion(){
        return persona.getApellido()+" "+persona.getNombre();
    }
    
    
    
}
