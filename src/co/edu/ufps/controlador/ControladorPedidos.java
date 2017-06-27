/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.controlador;

import co.edu.ufps.controlador.util.Convertidor;
import co.edu.ufps.modelo.entidad.Estado;
import co.edu.ufps.modelo.entidad.Mesa;
import co.edu.ufps.modelo.entidad.Pedido;
import co.edu.ufps.modelo.entidad.Plato;
import co.edu.ufps.modelo.servicios.Captura;
import co.edu.ufps.modelo.servicios.Caretaker;
import co.edu.ufps.modelo.servicios.GestionPedidos;
import co.edu.ufps.modelo.servicios.GestionUsuarios;
import co.edu.ufps.modelo.servicios.Originator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author miuguel
 */
public class ControladorPedidos {
    private GestionPedidos pedidos;
    private Caretaker almacen;
    public ControladorPedidos(){
        pedidos = new GestionPedidos();
        almacen = new Caretaker();
    }
    public DefaultComboBoxModel modeloMesa(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(pedidos.listarMesas().toArray());
        return modelo;
    }
    public DefaultComboBoxModel modeloPlato(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel(pedidos.listarPlatos().toArray());
        return modelo;
    }
    
    public String registrarPedido(Object opcionPlato,Object opcionMesa,int cantidad){
        Plato plato = (Plato) opcionPlato;
        Mesa mesa =(Mesa) opcionMesa;
        Pedido pedido = new Pedido(mesa,plato,cantidad);
        return pedidos.registrarPedido(pedido);
    }
    public boolean Ingresar(String usuario, String clave){
        GestionUsuarios gestionUsuarios= GestionUsuarios.getInstance();
        return gestionUsuarios.validarIngreso(usuario, clave);
    }
    
    public String consultarRol(){
        return GestionUsuarios.getInstance().getTipoUsuario();
    }
    public String consultarNombres(){
        return GestionUsuarios.getInstance().datosSesion();
    }
    /**
     * Genera modelo de tabla con los pedidos de una mesa
     * @param mesa
     * @return 
     */
    public DefaultTableModel pedidosMesa(Object mesa){
        List<Pedido>lista = pedidos.listarPedidos((Mesa)mesa);
        return Convertidor.PedidoToTableModelCliente(lista);
    }
    /**
     * Genera modelo de tabla con los pedidos de un mesero
     * @return 
     */
    public DefaultTableModel pedidosMesero(){
        List<Pedido> lista =pedidos.listarPedidoTerminado();
        return Convertidor.PedidoToTableModelMin(lista);
    }
    /**
     * Genera modelo de tabla con los pedidos que atiende un cocinero
     * @return 
     */
    public DefaultTableModel pedidosCocinero(){
        List<Pedido> lista =pedidos.listadosPedidosCocinero(GestionUsuarios.getInstance().getPersona());
        return Convertidor.pedidoToTableModel(lista);   
    }
    /**
     * Genera modelo de tabla con todos los pedidos
     * @return 
     */
    public DefaultTableModel pedidosListado(){
        List<Pedido> lista =pedidos.listarPedidos();
        return Convertidor.PedidoToTableModelMin(lista);
    }
    public DefaultComboBoxModel modeloEstado(){
        DefaultComboBoxModel modelo = new  DefaultComboBoxModel(pedidos.listadoEstados().toArray());
        return modelo;
    }
    public String actualizarEstadoPedido(Object estado,int pedido){
        if(pedidos.actualizarEstadoPedido((Estado)estado, pedido)){
            return  "Actualizacion exitosa";
        }
        return "Fallo actualizacion";
    }
    public DefaultTableModel mostrarMomento(int o){
        return Convertidor.Mementos(almacen.getMemento(o));
    }
    public DefaultComboBoxModel mostrarCantidadMomentos(){
        return Convertidor.CantidadMementos(almacen.getSize());
    }
    public void almacenarMomento(TableModel modelo){
        List<Captura>listado =Convertidor.DefaultTableModelToList(modelo);
        Originator originar = new Originator(listado);
        almacen.addMemento(originar.crearMemento());
    }
}
