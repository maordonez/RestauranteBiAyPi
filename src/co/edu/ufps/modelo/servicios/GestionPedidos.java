/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.servicios;

import co.edu.ufps.modelo.entidad.Estado;
import co.edu.ufps.modelo.entidad.Mesa;
import co.edu.ufps.modelo.entidad.Pedido;
import co.edu.ufps.modelo.entidad.Persona;
import co.edu.ufps.modelo.entidad.Plato;
import co.edu.ufps.modelo.fabrica.Fabrica;
import co.edu.ufps.modelo.interfaces.EstadoDAO;
import co.edu.ufps.modelo.interfaces.MesaDAO;
import co.edu.ufps.modelo.interfaces.PedidoDAO;
import co.edu.ufps.modelo.interfaces.PlatoDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author miuguel
 */
public class GestionPedidos {

    private Fabrica fabrica;

    public GestionPedidos() {
        fabrica = Fabrica.getSubFabrica(Fabrica.MYSQL);
    }

    public List<Plato> listarPlatos() {

        PlatoDAO dao = fabrica.getPlatoDAO();
        return dao.listar();
    }

    public List<Mesa> listarMesas() {

        MesaDAO dao = fabrica.getMesaDAO();
        return dao.listar();
    }

    public String registrarPedido(Pedido pedido) {
        PedidoDAO dao = fabrica.getPedidoDAO();
        pedido.setPersona(GestionUsuarios.getInstance().getPersona());
        if (dao.registrar(pedido)) {
            return "Registro exitoso";
        }
        return "Fallo de registro";
    }

    /**
     * Metodo lista los pedidos por una mesa
     * @param mesa
     * @return 
     */
    public List<Pedido> listarPedidos(Mesa mesa) {
        PedidoDAO dao = fabrica.getPedidoDAO();
        Map<String, Object> filtro = new HashMap<>();
        filtro.put("p.mesa", mesa.getCodigo());
        List<Pedido> listado = dao.findBy(filtro);
        return listado;
    }
    /**
     * Metodo lista todos los pedidos;
     * @return 
     */
    public List<Pedido> listarPedidos() {
        PedidoDAO dao = fabrica.getPedidoDAO();
        List<Pedido> listado = dao.findAll();
        return listado;
    }
    
    /**
     * Metodo lista los pedidos por un mesero 
     * @param persona
     * @return 
     */
    public List<Pedido> listarPedidos(Persona persona) {
        PedidoDAO dao = fabrica.getPedidoDAO();
        Map<String, Object> filtro = new HashMap<>();
        filtro.put("p.mesero", persona.getCodigo());
        List<Pedido> listado = dao.findBy(filtro);
        return listado;
    }
    /**
     * Metodo lista los pedidos por estado "LISTO"
     * @return 
     */
    public List<Pedido> listarPedidoTerminado(){
        PedidoDAO dao = fabrica.getPedidoDAO();
        Map<String, Object> filtro = new HashMap<>();
        filtro.put("p.estado", 3);
        List<Pedido> listado=dao.findBy(filtro);
        return listado;
    }

    /**
     * Metodo lista los pedidos por el cocinero encargado del plato
     * @param persona
     * @return 
     */
    public List<Pedido> listadosPedidosCocinero(Persona persona) {
        PedidoDAO dao = fabrica.getPedidoDAO();
        Map<String, Object> filtro = new HashMap<>();
        filtro.put("pl.cocineo", persona.getCodigo());
        List<Pedido> listado = dao.findBy(filtro);
        Map mapa = new HashMap<>();
        
        
        return listado;
    }
    

    /**
     * Metodo lista los estados de un pedido
     * @return 
     */
    public List<Estado> listadoEstados() {
        EstadoDAO dao = fabrica.getEstadoDAO();
        return dao.listar();

    }

    /**
     * Metodo Actualiza el estado de un pedido
     * @param estado
     * @param codPedido
     * @return 
     */
    public boolean actualizarEstadoPedido(Estado estado, int codPedido) {
        PedidoDAO dao = fabrica.getPedidoDAO();
        System.out.println("codigoPedido: "+codPedido);
        Pedido pedido = dao.find(codPedido);
        if (pedido != null) {
            pedido.setEstado(estado);
            dao=fabrica.getPedidoDAO();
            return dao.actualizar(pedido);
        }

        return false;

    }

}
