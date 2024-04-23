package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Javier.SpaceCourier.exception.ExcepcionLlena;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.model.Producto;
import com.edu.Javier.SpaceCourier.repository.ProductoRepository;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(Producto nuevoProducto) {
        return productoRepository.save(nuevoProducto);
    }

    @Override
    public Producto obtenerProducto(Long idProducto) {
        return productoRepository.findById(idProducto).orElseThrow();
    }

    @Override
    public List<Producto> obtenerTodosProducto() {
        return productoRepository.findAll();
    }

    
    @Override
    public void borrarProducto(Long idProducto) {
        Producto product = obtenerProducto(idProducto);
        if (!product.getProductosPlanetas().isEmpty()) {
            throw new ExcepcionLlena("No se puede borrar el producto");
        }
        else
        productoRepository.deleteById(idProducto);
    }

}
