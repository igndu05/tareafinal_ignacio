package servicios;

import controladores.DiscoController;
import entidades.Disco;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ServicioDisco {
    private static final DiscoController dc = new DiscoController();
    
    public static void insertarDiscosEjemplo() {
        var lista = new ArrayList<Disco>();
        lista.add(new Disco(1, "Disco 1", Date.from(Instant.now()), 7));
        lista.add(new Disco(2, "Disco 2", Date.from(Instant.now()), 10));
        lista.add(new Disco(3, "Disco 3", Date.from(Instant.now()), 3));
        lista.add(new Disco(4, "Disco 4", Date.from(Instant.now()), 6));
        lista.add(new Disco(5, "Disco 5", Date.from(Instant.now()), 2));
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
