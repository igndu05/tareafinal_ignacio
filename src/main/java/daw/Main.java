package daw;

import controladores.DetalleVentaController;
import controladores.UsuarioController;
import controladores.VentaController;
import entidades.DetalleVenta;
import entidades.Usuario;
import entidades.Venta;
import java.time.LocalDateTime;
import servicios.ServicioDetalleVenta;
import servicios.ServicioDisco;
import servicios.ServicioUsuario;
import servicios.ServicioVenta;

public class Main {
    // Lo ideal sería crear un servicio para cada controlador, pero
    // para este ejercicio no es necesario. Para simplificar el código usamos
    // directamente los controladores
    private static final UsuarioController uc = new UsuarioController();
    // private static final ProductoController pc = new ProductoController(); // No se usa en este main
    private static final VentaController vc = new VentaController();
    private static final DetalleVentaController dvc = new DetalleVentaController();

    public static void main(String[] args) {

        // Preparamos la base de datos. En cada nueva ejecución se borran los
        // datos que hubiera, para facilitar la depuración
        prepararBaseDatos();

        System.out.println("Clientes en la base de datos ----------- ");
        ServicioUsuario.mostrarTodosUsuarios();
        System.out.println("Productos en la base de datos ----------- ");
        ServicioDisco.mostrarTodosDiscos();
        System.out.println("Ventas en la base de datos ----------- ");
        ServicioVenta.mostrarTodasVentas();

        // Insertamos una venta de ejemplo -----------------------------------------
        ServicioVenta.insertarVentaEjemplo();
        // Se muestran los datos de la base de datos
        System.out.println("Clientes en la base de datos con sus ventas ----------- ");
        ServicioUsuario.mostrarTodosUsuarios();


        // Modificar el cliente con id 2 -------------------------------------------
        // Se busca el cliente por su id. Se modifica el nombre del cliente
        Usuario usuario = uc.findById(2);
        usuario.setNombreUsuario("Alberto");
        uc.update(usuario);
        // Se muestran los datos de la base de datos
        System.out.println("Clientes en la base de datos con nombre modificado ----------- ");
        ServicioUsuario.mostrarTodosUsuarios();


        // Modificar la fecha de la venta 1 del cliente 1 -------------------------
        // Se busca el cliente por su id
        Usuario usuarioModificar = uc.findById(1);
        // Obtiene la venta del cliente a modificar, en este caso la primera venta, como
        // ejemplo
        Venta venta = usuarioModificar.getVentaCollection().stream().toList().get(0);
        // Se modifica la fecha de la venta
        // Crear un LocalDateTime con la fecha específica que queremos poner (1 de enero
        // de 2000, 12:00:00 UTC)
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 12, 0, 0);
        // Actualiza la fecha de la venta
        venta.setFechaVenta(localDateTime);
        // Actualiza la venta en la base de datos
        vc.update(venta);
        // Se muestran los datos de la base de datos
        System.out.println("Clientes en la base de datos con fecha de venta modificada ----------- ");
        ServicioUsuario.mostrarTodosUsuarios();


        // Modificar la cantidad de producto del primer detalle de la venta 1 del
        // cliente 1 -------------------------
        // Se busca el cliente por su id
        Usuario usuarioModificar2 = uc.findById(1);
        // Se busca la venta del cliente a modificar, en este caso la primera venta, a
        // modo de ejemplo
        Venta venta2 = usuarioModificar2.getVentaCollection().stream().toList().get(0);
        // Se busca el detalle de la venta a modificar, en este caso el primer detalle,
        // a modo de ejemplo
        DetalleVenta detalle = venta2.getDetalleVentaCollection().stream().toList().get(0);
        // Se modifica la cantidad del detalle
        detalle.setCantidad(100000);
        // Actualiza el detalle en la base de datos
        dvc.update(detalle);
        // Se muestran los datos de la base de datos
        System.out.println("Clientes en la base de datos con detalle venta modificado ----------- ");
        ServicioUsuario.mostrarTodosUsuarios();

        // Eliminar el primer detalle de la venta 1 del cliente 1  -------------------------
        // Se busca el cliente por su id
        Usuario usuarioModificar3 = uc.findById(1);
        // Se busca la venta del cliente a modificar, en este caso la primera venta
        Venta venta3 = usuarioModificar3.getVentaCollection().stream().toList().get(0);
        // Se busca el detalle de la venta a eliminar, en este caso el primer detalle
        // a modo de ejemplo
        DetalleVenta detalle2 = venta3.getDetalleVentaCollection().stream().toList().get(0);
        // Se elimina el detalle en la venta para mantener la relación bidireccional
        venta3.removeDetalleVenta(detalle2);
        // Se elimina el detalle de la base de datos
        dvc.delete(detalle2.getCodDetalleVenta());
        // Se actualiza la venta en la base de datos
        vc.update(venta3);
        // Se muestran los datos de la base de datos
        System.out.println("Clientes en la base de datos con detalle venta eliminado ----------- ");
        ServicioUsuario.mostrarTodosUsuarios();
    }

    private static void borrarTodo() {
        // Empieza borrando los detalles venta, que contienen las claves foráneas
        // producto y venta
        ServicioDetalleVenta.borrarTodosDetallesVentas();
        // Sigue borrando las ventas, que contiene clave foránea a cliente
        ServicioVenta.borrarTodasVentas();
        // Ahora que no hay claves foráneas se pueden borrar clientes y productos
        ServicioUsuario.borrarTodosUsuarios();
        ServicioDisco.borrarTodosDiscos();
        System.out
                .println("Se han borrado todos los registros e inicializado las claves primarias de todas las tablas");
    }

    // Se borra todo, reinicia pk e inserta datos de ejemplo
    // de productos y clientes
    private static void prepararBaseDatos() {
        borrarTodo();
        ServicioUsuario.insertarUsuariosEjemplo();
        ServicioDisco.insertarDiscosEjemplo();
    }
}