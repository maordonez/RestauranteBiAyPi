/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.dao;

import co.edu.ufps.modelo.entidad.Estado;
import co.edu.ufps.modelo.entidad.Mesa;
import co.edu.ufps.modelo.entidad.Pedido;
import co.edu.ufps.modelo.entidad.Persona;
import co.edu.ufps.modelo.entidad.Plato;
import co.edu.ufps.modelo.interfaces.PedidoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author miuguel
 */
public class MysqlPedidoDAO implements PedidoDAO {

    private Connection con;

    public MysqlPedidoDAO(Connection con) {
        this.con = con;
    }

   

    @Override
    public Pedido find(int valor) {
        Pedido pedido = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT p.`codigo`, p.`fecha`, p.`mesa`as codigoMesa,m.`nombre` as nombreMesa, p.`plato` as codigoPlato, pl.`nombre` as nombrePlato, p.`cantidad`, e.`codigo` as codigoEstado, e.`nombre` as nombreEstado, p.mesero as codigoMesero FROM `Pedido` p INNER JOIN Mesa m ON m.codigo=p.mesa INNER JOIN Plato pl ON pl.codigo=p.plato INNER JOIN Estado e ON e.codigo=p.estado WHERE p.codigo=? LIMIT 1";
        try {
  
            preparedStatement = this.con.prepareStatement(sql);
            preparedStatement.setInt(1, valor);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                int codigo = rs.getInt("codigo");
                Date fecha = rs.getDate("fecha");
                int codigoPlato =rs.getInt("codigoPlato");
                String nombrePlato =rs.getString("nombrePlato");
                int codigoMesa =rs.getInt("codigoMesa");
                String nombreMesa=rs.getString("nombreMesa");
                int codigoEstado=rs.getInt("codigoEstado");
                String nombreEstado=rs.getString("nombreEstado");
                int cantidadPedido =rs.getInt("cantidad");
                int codigoMesero=rs.getInt("codigoMesero");
                
                Persona persona = new Persona(codigoMesero);
                Plato plato = new Plato(codigoPlato, nombrePlato);
                Mesa mesa = new Mesa(codigoMesa, nombreMesa);
                Estado estado = new Estado(codigoEstado, nombreEstado);
                //pedido
                pedido = new Pedido(mesa, plato, cantidadPedido);
                pedido.setCodigo(codigo);
                pedido.setEstado(estado);
                pedido.setFecha(fecha);
                pedido.setPersona(persona);
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
        return pedido;
    }

    @Override
    public boolean registrar(Pedido pedido) {
        boolean estado = false;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO `Pedido`(`fecha`, `mesa`, `plato`, `cantidad`, `estado`,`mesero`) VALUES (Now(),?,?,?,1,?)";
        try {
            preparedStatement = this.con.prepareStatement(sql);

            preparedStatement.setInt(1, pedido.getMesa().getCodigo());
            preparedStatement.setInt(2, pedido.getPlato().getCodigo());
            preparedStatement.setInt(3, pedido.getCantidad());
            preparedStatement.setInt(4, pedido.getPersona().getCodigo());
   

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            estado=true;

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
        return estado;
    }

    @Override
    public List<Pedido> findBy(Map<String, Object> filtro) {
        List<Pedido> lista =new ArrayList();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT p.`codigo`, p.`fecha`, p.`mesa`as codigoMesa,m.`nombre` as nombreMesa, p.`plato` as codigoPlato, pl.`nombre` as nombrePlato, p.`cantidad`, e.`codigo` as codigoEstado, e.`nombre` as nombreEstado FROM `Pedido` p INNER JOIN Mesa m ON m.codigo=p.mesa INNER JOIN Plato pl ON pl.codigo=p.plato INNER JOIN Estado e ON e.codigo=p.estado WHERE ";
        Collection<String> llaves =filtro.keySet();
        for(String key:llaves){
            sql+=key+"=? and ";
        }
        sql =sql.substring(0, sql.length()-5);
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
                Date fecha = rs.getDate("fecha");
                int codigoPlato =rs.getInt("codigoPlato");
                String nombrePlato =rs.getString("nombrePlato");
                int codigoMesa =rs.getInt("codigoMesa");
                String nombreMesa=rs.getString("nombreMesa");
                int codigoEstado=rs.getInt("codigoEstado");
                String nombreEstado=rs.getString("nombreEstado");
                int cantidadPedido =rs.getInt("cantidad");
                
                Plato plato = new Plato(codigoPlato, nombrePlato);
                Mesa mesa = new Mesa(codigoMesa, nombreMesa);
                Estado estado = new Estado(codigoEstado, nombreEstado);
                //pedido
                Pedido pedido = new Pedido(mesa, plato, cantidadPedido);
                pedido.setCodigo(codigo);
                pedido.setEstado(estado);
                pedido.setFecha(fecha);
                lista.add(pedido);
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

    @Override
    public boolean actualizar(Pedido pedido) {
        boolean estado = false;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE `Pedido` SET `mesa`=?,`plato`=?,`cantidad`=?,`estado`=?,`mesero`=? WHERE `codigo`=?";
        try {
           
            preparedStatement = this.con.prepareStatement(sql);
            preparedStatement.setInt(1, pedido.getMesa().getCodigo());
            preparedStatement.setInt(2, pedido.getPlato().getCodigo());
            preparedStatement.setInt(3, pedido.getCantidad());
            preparedStatement.setInt(4, pedido.getEstado().getCodigo());
            preparedStatement.setInt(5, pedido.getPersona().getCodigo());
            preparedStatement.setInt(6, pedido.getCodigo());

            // execute insert SQL stetement
            preparedStatement.executeUpdate();

            estado=true;

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
        return estado;
    }

    @Override
    public List<Pedido> findAll() {
        List<Pedido> lista =new ArrayList();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT p.`codigo`, p.`fecha`, p.`mesa`as codigoMesa,m.`nombre` as nombreMesa, p.`plato` as codigoPlato, pl.`nombre` as nombrePlato, p.`cantidad`, e.`codigo` as codigoEstado, e.`nombre` as nombreEstado FROM `Pedido` p INNER JOIN Mesa m ON m.codigo=p.mesa INNER JOIN Plato pl ON pl.codigo=p.plato INNER JOIN Estado e ON e.codigo=p.estado";
        try {
            preparedStatement = this.con.prepareStatement(sql);
            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                Date fecha = rs.getDate("fecha");
                int codigoPlato =rs.getInt("codigoPlato");
                String nombrePlato =rs.getString("nombrePlato");
                int codigoMesa =rs.getInt("codigoMesa");
                String nombreMesa=rs.getString("nombreMesa");
                int codigoEstado=rs.getInt("codigoEstado");
                String nombreEstado=rs.getString("nombreEstado");
                int cantidadPedido =rs.getInt("cantidad");
                
                Plato plato = new Plato(codigoPlato, nombrePlato);
                Mesa mesa = new Mesa(codigoMesa, nombreMesa);
                Estado estado = new Estado(codigoEstado, nombreEstado);
                //pedido
                Pedido pedido = new Pedido(mesa, plato, cantidadPedido);
                pedido.setCodigo(codigo);
                pedido.setEstado(estado);
                pedido.setFecha(fecha);
                lista.add(pedido);
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
