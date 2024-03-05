package com.edu.Javier.SpaceCourier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.edu.Javier.SpaceCourier.model.Estrella;
import com.edu.Javier.SpaceCourier.services.IEstrellaService;

@RestController
@Controller
@RequestMapping("/star")
public class EstrellaController {
    @Autowired
   private IEstrellaService estrellaService;


    // Creando & Actualizando Jugador
    @PostMapping("/save")
    public RedirectView guardarEstrella(@ModelAttribute Estrella star) {
        estrellaService.crearEstrella(star);
        return new RedirectView("/star/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteNave(@ModelAttribute Estrella star){
        estrellaService.borrarEstrella(star.getId());
        return new RedirectView("/star/list");
    }

    @GetMapping("/list")
    public ModelAndView listarEstrellas() {
        List<Estrella> starList = estrellaService.obtenerTodasEstrellas();
        ModelAndView starListView = new ModelAndView("estrella-list");
        starListView.addObject("stars", starList);
        return starListView;
    }

    @GetMapping("/view/{idstar}")
    public ModelAndView verEstrella(@PathVariable Long idstar) {
        Estrella star = estrellaService.obtenerEstrella(idstar);
        ModelAndView starView = new ModelAndView("estrella-view");
        starView.addObject("star", star);
        return starView;
    }

    @GetMapping("/create")
    public ModelAndView crearEstrella() {
        Estrella newEstrella = new Estrella();
        ModelAndView estrellaCreate = new ModelAndView("estrella-create");
        estrellaCreate.addObject("star", newEstrella);
        return estrellaCreate;
    }

    @GetMapping("/edit/{idstar}")
    public ModelAndView recuperarJugador(@PathVariable Long idstar) {
        Estrella star = estrellaService.obtenerEstrella(idstar);
        ModelAndView estrellaEdit = new ModelAndView("estrella-edit");
        estrellaEdit.addObject("star", star);
        return estrellaEdit;
    }

    //Revisar
    @GetMapping("/delete/{idShip}")
    public ModelAndView borrarEstrella(@PathVariable Long idStar) {
        Estrella star = estrellaService.obtenerEstrella(idStar);
        ModelAndView starDelete = new ModelAndView("estrella-delete");
        starDelete.addObject("star", star);
        return starDelete;
    }


    //Revisar
    // @GetMapping("/addPlayer/{idPlayer}/{idNave}")
    // public ModelAndView addJugador(@PathVariable Long idPlayer, @PathVariable Long idNave){
    //     Jugador playerAdd = jugadorService.obtenerJugador(idPlayer);
    //     Nave naveAdd = naveService.obtenerNave(idNave);
    //     ModelAndView shipAddPlayer = new ModelAndView("ship-addplayer");
    //     shipAddPlayer.addObject("player", playerAdd);
    //     shipAddPlayer.addObject("ship", naveAdd);
    //     return shipAddPlayer;
    // }

}
