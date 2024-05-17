package com.edu.Javier.SpaceCourier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.services.IJugadorService;
import com.edu.Javier.SpaceCourier.services.INaveService;
import com.edu.Javier.SpaceCourier.services.IProductoService;

@RestController
@RequestMapping("api/ship")
public class RestNaveContoller {
    @Autowired
    private INaveService naveService;

    @Autowired
    private IJugadorService jugadorService;

     @Autowired
    private IProductoService productoService;



    @GetMapping("/list")
    public ResponseEntity<List<Nave>> listarNaves() {
        List<Nave> shipList = naveService.obtenerTodasNaves();
        return ResponseEntity.ok(shipList);
    }

    @PostMapping("/create")
    public ResponseEntity<?> crearNave(@RequestBody Nave newNave) {
        naveService.crearNave(newNave);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/view/{idShip}")
    public ResponseEntity<Nave> verJugador(@PathVariable Long idShip) {
        Nave ship = naveService.obtenerNave(idShip);
        return ResponseEntity.ok(ship);
    }

    
    @PutMapping("/edit/{idShip}")
    public ResponseEntity<?> editarNave(@PathVariable Long idShip, @RequestBody Nave nave) {
        nave.setId(idShip);
        naveService.crearNave(nave);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{idShip}")
    public ResponseEntity<?> borrarNave(@PathVariable Long idShip, @RequestBody Nave ship) {
        naveService.borrarNave(idShip);
        naveService.actualizarNave(ship);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addExistingPlayer/{idPlayer}/{idNave}")
    public ResponseEntity<?> addJugadorExistente(@PathVariable Long idPlayer, @PathVariable Long idNave) {
        Jugador playerAdd = jugadorService.obtenerJugador(idPlayer);
        Nave naveAdd = naveService.obtenerNave(idNave);
        playerAdd.setNave(naveAdd);
        jugadorService.actualizarJugador(playerAdd);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addNewPlayer/{idNave}")
    public ResponseEntity<?> addNewJugador(@PathVariable Long idNave, @RequestBody Jugador player) {
        Nave naveAdd = naveService.obtenerNave(idNave);
        player.setNave(naveAdd);
        jugadorService.crearJugador(player);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addProducto/{idNave}/{idProducto}/{stock}")
    public ResponseEntity<?> AddProductoNave(@PathVariable Long idNave, @PathVariable Long idProducto, @PathVariable int stock) {
        naveService.agregarProductoNave(idNave, idProducto, stock);
        return ResponseEntity.ok().build();
    }
}
