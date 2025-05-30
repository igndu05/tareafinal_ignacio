package servicios;

import controladores.UsuarioController;
import entidades.Usuario;
import java.util.ArrayList;

public class ServicioUsuario {
    private static final UsuarioController uc = new UsuarioController();

    public static void insertarUsuariosEjemplo() {
        var lista = new ArrayList<Usuario>();
        
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
