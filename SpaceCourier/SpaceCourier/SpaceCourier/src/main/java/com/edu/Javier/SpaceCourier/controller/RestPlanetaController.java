package com.edu.Javier.SpaceCourier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.model.ProductoxPlaneta;
import com.edu.Javier.SpaceCourier.services.IPlanetService;

@RestController
@RequestMapping("api/planet")
public class RestPlanetaController {

    @Autowired
    private IPlanetService planetaService;

    @PostMapping("/planet/save")
    public RedirectView guardarPlaneta(@RequestBody Planeta planet) {
        planetaService.crearPlaneta(planet);
        return new RedirectView("/planet/list");
    }

    @PostMapping("/planet/delete")
    public RedirectView deletePlaneta(@RequestBody Planeta planet){
        planetaService.borrarPlaneta(planet.getId());
        return new RedirectView("/planet/list");
    }

    @GetMapping("/list")
    public List<Planeta> listarPlanetas() {
        return planetaService.obtenerTodosPlanetas();
    }

    @GetMapping("/view/{idplanet}")
    public Planeta verPlaneta(@PathVariable Long idplanet) {
        return planetaService.obtenerPlaneta(idplanet);
    }

    @PostMapping("/planet/create")
    public Planeta crearPlaneta(@RequestBody Planeta newPlaneta) {
        return planetaService.crearPlaneta(newPlaneta);
    }

    @GetMapping("/planet/edit/{idPlanet}")
    public Planeta recuperarPlaneta(@PathVariable Long idPlanet) {
        return planetaService.obtenerPlaneta(idPlanet);
    }

    @DeleteMapping("/planet/delete/{idPlanet}")
    public void borrarPlaneta(@PathVariable Long idPlanet) {
        planetaService.borrarPlaneta(idPlanet);
    }

    @GetMapping("/list/product/{idplanet}")
    public List<ProductoxPlaneta> obtenerListaPlanetasDeEstrella(@PathVariable long idplanet) {
        return planetaService.obtenerListaProductos(idplanet);
    }
    
}
