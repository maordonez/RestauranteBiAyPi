/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.dao;

import co.edu.ufps.modelo.entidad.Estado;
import co.edu.ufps.modelo.interfaces.EstadoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miuguel
 */
public class MysqlEstadoDAO implements EstadoDAO{
    private Connection con;
    
    public MysqlEstadoDAO(Connection con){
        this.con=con;
    }

    @Override
    public List<Estado> listar() {
            List<Estado> lista = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT `codigo`, `nombre` FROM `Estado`";
        try {
  
            preparedStatement = this.con.prepareStatement(sql);
    
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                String nombre = rs.getString("nombre");
                lista.add(new Estado(codigo,nombre));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MysqlMesaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (this.con != null) {
                try {
                    this.con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MysqlMesaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return lista;
    }
    
}
