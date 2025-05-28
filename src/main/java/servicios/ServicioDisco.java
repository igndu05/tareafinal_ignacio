package servicios;

import controladores.DiscoController;
import entidades.Artista;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ServicioDisco {
    private static final DiscoController dc = new DiscoController();
    
    public static void insertarArtistasEjemplo() {
        var lista = new ArrayList<Disco>();
        lista.add(new Dis);
        lista.add(new Artista(2, "hola2", Date.from(Instant.now())));
        lista.add(new Artista(3, "hola3", Date.from(Instant.now())));
        lista.add(new Artista(4, "hola4", Date.from(Instant.now())));
        lista.add(new Artista(5, "hola5", Date.from(Instant.now())));
        // Todos los clientes se guardan sin ventas
        for (Artista a : lista) {
            dc.create(a);
            
        }
        System.out.println("--- > Usuarios de ejemplo insertados ");
    }
    
    public static void mostrarTodosDiscos() {
        dc.findAll().forEach(System.out::println);

    }
    
    public static void borrarTodosDiscos(){
        dc.deleteAll();
    }
}
