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
    
   
    public static void mostrarTodosDiscos() {
        dc.findAll().forEach(System.out::println);

    }
    
    public static void borrarTodosDiscos(){
        dc.deleteAll();
    }
}
