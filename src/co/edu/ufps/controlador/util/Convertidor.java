/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.controlador.util;

import co.edu.ufps.modelo.entidad.Pedido;
import co.edu.ufps.modelo.servicios.Captura;
import co.edu.ufps.modelo.servicios.Memento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author miuguel
 */
public class Convertidor {

    public static DefaultTableModel PedidoToTableModelCliente(List<Pedido> lista) {
        DefaultTableModel modelo;
        String[] columnas = {"MESA", "PLATO", "STATE"};
        Object[][] filas = new Object[lista.size()][3];
        int i = 0;
        for (Pedido p : lista) {
            filas[i][0] = p.getMesa().toString();
            filas[i][1] ="Su plato "+ p.getPlato().toString();
            filas[i][2] ="esta en el STATE "+ p.getEstado().toString();
            i++;
        }
        modelo = new DefaultTableModel(filas, columnas){
              @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return modelo;
    }
    public static DefaultTableModel PedidoToTableModelMin(List<Pedido> lista) {
        DefaultTableModel modelo;
        String[] columnas = {"MESA", "PLATO", "STATE"};
        Object[][] filas = new Object[lista.size()][3];
        int i = 0;
        for (Pedido p : lista) {
            filas[i][0] = p.getMesa().toString();
            filas[i][1] =p.getPlato().toString();
            filas[i][2] = p.getEstado().toString();
            i++;
        }
        modelo = new DefaultTableModel(filas, columnas){
              @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return modelo;
    }
    

    public static DefaultTableModel pedidoToTableModel(List<Pedido> lista) {
        DefaultTableModel modelo;
        String[] columnas = {"CODIGO", "FECHA", "MESA", "PLATO","CANTIDAD", "STATE"};
        Object[][] filas = new Object[lista.size()][6];
        int i = 0;
        for (Pedido p : lista) {
            filas[i][0] = p.getCodigo();
            filas[i][1] = p.getFecha().toString();
            filas[i][2] = p.getMesa();
            filas[i][3] = p.getPlato();
            filas[i][4]= p.getCantidad();
            filas[i][5] = p.getEstado();
            i++;
        }
        modelo = new DefaultTableModel(filas, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return modelo;
    }
    public static List<Captura> DefaultTableModelToList(TableModel modelo){
        List<Captura> listado= new ArrayList();
        int cantidad=modelo.getRowCount();
        Calendar calendario = Calendar.getInstance();
        for(int i=0;i<cantidad;i++){
           String mesa= (String) modelo.getValueAt(i, 0);
           String plato= (String) modelo.getValueAt(i, 1);
           String estado= (String) modelo.getValueAt(i, 2);
           
           Captura captura = new Captura(plato, mesa, estado,String.valueOf(calendario.get(Calendar.HOUR_OF_DAY)));
           listado.add(captura);
        }
        return listado;
    }
    public static DefaultTableModel Mementos(Memento m){
        List<Captura>listado= m.getEstados();
        Object[][] filas = new Object[listado.size()][4];
        String[] columnas={"HORA","PLATO","MESA","ESTADO PLATO"};
        int i=0;
        for(Captura c:listado){
            filas[i][0]=c.getHora();
            filas[i][1]=c.getPlato();
            filas[i][2]=c.getMesa();
            filas[i][3]=c.getEstado();
            i++;
        }
        return new DefaultTableModel(filas,columnas){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; 
    }
    public static DefaultComboBoxModel CantidadMementos(int cantidad){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for(int i=0;i<cantidad;i++){
            modelo.addElement(i);
        }
        return modelo;
    } 
}
