package daw;

import controladores.UsuarioController;
import servicios.ServicioArtista;
import servicios.ServicioUsuario;

public class Main {
    private static final UsuarioController uc = new UsuarioController();
    
    public static void main(String[] args) {
        prepararBaseDatos();
        System.out.println("Clientes en la base de datos ----------- ");
        ServicioUsuario.mostrarTodosUsuarios();
        ServicioArtista.mostrarTodosArtistas();
    }
    
    private static void borrarTodo() {

        ServicioUsuario.borrarTodosUsuarios();
        
        System.out
                .println("Se han borrado todos los usuarios del sistema e inicializado las claves primarias de todas las tablas");
        
        ServicioArtista.borrarTodosArtistas();
        
        System.out
                .println("Se han borrado todos los artistas del sistema e inicializado las claves primarias de todas las tablas");
    }

    // Se borra todo, reinicia pk e inserta datos de ejemplo
    // de productos y clientes
    private static void prepararBaseDatos() {
        borrarTodo();
        ServicioUsuario.insertarUsuariosEjemplo();
        ServicioArtista.insertarArtistasEjemplo();
    }
}