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

import com.edu.Javier.SpaceCourier.model.Estrella;
import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.repository.EstrellaRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstrellaRestControllerIntegrationTest {

      @LocalServerPort
    private int port;

    @Autowired
    EstrellaRepository estrellaRepository;

    @Autowired
    TestRestTemplate rest;

    @Test
    void listarEstrellas() {
        String url = "http://localhost:" + port + "/api/star/list";
        ResponseEntity<Estrella[]> responseEntity = rest.getForEntity(url, Estrella[].class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Estrella[] estrellas = responseEntity.getBody();
        assertNotNull(estrellas);
        assertTrue(estrellas.length > 0);
    }

    @Test
    void crearEstrella() {
        Estrella nuevaEstrella = new Estrella();
        nuevaEstrella.setNombre_estrella("Nueva Estrella");
        nuevaEstrella.setCoordenada_X(10);
        nuevaEstrella.setCoordenada_y(20);
        nuevaEstrella.setCoordenada_z(30);

        String url = "http://localhost:" + port + "/api/star/save";
        ResponseEntity<Void> responseEntity = rest.postForEntity(url, nuevaEstrella, Void.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<Estrella> estrellas = estrellaRepository.findAll();
        Optional<Estrella> estrellaCreadaOptional = estrellas.stream()
                .filter(estrella -> estrella.getNombre_estrella().equals("Nueva Estrella"))
                .findFirst();

        assertTrue(estrellaCreadaOptional.isPresent());

        Estrella estrellaCreada = estrellaCreadaOptional.get();
        assertEquals(10, estrellaCreada.getCoordenada_X());
        assertEquals(20, estrellaCreada.getCoordenada_y());
        assertEquals(30, estrellaCreada.getCoordenada_z());
    }

    @Test
    void verEstrella() {
        String url = "http://localhost:" + port + "/api/star/view/1";
        ResponseEntity<Estrella> responseEntity = rest.getForEntity(url, Estrella.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Estrella estrella = responseEntity.getBody();
        assertNotNull(estrella);
        assertEquals(1L, estrella.getId());
    }

    @Test
    void editarEstrella() {
        String url = "http://localhost:" + port + "/api/star/view/2";
        Estrella estrellaExistente = rest.getForObject(url, Estrella.class);

        Estrella estrellaActualizada = new Estrella();
        estrellaActualizada.setNombre_estrella("Estrella Actualizada");
        estrellaActualizada.setCoordenada_X(15);
        estrellaActualizada.setCoordenada_y(25);
        estrellaActualizada.setCoordenada_z(35);

        String urlUpdate = "http://localhost:" + port + "/api/star/edit/" + estrellaExistente.getId();
        rest.put(urlUpdate, estrellaActualizada);

        Optional<Estrella> estrellaActualizadaOptional = estrellaRepository.findById(estrellaExistente.getId());
        assertTrue(estrellaActualizadaOptional.isPresent());

        Estrella estrellaEnBD = estrellaActualizadaOptional.get();
        assertEquals("Estrella Actualizada", estrellaEnBD.getNombre_estrella());
        assertEquals(15, estrellaEnBD.getCoordenada_X());
        assertEquals(25, estrellaEnBD.getCoordenada_y());
        assertEquals(35, estrellaEnBD.getCoordenada_z());
    }

    @Test
    void borrarEstrella() {
        Estrella nuevaEstrella = new Estrella();
        nuevaEstrella.setNombre_estrella("Estrella a Borrar");
        nuevaEstrella.setCoordenada_X(10);
        nuevaEstrella.setCoordenada_y(20);
        nuevaEstrella.setCoordenada_z(30);
        estrellaRepository.save(nuevaEstrella);

        Long idEstrella = nuevaEstrella.getId();
        String url = "http://localhost:" + port + "/api/star/delete/" + idEstrella;
        rest.delete(url);

        assertFalse(estrellaRepository.existsById(idEstrella));
    }

  
    
}
