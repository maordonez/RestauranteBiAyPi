/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.vistas.pedidos;

import co.edu.ufps.controlador.ControladorPedidos;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author miuguel
 */
public class Observador extends Thread {

    private final ControladorPedidos controlador;
    private final JTable table;
    private final TableColumnModel columnModel;

    public Observador(JTable tabla) {
        this.controlador = new ControladorPedidos();
        this.table = tabla;
        this.columnModel = this.table.getColumnModel();
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.setModel(controlador.pedidosCocinero());
                columnModel.getColumn(0).setPreferredWidth(40);
                columnModel.getColumn(1).setPreferredWidth(80);
                columnModel.getColumn(2).setPreferredWidth(80);
                columnModel.getColumn(3).setPreferredWidth(150);
                columnModel.getColumn(4).setPreferredWidth(80);
                columnModel.getColumn(5).setPreferredWidth(150);
                sleep(4000);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Observador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
