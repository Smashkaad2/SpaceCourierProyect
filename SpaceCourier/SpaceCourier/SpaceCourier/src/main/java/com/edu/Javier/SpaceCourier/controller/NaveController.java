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

import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.services.INaveService;

@Controller
@RequestMapping("/ship")
public class NaveController {
    @Autowired
    private INaveService naveService;

 
    @GetMapping("/create")
    public ModelAndView crearNave() {
        Nave newShip = new Nave();
        ModelAndView shipCreate = new ModelAndView("ship-create");
        shipCreate.addObject("ship", newShip);
        return shipCreate;
    }


    // Creando & Actualizando Jugador
    @PostMapping("/save")
    public RedirectView guardarNave(@ModelAttribute Nave ship) {
        naveService.crearNave(ship);
        return new RedirectView("/ship/list");
    }


    @GetMapping("/list")
    public ModelAndView listarNaves() {
        List<Nave> shipList = naveService.obtenerTodasNaves();
        ModelAndView shipListView = new ModelAndView("ship-list");
        shipListView.addObject("ships", shipList);
        return shipListView;
    }

}
