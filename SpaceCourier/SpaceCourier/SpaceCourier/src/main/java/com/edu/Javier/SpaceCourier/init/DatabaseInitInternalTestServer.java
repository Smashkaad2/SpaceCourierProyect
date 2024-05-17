package com.edu.Javier.SpaceCourier.init;


import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;


import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
// https://www.baeldung.com/spring-junit-prevent-runner-beans-testing-execution
// https://www.baeldung.com/spring-profiles

// mvn  spring-boot:run -Dspring-boot.run.profiles=internal-test-server
@Profile({"internal-test-server"})
public class DatabaseInitInternalTestServer implements CommandLineRunner {
    @Autowired
    JugadorRepository jugadorRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        jugadorRepository.save(new Jugador( null, "Javier","Password","Player",new Nave()));
        jugadorRepository.save(new Jugador(null, "Paula","Password","Player",new Nave()));
        jugadorRepository.save(new Jugador(null, "Gordo","Password","Player",new Nave()));
    }
    
}
