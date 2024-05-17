package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Javier.SpaceCourier.exception.ExcepcionLlena;
import com.edu.Javier.SpaceCourier.model.Estrella;
import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.model.Producto;
import com.edu.Javier.SpaceCourier.model.ProductoxNave;
import com.edu.Javier.SpaceCourier.repository.NaveRepository;
import com.edu.Javier.SpaceCourier.repository.ProductoRepository;
import com.edu.Javier.SpaceCourier.repository.ProductoxNaveRepository;

@Service
public class NaveService implements INaveService {
    @Autowired
    private NaveRepository naveRepository;

    @Autowired
    private ProductoxNaveRepository productoxNaveRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Nave crearNave(Nave nuevaNave) {
        return naveRepository.save(nuevaNave);
    }

    @Override
    public Nave obtenerNave(Long idNave) {
        return naveRepository.findById(idNave).orElseThrow();
    }

    @Override
    public List<Nave> obtenerTodasNaves() {
        return naveRepository.findAll();
    }

    @Override
    public void borrarNave(Long idNave) {
        Nave nave = obtenerNave(idNave);
        if (!nave.getJugador().isEmpty()) {
            throw new ExcepcionLlena("No se puede borrar la nave porque tiene pasajeros");
        } else
            naveRepository.deleteById(idNave);
    }

    @Override
    public Nave actualizarNave(Nave naveUp) {
        return naveRepository.save(naveUp);
    }

    
    @Override
    public void agregarProductoNave(Long idNave, Long idProducto, int stock) {
        Nave nave = naveRepository.findById(idNave).orElseThrow();
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        ProductoxNave productoxNave = productoxNaveRepository.findByNaveIdAndProductoId(idNave, idProducto);
        if (productoxNave == null) {
            productoxNave = new ProductoxNave();
            productoxNave.setNombreProducto(producto.getNombreProducto());
            productoxNave.setCantidad(stock);
            productoxNave.setProducto(producto);
            productoxNave.setNaveProd(nave);
            productoxNaveRepository.save(productoxNave);
        } else {
            productoxNave.setCantidad(stock);
            productoxNaveRepository.save(productoxNave);
        }
    }

    @Override
    public List<ProductoxNave> obtenerListaProductos(Long idNave) {
        Nave nave = naveRepository.findById(idNave).orElseThrow();
        return nave.getProductos();
    }

}
