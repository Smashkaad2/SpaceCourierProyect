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
import com.edu.Javier.SpaceCourier.model.ProductoxPlaneta;
import com.edu.Javier.SpaceCourier.model.ProductoxNave;
import com.edu.Javier.SpaceCourier.repository.EstrellaRepository;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;
import com.edu.Javier.SpaceCourier.repository.NaveRepository;
import com.edu.Javier.SpaceCourier.repository.PlanetaRepository;
import com.edu.Javier.SpaceCourier.repository.ProductoRepository;
import com.edu.Javier.SpaceCourier.repository.ProductoxNaveRepository;
import com.edu.Javier.SpaceCourier.repository.ProductoxPlanetaRepository;

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
    @Autowired
    private ProductoxPlanetaRepository productoxPlanetaRepository;

    @Override
    public void run(String... args) throws Exception {

        if (naveRepository.count() == 0) {

            for (int i = 1; i <= 20; i++) {
                Nave nave = new Nave();
                nave.setShip_name("Nave" + i);
                nave.setVelocidadMax(100 + i);
                nave.setCapacidadCarga(500 + i);
                nave.setCoordenada_X(500 + i);
                nave.setCoordenada_y(500 + i);
                nave.setCoordenada_z(500 + i);
                nave.setCreditos(500 + i);
                naveRepository.save(nave);
            }
            System.out.println("Se han creado 20 naves.");
        } else {
            System.out.println("Ya existen naves en la base de datos.");
        }

        if (jugadorRepository.count() == 0) {

            List<Nave> navesDisponibles = naveRepository.findAll();
            Random random = new Random();
            int randValue = 0;

            for (int i = 1; i <= 100; i++) {
                Jugador jugador = new Jugador();
                jugador.setUsername("jugador" + i);
                jugador.setPassword("password" + i);
                randValue = random.nextInt(3);
                if (randValue == 0) {
                    jugador.setRol("capitan"); 
                }
                else if (randValue == 1) {
                    jugador.setRol("comerciante");
                }
                else 
                    jugador.setRol("piloto");
                
                Random rand = new Random();
                Nave naveAleatoria = navesDisponibles.get(rand.nextInt(navesDisponibles.size()));

                jugador.setNave(naveAleatoria);

                jugadorRepository.save(jugador);
            }
            System.out.println("Se han creado 100 jugadores.");
        } else {
            System.out.println("Ya existen jugadores en la base de datos.");
        }

        if (productoRepository.count() == 0) {

            Random random = new Random();
            int factorDemandaAleatorio = random.nextInt(1000000) + 1;
            int stockAleatorio = random.nextInt(1000000) + 1;

            for (int i = 1; i <= 30; i++) {
                Producto producto = new Producto();
                producto.setNombreProducto("Producto " + i);
                producto.setDescription("Descripción del Producto " + i);
                productoRepository.save(producto);
            }
            System.out.println("Se han creado 30 productos.");
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
                productoxNave.setNombreProducto(productoAleatorio.getNombreProducto());

                productoxNaveRepository.save(productoxNave);
            }
            System.out.println(
                    "Se han creado 500 instancias de ProductoxNave y se han relacionado con naves y productos aleatorios.");
        } else {
            System.out.println("Ya existen instancias de ProductoxNave en la base de datos.");
        }

        Random random = new Random();

        if (estrellaRepository.count() == 0) {

            for (int i = 1; i <= 20; i++) {

                Estrella estrella = new Estrella();
                estrella.setNombre_estrella("Estrella " + i);
                estrella.setCoordenada_X(random.nextInt(1000));
                estrella.setCoordenada_y(random.nextInt(1000));
                estrella.setCoordenada_z(random.nextInt(1000));
                estrellaRepository.save(estrella);
            }
            System.out.println("Se han creado 20 estrellas.");
        } else {
            System.out.println("Ya existen estrellas en la base de datos.");
        }

        if (planetaRepository.count() == 0) {

            List<Estrella> estrellasDisponibles = estrellaRepository.findAll();
            List<Producto> productosDisponibles = productoRepository.findAll();
            List<ProductoxPlaneta> productosxplanetasDisponibles = new ArrayList<>();

            for (int i = 1; i <= 100; i++) {
                Planeta planeta = new Planeta();
                planeta.setNombrePlaneta("Planeta " + i);
                planeta.setDescripcionPlaneta("Descripción del Planeta " + i);
                planetaRepository.save(planeta);

                Random rand = new Random();
                Estrella estrellaAleatoria = estrellasDisponibles.get(rand.nextInt(estrellasDisponibles.size()));

                planeta.setEstrella(estrellaAleatoria);

                for (int j = 0; j < 5; j++) {
                    ProductoxPlaneta nuevoProductoxPlaneta = new ProductoxPlaneta();
                    Producto productoAleatorio = productosDisponibles.get(random.nextInt(productosDisponibles.size()));
                    nuevoProductoxPlaneta.setProductoNombre(productoAleatorio.getNombreProducto());
                    nuevoProductoxPlaneta.setFactor_Demanda(random.nextInt(1000001));
                    nuevoProductoxPlaneta.setFactorOferta(random.nextInt(1000001));
                    nuevoProductoxPlaneta.setStock(random.nextInt(1000001));
                    nuevoProductoxPlaneta.setProductoPlaneta(productoAleatorio);
                    nuevoProductoxPlaneta.setPlanetaProducto(planeta);
                    productosxplanetasDisponibles.add(nuevoProductoxPlaneta);
                    productoxPlanetaRepository.save(nuevoProductoxPlaneta);

                }

                planetaRepository.save(planeta);
            }
            System.out.println("Se han creado 100 planetas.");
        } else {
            System.out.println("Ya existen planetas en la base de datos.");
        }

        // Nave n1 = new Nave(null, "JavierShip", 300, 100, new ArrayList<Jugador>(),
        // new ArrayList<ProductoxNave>());
        // naveRepository.save(n1);

        // Nave n2 = new Nave(null, "GordoShip", 300, 100, new ArrayList<Jugador>(), new
        // ArrayList<ProductoxNave>());
        // naveRepository.save(n2);

        // Nave n3 = new Nave(null, "NaveFantasma", 300, 100, new ArrayList<Jugador>(),
        // new ArrayList<ProductoxNave>());
        // naveRepository.save(n3);

        // Producto pr1 = new Producto(null, "placeholder1", "producto place holder",
        // new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr1);
        // Producto pr2 = new Producto(null, "placeholder2", "producto place holder",
        // new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr2);
        // Producto pr3 = new Producto(null, "placeholder3", "producto place holder",
        // new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr3);
        // Producto pr4 = new Producto(null, "placeholder4", "producto place holder",
        // new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr4);
        // Producto pr5 = new Producto(null, "placeholder5", "producto place holder",
        // new ArrayList<ProductoxNave>(), new ArrayList<ProductoxEstrella>());
        // productoRepository.save(pr5);

        // ProductoxNave prxn1 = new ProductoxNave(null, 10,pr1,n1);
        // productoxNaveRepository.save(prxn1);
        // ProductoxNave prxn2 = new ProductoxNave(null, 10,pr2,n1);
        // productoxNaveRepository.save(prxn2);
        // ProductoxNave prxn3 = new ProductoxNave(null, 10,pr2,n2);
        // productoxNaveRepository.save(prxn3);
        // ProductoxNave prxn4 = new ProductoxNave(null, 10,pr1,n2);
        // productoxNaveRepository.save(prxn4);

        // Estrella e1 = new Estrella(null, "EstrellaCool", 20, 10, 40, new
        // ArrayList<Planeta>(), new ArrayList<ProductoxEstrella>());
        // estrellaRepository.save(e1);

        // Estrella e2 = new Estrella(null, "Destello", 20, 10, 40, new
        // ArrayList<Planeta>(), new ArrayList<ProductoxEstrella>());
        // estrellaRepository.save(e2);

        // // Jugador p1 = new Jugador(null, "Kaad", "Password", "Captain", n1);
        // // jugadorRepository.save(p1);
        // // Jugador p2 = new Jugador(null, "Uzeche", "Password", "Trooper", n2);
        // // jugadorRepository.save(p2);
        // // Jugador p3 = new Jugador(null, "TropiFAT", "Password", "Trooper", n2);
        // // jugadorRepository.save(p3);
        // // Jugador p4 = new Jugador(null, "YoryoBOT", "Password", "Trooper", n1);
        // // jugadorRepository.save(p4);

        // Planeta pla1 = new Planeta(null, "Planeta Vegeta", "Muh buenas a todos
        // waaaaaapisimos", e1);
        // planetaRepository.save(pla1);

        Nave n3 = new Nave(null, "NaveFantasma", 300, 100, 0, 0, 0, 0, new ArrayList<Jugador>(),
                new ArrayList<ProductoxNave>());
        naveRepository.save(n3);

    }

}
