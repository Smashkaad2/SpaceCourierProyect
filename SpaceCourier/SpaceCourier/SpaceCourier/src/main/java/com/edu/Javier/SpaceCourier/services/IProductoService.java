package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import com.edu.Javier.SpaceCourier.model.Producto;


public interface IProductoService {

    public Producto crearProducto(Producto nuevoProducto);
    public Producto obtenerProducto(Long idProducto);
    public List<Producto> obtenerTodosProducto();
    public void borrarProducto(Long idProducto);

}
