package com.edu.Javier.SpaceCourier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    // Creando & Actualizando Jugador
    @PostMapping("/create")
    public RedirectView guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorService.crearJugador(jugador);
        return new RedirectView("/player/list");
    }

    @GetMapping("/list")
    public ModelAndView listarJugadores() {
        List<Jugador> playerList = jugadorService.obtenerTodosJugadores();
        ModelAndView playerListView = new ModelAndView("person-list");
        playerListView.addObject("players", playerList);
        return playerListView;
    }

}
