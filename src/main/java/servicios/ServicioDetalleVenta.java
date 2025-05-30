/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import controladores.DetalleVentaController;

/**
 *
 * @author ignacio
 */
public class ServicioDetalleVenta {
    private static final DetalleVentaController dvc = new DetalleVentaController();
    
    public static void borrarTodosDetallesVentas(){
        dvc.deleteAll();
    }
    
    public static void mostrarTodosDetallesVenta() {
        dvc.findAll().forEach(System.out::println);

    }
}
