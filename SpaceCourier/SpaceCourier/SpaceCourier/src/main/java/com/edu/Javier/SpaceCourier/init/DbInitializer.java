package com.edu.Javier.SpaceCourier.init;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.edu.Javier.SpaceCourier.model.Estrella;
import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.model.ProductoxEstrella;
import com.edu.Javier.SpaceCourier.model.ProductoxNave;
import com.edu.Javier.SpaceCourier.repository.EstrellaRepository;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;
import com.edu.Javier.SpaceCourier.repository.NaveRepository;


@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private JugadorRepository jugadorRepository;
    @Autowired
    private NaveRepository naveRepository;
    @Autowired
    private EstrellaRepository estrellaRepository;


    @Override
    public void run(String... args) throws Exception {

        Nave n1 = new Nave(null, "JavierShip", 300, 100, new ArrayList<Jugador>(), new ArrayList<ProductoxNave>());
        naveRepository.save(n1);

        Nave n2 = new Nave(null, "GordoShip", 300, 100, new ArrayList<Jugador>(), new ArrayList<ProductoxNave>());
        naveRepository.save(n2);

        Estrella e1 = new Estrella(null, "EstrellaCool", 20, 10, 40, new ArrayList<Planeta>(), new ArrayList<ProductoxEstrella>());
        estrellaRepository.save(e1);

        Estrella e2 = new Estrella(null, "Destello", 20, 10, 40, new ArrayList<Planeta>(), new ArrayList<ProductoxEstrella>());
        estrellaRepository.save(e2);

        Jugador p1 = new Jugador(null, "Kaad", "Password", "Captain", n1);
        jugadorRepository.save(p1);
        Jugador p2 = new Jugador(null, "Uzeche", "Password", "Trooper", n2);
        jugadorRepository.save(p2);
        Jugador p3 = new Jugador(null, "TropiFAT", "Password", "Trooper", n2);
        jugadorRepository.save(p3);
        Jugador p4 = new Jugador(null, "YoryoBOT", "Password", "Trooper", n1);
        jugadorRepository.save(p4);

        
    }

}
