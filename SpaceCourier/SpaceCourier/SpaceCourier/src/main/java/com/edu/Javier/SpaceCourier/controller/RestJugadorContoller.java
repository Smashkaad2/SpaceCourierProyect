package com.edu.Javier.SpaceCourier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.services.IJugadorService;

@RestController
@RequestMapping("/api/player")
public class RestJugadorContoller {

    @Autowired
    private IJugadorService jugadorService;

    @GetMapping("/{idPlayer}")
    public Jugador recuperarJugador(@PathVariable Long idPlayer) {
        return jugadorService.obtenerJugador(idPlayer);
    }

    @GetMapping("nave/{idPlayer}")
    public Nave recuperarJugadorNave(@PathVariable Long idPlayer) {
        return jugadorService.obtenerNaveJugador(idPlayer);
    }

    @DeleteMapping("/{idPlayer}")
    public void borrarJugador(@PathVariable Long idPlayer) {
        jugadorService.borrarJugador(idPlayer);
    }

    @PostMapping
    public ResponseEntity<Void> crearJugador(@RequestBody Jugador jugador) {
        jugadorService.crearJugador(jugador);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{idPlayer}")
    public void actualizarJugador(@PathVariable Long idPlayer, @RequestBody Jugador jugador) {
        jugador.setId(idPlayer);
        jugadorService.actualizarJugador(jugador);
    }

    @GetMapping("/list")
    public List<Jugador> listarJugadores() {
        return jugadorService.obtenerTodosJugadores();
    }

}
