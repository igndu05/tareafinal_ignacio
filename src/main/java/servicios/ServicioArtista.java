package servicios;

import controladores.ArtistaController;
import entidades.Artista;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class ServicioArtista {
    private static final ArtistaController ac = new ArtistaController();
    
    public static void insertarArtistasEjemplo() {
        var lista = new ArrayList<Artista>();
        
        lista.add(new Artista(1, "Artista 1", Date.valueOf(LocalDate.of(2025, Month.FEBRUARY, 13))));
        lista.add(new Artista(2, "Artista 2", Date.valueOf(LocalDate.of(2025, Month.FEBRUARY, 14))));
        lista.add(new Artista(3, "Artista 3", Date.valueOf(LocalDate.of(2025, Month.FEBRUARY, 15))));
        lista.add(new Artista(4, "Artista 4", Date.valueOf(LocalDate.of(2025, Month.FEBRUARY, 16))));
        lista.add(new Artista(5, "Artista 5", Date.valueOf(LocalDate.of(2025, Month.FEBRUARY, 17))));
        
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
