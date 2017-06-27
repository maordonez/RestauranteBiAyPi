/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.fabrica;

import co.edu.ufps.modelo.conexion.BDSingleton;
import co.edu.ufps.modelo.dao.MysqlEstadoDAO;
import co.edu.ufps.modelo.dao.MysqlMesaDAO;
import co.edu.ufps.modelo.dao.MysqlPedidoDAO;
import co.edu.ufps.modelo.dao.MysqlPersonaDAO;
import co.edu.ufps.modelo.dao.MysqlPlatoDAO;
import co.edu.ufps.modelo.interfaces.EstadoDAO;
import co.edu.ufps.modelo.interfaces.MesaDAO;
import co.edu.ufps.modelo.interfaces.PedidoDAO;
import co.edu.ufps.modelo.interfaces.PersonaDAO;
import co.edu.ufps.modelo.interfaces.PlatoDAO;

/**
 *
 * @author miuguel
 */
public class SubFabricaMysql extends Fabrica {

    @Override
    public PlatoDAO getPlatoDAO() {
        return new MysqlPlatoDAO(BDSingleton.getConnection());
    }

    @Override
    public MesaDAO getMesaDAO() {
        return new MysqlMesaDAO(BDSingleton.getConnection());
    }

    @Override
    public PedidoDAO getPedidoDAO() {
        return new MysqlPedidoDAO(BDSingleton.getConnection());
    }

    @Override
    public PersonaDAO getPersonaDAO() {
        return new MysqlPersonaDAO(BDSingleton.getConnection());
    }

    @Override
    public EstadoDAO getEstadoDAO() {
        return new MysqlEstadoDAO(BDSingleton.getConnection());
    }

}
