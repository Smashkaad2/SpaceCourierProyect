package com.edu.Javier.SpaceCourier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JugadorRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    JugadorRepository jugadorRepository;

    @Autowired
    TestRestTemplate rest;

    @Test
    void jugadorunoporUnoId() {
        String url = "http://localhost:" + port + "/api/player/1";
        Jugador jugador = rest.getForObject(url, Jugador.class);

        assertEquals(1L, jugador.getId());
        assertEquals("jugador1", jugador.getUsername());
        assertEquals("password1", jugador.getPassword());
        assertEquals("jugador", jugador.getRol());

    }

    @Test
    void borrarJugador() {
        String url = "http://localhost:" + port + "/api/player/1";
        Jugador jugador = rest.getForObject(url, Jugador.class);

        String urlDel = "http://localhost:" + port + "/api/player/" + jugador.getId();
        rest.delete(urlDel);

        assertFalse(jugadorRepository.existsById(jugador.getId()));
    }

    @Test
    void crearJugador() {

        Jugador nuevoJugador = new Jugador();
        nuevoJugador.setUsername("nuevoJugador");
        nuevoJugador.setPassword("password123");
        nuevoJugador.setRol("jugador");

        String url = "http://localhost:" + port + "/api/player";
        ResponseEntity<Void> responseEntity = rest.postForEntity(url, nuevoJugador, Void.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        List<Jugador> jugadores = jugadorRepository.findAll();
        Optional<Jugador> jugadorCreadoOptional = jugadores.stream()
                .filter(jugador -> jugador.getUsername().equals("nuevoJugador"))
                .findFirst();

        assertTrue(jugadorCreadoOptional.isPresent());

        Jugador jugadorCreado = jugadorCreadoOptional.get();
        assertEquals("password123", jugadorCreado.getPassword());
        assertEquals("jugador", jugadorCreado.getRol());
    }

    @Test
    void actualizarJugador() {

        String url = "http://localhost:" + port + "/api/player/1";
        Jugador jugadorExistente = rest.getForObject(url, Jugador.class);

        Jugador jugadorActualizado = new Jugador();
        jugadorActualizado.setUsername("jugadorActualizado");
        jugadorActualizado.setPassword("updatedPassword");
        jugadorActualizado.setRol("admin");

        String urlUpdate = "http://localhost:" + port + "/api/player/" + jugadorExistente.getId();
        rest.put(urlUpdate, jugadorActualizado);

        Optional<Jugador> jugadorActualizadoOptional = jugadorRepository.findById(jugadorExistente.getId());
        assertTrue(jugadorActualizadoOptional.isPresent());

        Jugador jugadorEnBD = jugadorActualizadoOptional.get();
        assertEquals("jugadorActualizado", jugadorEnBD.getUsername());
        assertEquals("updatedPassword", jugadorEnBD.getPassword());
        assertEquals("admin", jugadorEnBD.getRol());
    }

    @Test
    void listarJugadores() {

        String urlList = "http://localhost:" + port + "/api/player/list";
        ResponseEntity<Jugador[]> responseEntity = rest.getForEntity(urlList, Jugador[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Jugador[] jugadores = responseEntity.getBody();

        assertNotNull(jugadores);
        assertEquals(100, jugadores.length);
    
    }

}
