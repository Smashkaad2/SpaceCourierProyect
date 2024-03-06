package com.edu.Javier.SpaceCourier.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.edu.Javier.SpaceCourier.model.Estrella;
import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.model.Producto;
import com.edu.Javier.SpaceCourier.model.ProductoxEstrella;
import com.edu.Javier.SpaceCourier.model.ProductoxNave;
import com.edu.Javier.SpaceCourier.repository.EstrellaRepository;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;
import com.edu.Javier.SpaceCourier.repository.NaveRepository;
import com.edu.Javier.SpaceCourier.repository.PlanetaRepository;
import com.edu.Javier.SpaceCourier.repository.ProductoRepository;
import com.edu.Javier.SpaceCourier.repository.ProductoxNaveRepository;


@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private NaveRepository naveRepository;
    @Autowired
    private EstrellaRepository estrellaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoxNaveRepository productoxNaveRepository;
    @Autowired
    private PlanetaRepository planetaRepository;


    @Override
    public void run(String... args) throws Exception {

        

        if (naveRepository.count() == 0) {
        
            for (int i = 1; i <= 20; i++) {
                Nave nave = new Nave();
                nave.setShip_name("Nave" + i);
                nave.setVelocidadMax(100 + i);
                nave.setCapacidadCarga(500 + i);
                naveRepository.save(nave);
            }
            System.out.println("Se han creado 20 naves.");
        } else {
            System.out.println("Ya existen naves en la base de datos.");
        }

        if (jugadorRepository.count() == 0) {

            List<Nave> navesDisponibles = naveRepository.findAll();

            for (int i = 1; i <= 100; i++) {
                Jugador jugador = new Jugador();
                jugador.setUsername("jugador" + i);
                jugador.setPassword("password" + i);
                jugador.setRol("jugador");

                Random rand = new Random();
                Nave naveAleatoria = navesDisponibles.get(rand.nextInt(navesDisponibles.size()));

                jugador.setNave(naveAleatoria);
                
                jugadorRepository.save(jugador);
            }
            System.out.println("Se han creado 100 jugadores.");
        } else {
            System.out.println("Ya existen jugadores en la base de datos.");
        }

        Nave n3 = new Nave(null, "NaveFantasma", 300, 100, new ArrayList<Jugador>(), new ArrayList<ProductoxNave>());
        naveRepository.save(n3);

        if (productoRepository.count() == 0) {
        
            for (int i = 1; i <= 10; i++) {
                Producto producto = new Producto();
                producto.setNombreProducto("Producto " + i);
                producto.setDescription("Descripción del Producto " + i);
                productoRepository.save(producto);
            }
            System.out.println("Se han creado 10 productos.");
        } else {
            System.out.println("Ya existen productos en la base de datos.");
        }

        if (productoxNaveRepository.count() == 0) {

            List<Nave> navesDisponibles = naveRepository.findAll();
            List<Producto> productosDisponibles = productoRepository.findAll();

            Random rand = new Random();
            for (int i = 0; i < 500; i++) {
                ProductoxNave productoxNave = new ProductoxNave();
                productoxNave.setCantidad(rand.nextInt(100) + 1); 

                Producto productoAleatorio = productosDisponibles.get(rand.nextInt(productosDisponibles.size()));
                Nave naveAleatoria = navesDisponibles.get(rand.nextInt(navesDisponibles.size()));

                productoxNave.setProducto(productoAleatorio);
                productoxNave.setNaveProd(naveAleatoria);

                productoxNaveRepository.save(productoxNave);
            }
            System.out.println("Se han creado 500 instancias de ProductoxNave y se han relacionado con naves y productos aleatorios.");
        } else {
            System.out.println("Ya existen instancias de ProductoxNave en la base de datos.");
        }


        if (estrellaRepository.count() == 0) {
            
            for (int i = 1; i <= 10; i++) {
                Estrella estrella = new Estrella();
                estrella.setNombre_estrella("Estrella " + i);
                estrella.setCoordenada_X(i * 100);
                estrella.setCoordenada_y(i * 200);
                estrella.setCoordenada_z(i * 300);
                // No se están inicializando las listas de planetas y productos ya que no se especificó en el modelo
                estrellaRepository.save(estrella);
            }
            System.out.println("Se han creado 10 estrellas.");
        } else {
            System.out.println("Ya existen estrellas en la base de datos.");
        }





        if (planetaRepository.count() == 0) {

            List<Estrella> estrellasDisponibles = estrellaRepository.findAll();

            for (int i = 1; i <= 10; i++) {
                Planeta planeta = new Planeta();
                planeta.setNombrePlaneta("Planeta " + i);
                planeta.setDescripcionPlaneta("Descripción del Planeta " + i);
                planetaRepository.save(planeta);

                Random rand = new Random();
                Estrella estrellaAleatoria = estrellasDisponibles.get(rand.nextInt(estrellasDisponibles.size()));

                planeta.setEstrella(estrellaAleatoria);
                planetaRepository.save(planeta);
            }
            System.out.println("Se han creado 10 planetas.");
        } else {
            System.out.println("Ya existen planetas en la base de datos.");
        }

        

        // Nave n1 = new Nave(null, "JavierShip", 300, 100, new ArrayList<Jugador>(), new ArrayList<ProductoxNave>());
        // naveRepository.save(n1);

        // Nave n2 = new Nave(null, "GordoShip", 300, 100, new ArrayList<Jugador>(), new ArrayList<ProductoxNave>());
        // naveRepository.save(n2);

        // Nave n3 = new Nave(null, "NaveFantasma", 300, 100, new ArrayList<Jugador>(), new ArrayList<ProductoxNave>());
        // naveRepository.save(n3);

        // Producto pr1 = new Producto(null, "placeholder1", "producto place holder", new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr1);
        // Producto pr2 = new Producto(null, "placeholder2", "producto place holder", new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr2);
        // Producto pr3 = new Producto(null, "placeholder3", "producto place holder", new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr3);
        // Producto pr4 = new Producto(null, "placeholder4", "producto place holder", new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr4);
        // Producto pr5 = new Producto(null, "placeholder5", "producto place holder", new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr5);

        // ProductoxNave prxn1 = new ProductoxNave(null, 10,pr1,n1);
        // productoxNaveRepository.save(prxn1);
        // ProductoxNave prxn2 = new ProductoxNave(null, 10,pr2,n1);
        // productoxNaveRepository.save(prxn2);
        // ProductoxNave prxn3 = new ProductoxNave(null, 10,pr2,n2);
        // productoxNaveRepository.save(prxn3);
        // ProductoxNave prxn4 = new ProductoxNave(null, 10,pr1,n2);
        // productoxNaveRepository.save(prxn4);

        // Estrella e1 = new Estrella(null, "EstrellaCool", 20, 10, 40, new ArrayList<Planeta>(), new ArrayList<ProductoxEstrella>());
        // estrellaRepository.save(e1);

        // Estrella e2 = new Estrella(null, "Destello", 20, 10, 40, new ArrayList<Planeta>(), new ArrayList<ProductoxEstrella>());
        // estrellaRepository.save(e2);

        // // Jugador p1 = new Jugador(null, "Kaad", "Password", "Captain", n1);
        // // jugadorRepository.save(p1);
        // // Jugador p2 = new Jugador(null, "Uzeche", "Password", "Trooper", n2);
        // // jugadorRepository.save(p2);
        // // Jugador p3 = new Jugador(null, "TropiFAT", "Password", "Trooper", n2);
        // // jugadorRepository.save(p3);
        // // Jugador p4 = new Jugador(null, "YoryoBOT", "Password", "Trooper", n1);
        // // jugadorRepository.save(p4);

        // Planeta pla1 = new Planeta(null, "Planeta Vegeta", "Muh buenas a todos waaaaaapisimos", e1);
        // planetaRepository.save(pla1);


        
    }

}
