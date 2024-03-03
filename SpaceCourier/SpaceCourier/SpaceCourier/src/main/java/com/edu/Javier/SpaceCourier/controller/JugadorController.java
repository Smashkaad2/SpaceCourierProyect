package com.edu.Javier.SpaceCourier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.services.IJugadorService;

@Controller
@RequestMapping("/player")
public class JugadorController {
    @Autowired
    private IJugadorService jugadorService;

    @GetMapping("/edit/{idPlayer}")
    public ModelAndView recuperarJugador(@PathVariable Long idPlayer) {
        Jugador player = jugadorService.obtenerJugador(idPlayer);
        ModelAndView playerEdit = new ModelAndView("player-edit");
        playerEdit.addObject("player", player);
        return playerEdit;
    }

    @GetMapping("/delete/{idPlayer}")
    public ModelAndView borrarJugador(@PathVariable Long idPlayer) {
        Jugador player = jugadorService.obtenerJugador(idPlayer);
        ModelAndView playerDelete = new ModelAndView("player-delete");
        playerDelete.addObject("player", player);
        return playerDelete;
    }




    @GetMapping("/create")
    public ModelAndView crearJugador() {
        Jugador newPlayer = new Jugador();
        ModelAndView playerCreate = new ModelAndView("player-create");
        playerCreate.addObject("player", newPlayer);
        return playerCreate;
    }

    @GetMapping("/view/{idPlayer}")
    public ModelAndView verJugador(@PathVariable Long idPlayer) {
        Jugador player = jugadorService.obtenerJugador(idPlayer);
        ModelAndView playerView = new ModelAndView("player-view");
        playerView.addObject("player", player);
        return playerView;
    }


    // Creando & Actualizando Jugador
    @PostMapping("/save")
    public RedirectView guardarJugador(@ModelAttribute Jugador player) {
        jugadorService.crearJugador(player);
        return new RedirectView("/player/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteJugador(@ModelAttribute Jugador player){
        jugadorService.borrarJugador(player.getId());
        return new RedirectView("/player/list");
    }

    @GetMapping("/list")
    public ModelAndView listarJugadores() {
        List<Jugador> playerList = jugadorService.obtenerTodosJugadores();
        ModelAndView playerListView = new ModelAndView("player-list");
        playerListView.addObject("players", playerList);
        return playerListView;
    }

}
