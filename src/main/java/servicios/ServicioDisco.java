package servicios;

import controladores.ArtistaController;
import controladores.DiscoController;
import entidades.Artista;
import entidades.Disco;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class ServicioDisco {
    private static final DiscoController dc = new DiscoController();
    
    public static void insertarDiscosEjemplo() {
        var lista = new ArrayList<Disco>();
        
        lista.add(new Disco("Los chichos", Date.valueOf(LocalDate.of(1998, Month.APRIL, 15)), ArtistaController.findById(1)));
        lista.add(new Disco("Los chichos2", Date.valueOf(LocalDate.of(1999, Month.APRIL, 15)), null));
        lista.add(new Disco("Los chichos3", Date.valueOf(LocalDate.of(2000, Month.APRIL, 15)), null));
        lista.add(new Disco("Los chichos4", Date.valueOf(LocalDate.of(2001, Month.APRIL, 15)), null));
        lista.add(new Disco("Los chichos5", Date.valueOf(LocalDate.of(2002, Month.APRIL, 15)), null));

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
