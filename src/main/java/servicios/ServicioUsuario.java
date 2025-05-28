package servicios;

import controladores.UsuarioController;
import entidades.Usuario;
import java.util.ArrayList;

public class ServicioUsuario {
    private static final UsuarioController uc = new UsuarioController();

    public static void insertarUsuariosEjemplo() {
        var lista = new ArrayList<Usuario>();
        lista.add(new Usuario(1, "75950015J", "La Linea", "677 29 34 41"));
        lista.add(new Usuario(2, "75950015H", "La Linea", "677 29 34 42"));
        lista.add(new Usuario(3, "75950015K", "La Linea", "677 29 34 43"));
        lista.add(new Usuario(4, "75950015L", "La Linea", "677 29 34 44"));
        lista.add(new Usuario(5, "75950015M", "La Linea", "677 29 34 45"));
        // Todos los clientes se guardan sin ventas
        for (Usuario u : lista) {
            uc.create(u);
            
        }
        System.out.println("--- > Usuarios de ejemplo insertados ");
    }

    public static void mostrarTodosUsuarios() {
        uc.findAll().forEach(System.out::println);
        System.out.println("-------------------------------------------------");
    }
    
    public static void borrarTodosUsuarios(){
        uc.deleteAll();
    }
}
