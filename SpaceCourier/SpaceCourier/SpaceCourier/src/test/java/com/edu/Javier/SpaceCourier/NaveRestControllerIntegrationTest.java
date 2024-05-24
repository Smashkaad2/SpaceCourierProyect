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
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;
import com.edu.Javier.SpaceCourier.repository.NaveRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NaveRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    NaveRepository naveRepository;

    @Autowired
    TestRestTemplate rest;

    @Test
    void jugadorunoporUnoId() {
        String url = "http://localhost:" + port + "/api/ship/view/1";
        Nave nave = rest.getForObject(url, Nave.class);

        assertEquals(1L, nave.getId());
        assertEquals("Nave1", nave.getShip_name());
        assertEquals(101, nave.getVelocidadMax());
        assertEquals(501, nave.getCapacidadCarga());
        assertEquals(501, nave.getCoordenada_X());
        assertEquals(501, nave.getCoordenada_y());
        assertEquals(501, nave.getCoordenada_z());
        assertEquals(501.0, nave.getCreditos());

    }

    @Test
    void borrarNave() {
        String url = "http://localhost:" + port + "/api/ship/view/21";
        Nave nave = rest.getForObject(url, Nave.class);

        String urlDel = "http://localhost:" + port + "/api/ship/delete/" + nave.getId();
        rest.delete(urlDel);

        assertFalse(naveRepository.existsById(nave.getId()));
    }

    @Test
    void editarNave() {
        String url = "http://localhost:" + port + "/api/ship/view/2";
        Nave naveExistente = rest.getForObject(url, Nave.class);

        Nave naveActualizada = new Nave();
        naveActualizada.setShip_name("Nave Actualizada");
        naveActualizada.setVelocidadMax(1200);
        naveActualizada.setCapacidadCarga(600);
        naveActualizada.setCoordenada_X(15);
        naveActualizada.setCoordenada_y(25);
        naveActualizada.setCoordenada_z(35);
        naveActualizada.setCreditos(2000.0f);

        String urlUpdate = "http://localhost:" + port + "/api/ship/edit/" + naveExistente.getId();
        rest.put(urlUpdate, naveActualizada);

        Optional<Nave> naveActualizadaOptional = naveRepository.findById(naveExistente.getId());
        assertTrue(naveActualizadaOptional.isPresent());

        Nave naveEnBD = naveActualizadaOptional.get();
        assertEquals("Nave Actualizada", naveEnBD.getShip_name());
        assertEquals(1200, naveEnBD.getVelocidadMax());
        assertEquals(600, naveEnBD.getCapacidadCarga());
        assertEquals(15, naveEnBD.getCoordenada_X());
        assertEquals(25, naveEnBD.getCoordenada_y());
        assertEquals(35, naveEnBD.getCoordenada_z());
        assertEquals(2000.0f, naveEnBD.getCreditos());
    }

     @Test
    void crearNave() {
        Nave nuevaNave = new Nave();
        nuevaNave.setShip_name("Nueva Nave");
        nuevaNave.setVelocidadMax(1000);
        nuevaNave.setCapacidadCarga(500);
        nuevaNave.setCoordenada_X(10);
        nuevaNave.setCoordenada_y(20);
        nuevaNave.setCoordenada_z(30);
        nuevaNave.setCreditos(1500.5f);

        String url = "http://localhost:" + port + "/api/ship/create";
        ResponseEntity<Void> responseEntity = rest.postForEntity(url, nuevaNave, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Nave> naves = naveRepository.findAll();
        Optional<Nave> naveCreadaOptional = naves.stream()
                .filter(nave -> nave.getShip_name().equals("Nueva Nave"))
                .findFirst();

        assertTrue(naveCreadaOptional.isPresent());

        Nave naveCreada = naveCreadaOptional.get();
        assertEquals(1000, naveCreada.getVelocidadMax());
        assertEquals(500, naveCreada.getCapacidadCarga());
        assertEquals(10, naveCreada.getCoordenada_X());
        assertEquals(20, naveCreada.getCoordenada_y());
        assertEquals(30, naveCreada.getCoordenada_z());
        assertEquals(1500.5f, naveCreada.getCreditos());
    }

    @Test
    void listarNaves() {
        String url = "http://localhost:" + port + "/api/ship/list";
        ResponseEntity<Nave[]> responseEntity = rest.getForEntity(url, Nave[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Nave[] naves = responseEntity.getBody();
        assertNotNull(naves);
        assertTrue(naves.length > 0);
    }

}
