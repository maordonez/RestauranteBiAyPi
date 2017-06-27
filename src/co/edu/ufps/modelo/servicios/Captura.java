/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.servicios;

/**
 *
 * @author miuguel
 */
public class Captura {
    private String plato;
    private String mesa;
    private String estado;
    private String hora;

    public Captura(String plato, String mesa, String estado, String hora) {
        this.plato = plato;
        this.mesa = mesa;
        this.estado = estado;
        this.hora = hora;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String plato) {
        this.plato = plato;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    
}
