package com.edu.Javier.SpaceCourier.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;


@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private JugadorRepository jugadorRepository;


    @Override
    public void run(String... args) throws Exception {
        Jugador p1 = new Jugador(null, "Kaad", "Password", "Captain", null);
        jugadorRepository.save(p1);
    }

}
