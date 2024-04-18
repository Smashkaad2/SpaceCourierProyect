package com.edu.Javier.SpaceCourier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.Javier.SpaceCourier.model.Estrella;
import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.services.IEstrellaService;

@RestController
@RequestMapping("/api/star")
public class RestEstrellaController {

    @Autowired
    private IEstrellaService estrellaService;

    // Creando & Actualizando Estrella
    @PostMapping("/save")
    public ResponseEntity<Void> guardarEstrella(@RequestBody Estrella star) {
        estrellaService.crearEstrella(star);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteEstrella(@RequestBody Estrella star) {
        estrellaService.borrarEstrella(star.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public List<Estrella> listarEstrellas() {
        return estrellaService.obtenerTodasEstrellas();
    }

    @GetMapping("/view/{idstar}")
    public ResponseEntity<Estrella> verEstrella(@PathVariable Long idstar) {
        Estrella star = estrellaService.obtenerEstrella(idstar);
        return ResponseEntity.ok(star);
    }

    @GetMapping("/create")
    public Estrella crearEstrella() {
        return new Estrella();
    }

    @GetMapping("/edit/{idstar}")
    public void recuperarEstrella(@PathVariable Long idstar, @RequestBody Estrella star) {
        star.setId(idstar);
        estrellaService.actualizarEstrella(star);
    }

    @DeleteMapping("/delete/{idStar}")
    public ResponseEntity<Void> borrarEstrella(@PathVariable Long idStar) {
        estrellaService.borrarEstrella(idStar);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/planet/{idstar}")
    public List<Planeta> obtenerListaPlanetasDeEstrella(@PathVariable long idstar) {
        return estrellaService.obtenerListaPlanetas(idstar);

    }
}