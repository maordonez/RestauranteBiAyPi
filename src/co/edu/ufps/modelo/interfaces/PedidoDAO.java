/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.ufps.modelo.interfaces;

import co.edu.ufps.modelo.entidad.Pedido;
import java.util.List;
import java.util.Map;

/**
 *
 * @author miuguel
 */
public interface PedidoDAO {
    public List<Pedido> findBy(Map<String,Object> filtro);
    public List<Pedido> findAll();
    public Pedido find(int valor);
    public boolean registrar(Pedido pedido);
    public boolean actualizar(Pedido pedido);
}
