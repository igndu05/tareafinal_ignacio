package servicios;

import controladores.DiscoController;
import entidades.Disco;
import java.util.ArrayList;

public class ServicioDisco {
    private static final DiscoController dc = new DiscoController();
    
    public static void insertarDiscosEjemplo() {
        var lista = new ArrayList<Disco>();
        
        // Todos los clientes se guardan sin ventas
        for (Disco d : lista) {
            dc.create(d);
            
        }
        System.out.println("--- > Discos de ejemplo insertados ");
    }
    
    public static void mostrarTodosDiscos() {
        dc.findAll().forEach(System.out::println);

    }
    
    public static void borrarTodosDiscos(){
        dc.deleteAll();
    }
}
