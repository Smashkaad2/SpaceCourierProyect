package com.edu.Javier.SpaceCourier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edu.Javier.SpaceCourier.model.Producto;
import com.edu.Javier.SpaceCourier.services.IProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class RestControllerProducto {

    @Autowired
    private IProductoService productoService;

    @PostMapping("/save")
    public void guardarProducto(@RequestBody Producto product) {
        productoService.crearProducto(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.borrarProducto(id);
    }

    @GetMapping("/list")
    public List<Producto> listarProductos() {
        return productoService.obtenerTodosProducto();
    }

    @GetMapping("/view/{id}")
    public Producto verProducto(@PathVariable Long id) {
        return productoService.obtenerProducto(id);
    }

}