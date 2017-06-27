/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.fabrica;

import co.edu.ufps.modelo.interfaces.EstadoDAO;
import co.edu.ufps.modelo.interfaces.MesaDAO;
import co.edu.ufps.modelo.interfaces.PedidoDAO;
import co.edu.ufps.modelo.interfaces.PersonaDAO;
import co.edu.ufps.modelo.interfaces.PlatoDAO;

/**
 *
 * @author miuguel
 */
public abstract class Fabrica {
    public static final int MYSQL = 1;
    public static final int SQL_SERVER = 2;
    public static final int ORACLE = 3;
    
    public abstract PlatoDAO getPlatoDAO();
    public abstract MesaDAO getMesaDAO();
    public abstract PedidoDAO getPedidoDAO();
    public abstract PersonaDAO getPersonaDAO();
    public abstract EstadoDAO getEstadoDAO();
    
    public static Fabrica getSubFabrica(int tipo){
        switch(tipo){
            case MYSQL:
                return new SubFabricaMysql();
            case SQL_SERVER:
                return null;
   
            case ORACLE:
                return null;

        }
        return null;
    }
}
