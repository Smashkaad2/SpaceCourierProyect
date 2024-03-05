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
import com.edu.Javier.SpaceCourier.model.Producto;
import com.edu.Javier.SpaceCourier.services.IPlanetService;
import com.edu.Javier.SpaceCourier.services.IProductoService;

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

    // @GetMapping("/create")
    // public ModelAndView crearProducto() {
    //     Producto newProduct = new Producto();
    //     ModelAndView productCreate = new ModelAndView("product-create");
    //     productCreate.addObject("planet", newProduct);
    //     return productCreate;
    // }

    // @GetMapping("/edit/{idstar}")
    // public ModelAndView recuperarJugador(@PathVariable Long idProduct) {
    //     Producto product = productoService.obtenerProducto(idProduct);
    //     ModelAndView productEdit = new ModelAndView("product-edit");
    //     productEdit.addObject("planet", product);
    //     return productEdit;
    // }

    //Revisar
    // @GetMapping("/delete/{idProduct}")
    // public ModelAndView borrarProducto(@PathVariable Long idProduct) {
    //     Producto product = productoService.obtenerProducto(idProduct);
    //     ModelAndView productDelete = new ModelAndView("product-delete");
    //     productDelete.addObject("planet", product);
    //     return productDelete;
    // }


}
