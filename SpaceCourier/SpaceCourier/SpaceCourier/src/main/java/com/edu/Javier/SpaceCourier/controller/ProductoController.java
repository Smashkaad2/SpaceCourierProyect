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
import com.edu.Javier.SpaceCourier.model.Producto;
import com.edu.Javier.SpaceCourier.services.IProductoService;

@RestController
@Controller
@RequestMapping("/product")
public class ProductoController {
    @Autowired
   private IProductoService productoService;


    // Creando & Actualizando Jugador
    @PostMapping("/save")
    public RedirectView guardarProducto(@ModelAttribute Producto product) {
        productoService.crearProducto(product);
        return new RedirectView("/product/list");
    }

    @PostMapping("/delete")
    public RedirectView deleteNave(@ModelAttribute Producto product){
        try{
            productoService.borrarProducto(product.getId());
            return new RedirectView("/product/list");
        }
        
        catch(ExcepcionLlena e){
             return new RedirectView("/error? message=No se puede borrar la nave porque tiene pasajeros");
        }
    }

    @GetMapping("/list")
    public ModelAndView listarProductos() {
        List<Producto> productList = productoService.obtenerTodosProducto();
        ModelAndView productListView = new ModelAndView("product-list");
        productListView.addObject("products", productList);
        return productListView;
    }

    @GetMapping("/view/{idproduct}")
    public ModelAndView verProducto(@PathVariable Long idproduct) {
        Producto product = productoService.obtenerProducto(idproduct);
        ModelAndView productView = new ModelAndView("product-view");
        productView.addObject("product", product);
        return productView;
    }

    @GetMapping("/create")
    public ModelAndView crearProducto() {
        Producto newProduct = new Producto();
        ModelAndView productCreate = new ModelAndView("product-create");
        productCreate.addObject("product", newProduct);
        return productCreate;
    }

    @GetMapping("/edit/{idProduct}")
    public ModelAndView recuperarJugador(@PathVariable Long idProduct) {
        Producto product = productoService.obtenerProducto(idProduct);
        ModelAndView productEdit = new ModelAndView("product-edit");
        productEdit.addObject("product", product);
        return productEdit;
    }

    //Revisar
    @GetMapping("/delete/{idProduct}")
    public ModelAndView borrarProducto(@PathVariable Long idProduct) {
        Producto product = productoService.obtenerProducto(idProduct);
        ModelAndView productDelete = new ModelAndView("product-delete");
        productDelete.addObject("product", product);
        return productDelete;
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
