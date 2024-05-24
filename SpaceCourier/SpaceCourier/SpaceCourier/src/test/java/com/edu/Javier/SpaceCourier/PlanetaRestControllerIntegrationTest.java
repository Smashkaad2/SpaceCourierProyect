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

import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.repository.PlanetaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanetaRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    PlanetaRepository planetaRepository;

    @Autowired
    TestRestTemplate rest;

    @Test
    void guardarPlaneta() {
        Planeta planeta = new Planeta();
        planeta.setNombrePlaneta("PlanetaX");
        planeta.setDescripcionPlaneta("Un planeta increíble");

        String url = "http://localhost:" + port + "/api/planet/planet/save";
        ResponseEntity<Void> response = rest.postForEntity(url, planeta, Void.class);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        
        List<Planeta> planetas = planetaRepository.findAll();
        Optional<Planeta> planetaGuardado = planetas.stream()
                .filter(p -> p.getNombrePlaneta().equals("PlanetaX"))
                .findFirst();

        assertTrue(planetaGuardado.isPresent());
        assertEquals("Un planeta increíble", planetaGuardado.get().getDescripcionPlaneta());
    }

    @Test
    void listarPlanetas() {
        String url = "http://localhost:" + port + "/api/planet/list";
        ResponseEntity<Planeta[]> response = rest.getForEntity(url, Planeta[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Planeta[] planetas = response.getBody();
        assertNotNull(planetas);
        assertTrue(planetas.length > 0);
    }

    @Test
    void verPlaneta() {
        // Guardar un planeta para tener un ID válido
        Planeta planeta = new Planeta();
        planeta.setNombrePlaneta("PlanetaY");
        planeta.setDescripcionPlaneta("Un planeta increíble");
        planetaRepository.save(planeta);

        String url = "http://localhost:" + port + "/api/planet/view/" + planeta.getId();
        ResponseEntity<Planeta> response = rest.getForEntity(url, Planeta.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("PlanetaY", response.getBody().getNombrePlaneta());
    }

    @Test
    void crearPlaneta() {
        Planeta planeta = new Planeta();
        planeta.setNombrePlaneta("PlanetaZ");
        planeta.setDescripcionPlaneta("Otro planeta increíble");

        String url = "http://localhost:" + port + "/api/planet/planet/create";
        ResponseEntity<Planeta> response = rest.postForEntity(url, planeta, Planeta.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        Planeta planetaCreado = response.getBody();
        assertEquals("PlanetaZ", planetaCreado.getNombrePlaneta());
        assertEquals("Otro planeta increíble", planetaCreado.getDescripcionPlaneta());
    }

    @Test
    void recuperarPlaneta() {
        // Guardar un planeta para tener un ID válido
        Planeta planeta = new Planeta();
        planeta.setNombrePlaneta("PlanetaW");
        planeta.setDescripcionPlaneta("Otro planeta increíble");
        planetaRepository.save(planeta);

        String url = "http://localhost:" + port + "/api/planet/planet/edit/" + planeta.getId();
        ResponseEntity<Planeta> response = rest.getForEntity(url, Planeta.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("PlanetaW", response.getBody().getNombrePlaneta());
    }

    @Test
    void borrarPlaneta() {
        // Guardar un planeta para tener un ID válido
        Planeta planeta = new Planeta();
        planeta.setNombrePlaneta("PlanetaV");
        planeta.setDescripcionPlaneta("Otro planeta increíble");
        planetaRepository.save(planeta);

        String url = "http://localhost:" + port + "/api/planet/planet/delete/" + planeta.getId();
        rest.delete(url);

        assertFalse(planetaRepository.existsById(planeta.getId()));
    }

    @Test
    void obtenerListaProductos() {
        // Guardar un planeta para tener un ID válido
        Planeta planeta = new Planeta();
        planeta.setNombrePlaneta("PlanetaU");
        planeta.setDescripcionPlaneta("Otro planeta increíble");
        planetaRepository.save(planeta);

        String url = "http://localhost:" + port + "/api/planet/list/product/" + planeta.getId();
        ResponseEntity<Object[]> response = rest.getForEntity(url, Object[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    
}
