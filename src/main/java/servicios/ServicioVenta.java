/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import controladores.DiscoController;
import controladores.UsuarioController;
import controladores.VentaController;
import entidades.DetalleVenta;
import entidades.Disco;
import entidades.Usuario;
import entidades.Venta;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 *
 * @author ignacio
 */
public class ServicioVenta {
    private static final VentaController vc = new VentaController();
    private static final UsuarioController uc = new UsuarioController();
    private static final DiscoController dc = new DiscoController();

    public static void insertarVentaEjemplo(){
        // Busco el cliente de id 1
        Usuario usuario = uc.findById(1);
        System.out.println("Insertando una venta para " + 
                usuario.getNombreUsuario() + " " + usuario.getDniUsuario());
        
        // Creo el objeto Venta con la fecha actual
        // y el cliente que la realiza
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        Instant ahora = fechaHoraActual.toInstant(ZoneOffset.UTC);
        Venta venta = new Venta(Date.from(ahora), usuario);
               
        // Se crean los objetos detalle venta con los productos y cantidades cualesquiera
        DetalleVenta detalle = new DetalleVenta();
        Disco d1 = dc.findById(1);
        detalle.setCodDisco(d1);
        detalle.setCantidad(12);

        // Añade el detalle a la venta y la venta al detalle
        venta.addDetalleVenta(detalle);
        
        // Se crea otro detalle 
        Disco d2 = dc.findById(2);
        detalle = new DetalleVenta();
        detalle.setCodDisco(d2);
        detalle.setCantidad(120);
        
        // Añade el detalle a la venta y la venta al detalle
        venta.addDetalleVenta(detalle);

        // Añado la venta al cliente y a venta le indico que es de este cliente
        usuario.addVenta(venta);
        

        // Persisto la venta, que en cascada persistirá los detalleVenta
        vc.create(venta);

        // Actualizo el cliente con su nueva venta en el contexto de persistencia
        uc.update(usuario);
        
    }
    
    public static void borrarTodasVentas(){
        vc.deleteAll();
    }
    
    public static void mostrarTodasVentas() {
        vc.findAll().forEach(System.out::println);

    }
}
