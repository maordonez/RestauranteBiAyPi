/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.dao;

import co.edu.ufps.modelo.entidad.Mesa;
import co.edu.ufps.modelo.entidad.Persona;
import co.edu.ufps.modelo.interfaces.PersonaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miuguel
 */
public class MysqlPersonaDAO implements PersonaDAO{
    private final Connection con;
    
    public MysqlPersonaDAO(Connection con){
        this.con=con;
    }

    @Override
    public List<Persona> findBy(Map<String, Object> filtro) {
        List<Persona> lista =new ArrayList();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT `codigo`, `nombre`, `apellido`, `documento`, `clave`, `tipo` FROM `Persona` WHERE ";
        Collection<String> llaves =filtro.keySet();
        for(String key:llaves){
            sql+=key+"=? and ";
        }
        sql =sql.substring(0, sql.length()-5);
        System.out.println(sql);
        try {
            preparedStatement = this.con.prepareStatement(sql);
            Collection<Object> values =filtro.values();
            int i=1;
            for(Object value:values){
                preparedStatement.setObject(i,value);
                i++;
            }
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                String apellido =rs.getString("apellido");
                String documento =rs.getString("documento");
                String clave =rs.getString("clave");
                String tipo=rs.getString("tipo");
                lista.add(new Persona(codigo, nombre, apellido, documento, clave,tipo));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MysqlPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (this.con != null) {
                try {
                    this.con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MysqlPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return lista;
    }
    
}
