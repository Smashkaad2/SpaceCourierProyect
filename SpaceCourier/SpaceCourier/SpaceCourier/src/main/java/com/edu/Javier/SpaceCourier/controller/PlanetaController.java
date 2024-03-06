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

import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.services.IPlanetService;

@RestController
@Controller
@RequestMapping("/planet")
public class PlanetaController {
    @Autowired
   private IPlanetService planetaService;


    // Creando & Actualizando Jugador
    @PostMapping("/save")
    public RedirectView guardarPlaneta(@ModelAttribute Planeta planet) {
        planetaService.crearPlaneta(planet);
        return new RedirectView("/planet/list");
    }

    @PostMapping("/delete")
    public RedirectView deletePlaneta(@ModelAttribute Planeta planet){
        planetaService.borrarPlaneta(planet.getId());
        return new RedirectView("/planet/list");
    }

    @GetMapping("/list")
    public ModelAndView listarPlanetas() {
        List<Planeta> planetList = planetaService.obtenerTodosPlanetas();
        ModelAndView planetListView = new ModelAndView("planet-list");
        planetListView.addObject("planets", planetList);
        return planetListView;
    }

    @GetMapping("/view/{idplanet}")
    public ModelAndView verProducto(@PathVariable Long idplanet) {
        Planeta planet = planetaService.obtenerPlaneta(idplanet);
        ModelAndView planetView = new ModelAndView("planet-view");
        planetView.addObject("planet", planet);
        return planetView;
    }

    @GetMapping("/create")
    public ModelAndView crearPlaneta() {
        Planeta newPlaneta = new Planeta();
        ModelAndView planetaCreate = new ModelAndView("planet-create");
        planetaCreate.addObject("planet", newPlaneta);
        return planetaCreate;
    }

    @GetMapping("/edit/{idPlanet}")
    public ModelAndView recuperarJugador(@PathVariable Long idPlanet) {
        Planeta planet = planetaService.obtenerPlaneta(idPlanet);
        ModelAndView planetEdit = new ModelAndView("planet-edit");
        planetEdit.addObject("planet", planet);
        return planetEdit;
    }

    //Revisar
    @GetMapping("/delete/{idPlanet}")
    public ModelAndView borrarPlaneta(@PathVariable Long idPlanet) {
        Planeta planet = planetaService.obtenerPlaneta(idPlanet);
        ModelAndView planetDelete = new ModelAndView("planet-delete");
        planetDelete.addObject("planet", planet);
        return planetDelete;
    }

}
