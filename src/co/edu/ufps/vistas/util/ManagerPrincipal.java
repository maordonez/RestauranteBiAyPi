/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.vistas.util;

import co.edu.ufps.controlador.ControladorPedidos;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author miuguel
 */
public class ManagerPrincipal {

    private static JDesktopPane contenedorVentanas;
    private static ControladorPedidos controlador;

    public static void setDesktopPane(JDesktopPane dp) {
        contenedorVentanas = dp;
    }
    public static ControladorPedidos getControlador(){
        if(controlador==null){
            controlador= new ControladorPedidos();
        }
        return controlador;
    }
    
    public static void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(contenedorVentanas, mensaje,"Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
    }
    
   

    public static void agregar(JInternalFrame frm) {
        JInternalFrame ventana = buscar(frm.getName());
        if (ventana == null) {
            contenedorVentanas.add(frm.getName(), frm);
            ((javax.swing.plaf.basic.BasicInternalFrameUI)frm.getUI()).setNorthPane(null);
            frm.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
            frm.setResizable(false);
            frm.setClosable(false);
            frm.setVisible(true);
            frm.toFront();
        } else {
            ventana.setVisible(true);
            ventana.toFront();
        }
        

    }

    private static JInternalFrame buscar(String name) {
        JInternalFrame[] ventanas = contenedorVentanas.getAllFrames();
        for (JInternalFrame ventana : ventanas) {
            if (ventana.getName().equals(name)) {
                return ventana;
            }
        }
        return null;
    }

}
