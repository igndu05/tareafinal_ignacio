package servicios;

import controladores.ArtistaController;
import entidades.Artista;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class ServicioArtista {
    private static final ArtistaController ac = new ArtistaController();
    
    public static void insertarArtistasEjemplo() {
        var lista = new ArrayList<Artista>();
        lista.add(new Artista(1, "hola", Date.from(Instant.now())));
        lista.add(new Artista(2, "hola2", Date.from(Instant.now())));
        lista.add(new Artista(3, "hola3", Date.from(Instant.now())));
        lista.add(new Artista(4, "hola4", Date.from(Instant.now())));
        lista.add(new Artista(5, "hola5", Date.from(Instant.now())));
        // Todos los clientes se guardan sin ventas
        for (Artista a : lista) {
            ac.create(a);
            
        }
        System.out.println("--- > Usuarios de ejemplo insertados ");
    }
    
    public static void mostrarTodosArtistas() {
        ac.findAll().forEach(System.out::println);

    }
    
    public static void borrarTodosArtistas(){
        ac.deleteAll();
    }
    
}
