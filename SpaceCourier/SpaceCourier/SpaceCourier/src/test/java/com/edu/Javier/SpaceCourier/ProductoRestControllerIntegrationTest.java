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

import com.edu.Javier.SpaceCourier.model.Producto;
import com.edu.Javier.SpaceCourier.repository.ProductoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoRestControllerIntegrationTest {

    
    @LocalServerPort
    private int port;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    TestRestTemplate rest;

    @Test
    void guardarProducto() {
        Producto producto = new Producto();
        producto.setNombreProducto("ProductoX");
        producto.setDescription("Descripción del producto X");

        String url = "http://localhost:" + port + "/api/product/save";
        ResponseEntity<Void> response = rest.postForEntity(url, producto, Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Producto> productos = productoRepository.findAll();
        Optional<Producto> productoGuardado = productos.stream()
                .filter(p -> p.getNombreProducto().equals("ProductoX"))
                .findFirst();

        assertTrue(productoGuardado.isPresent());
        assertEquals("Descripción del producto X", productoGuardado.get().getDescription());
    }

    @Test
    void listarProductos() {
        String url = "http://localhost:" + port + "/api/product/list";
        ResponseEntity<Producto[]> response = rest.getForEntity(url, Producto[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Producto[] productos = response.getBody();
        assertNotNull(productos);
        assertTrue(productos.length > 0);
    }

    @Test
    void verProducto() {
        // Guardar un producto para tener un ID válido
        Producto producto = new Producto();
        producto.setNombreProducto("ProductoY");
        producto.setDescription("Descripción del producto Y");
        productoRepository.save(producto);

        String url = "http://localhost:" + port + "/api/product/view/" + producto.getId();
        ResponseEntity<Producto> response = rest.getForEntity(url, Producto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("ProductoY", response.getBody().getNombreProducto());
    }

    @Test
    void borrarProducto() {
        // Guardar un producto para tener un ID válido
        Producto producto = new Producto();
        producto.setNombreProducto("ProductoZ");
        producto.setDescription("Descripción del producto Z");
        productoRepository.save(producto);

        String url = "http://localhost:" + port + "/api/product/delete/" + producto.getId();
        rest.delete(url);

        assertFalse(productoRepository.existsById(producto.getId()));
    }
    

}
