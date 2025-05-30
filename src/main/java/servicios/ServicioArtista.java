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
