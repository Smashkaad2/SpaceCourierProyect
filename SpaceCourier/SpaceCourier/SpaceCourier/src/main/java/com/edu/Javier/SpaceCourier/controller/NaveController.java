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

import com.edu.Javier.SpaceCourier.exception.ExcepcionLlena;
import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.services.IJugadorService;
import com.edu.Javier.SpaceCourier.services.INaveService;

@Controller
@RequestMapping("/ship")
public class NaveController {
    @Autowired
    private INaveService naveService;

    @Autowired
    private IJugadorService jugadorService;

    // Creando & Actualizando Jugador
    @PostMapping("/save")
    public RedirectView guardarNave(@ModelAttribute Nave ship) {
        naveService.crearNave(ship);
        return new RedirectView("/ship/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteNave(@ModelAttribute Nave ship) {
        try {
            naveService.borrarNave(ship.getId());
            return new RedirectView("/ship/list");
        }

        catch (ExcepcionLlena e) {
            return new RedirectView("/error? message=No se puede borrar la nave porque tiene pasajeros");
        }
    }
    

    @GetMapping("/list")
    public ModelAndView listarNaves() {
        List<Nave> shipList = naveService.obtenerTodasNaves();
        ModelAndView shipListView = new ModelAndView("ship-list");
        shipListView.addObject("ships", shipList);
        return shipListView;
    }

    @GetMapping("/view/{idShip}")
    public ModelAndView verJugador(@PathVariable Long idShip) {
        Nave ship = naveService.obtenerNave(idShip);
        ModelAndView shipView = new ModelAndView("ship-view");
        shipView.addObject("ship", ship);
        return shipView;
    }

    @GetMapping("/create")
    public ModelAndView crearNave() {
        Nave newNave = new Nave();
        ModelAndView naveCreate = new ModelAndView("ship-create");
        naveCreate.addObject("ship", newNave);
        return naveCreate;
    }

    @GetMapping("/edit/{idShip}")
    public ModelAndView recuperarJugador(@PathVariable Long idShip) {
        Nave nave = naveService.obtenerNave(idShip);
        ModelAndView naveEdit = new ModelAndView("ship-edit");
        naveEdit.addObject("ship", nave);
        return naveEdit;
    }

    // Revisar
    @GetMapping("/delete/{idShip}")
    public ModelAndView borrarJugador(@PathVariable Long idShip) {
        Nave ship = naveService.obtenerNave(idShip);
        ModelAndView shipDelete = new ModelAndView("ship-delete");
        shipDelete.addObject("ship", ship);
        return shipDelete;
    }

    // Revisar
    @GetMapping("/addPlayer/{idPlayer}/{idNave}")
    public ModelAndView addJugador(@PathVariable Long idPlayer, @PathVariable Long idNave) {
        Jugador playerAdd = jugadorService.obtenerJugador(idPlayer);
        Nave naveAdd = naveService.obtenerNave(idNave);
        ModelAndView shipAddPlayer = new ModelAndView("ship-addplayer");
        shipAddPlayer.addObject("player", playerAdd);
        shipAddPlayer.addObject("ship", naveAdd);
        return shipAddPlayer;
    }

}
