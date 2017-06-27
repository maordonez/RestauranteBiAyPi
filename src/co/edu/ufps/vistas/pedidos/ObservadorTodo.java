/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.vistas.pedidos;

import co.edu.ufps.controlador.ControladorPedidos;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author miuguel
 */
public class ObservadorTodo extends Thread{
    private final ControladorPedidos controlador;
    private final JTable table;

    public ObservadorTodo(JTable tabla) {
        this.controlador = new ControladorPedidos();
        this.table = tabla;
    }

    @Override
    public void run() {
        try {
            while (true) {
                table.setModel(controlador.pedidosListado());
                sleep(4000);
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(ObservadorListo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
