package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.model.ProductoxNave;

public interface INaveService {

    public Nave crearNave(Nave nuevaNave);
    public Nave obtenerNave(Long idNave);
    public List<Nave> obtenerTodasNaves();
    public void borrarNave(Long idNave);
    public Nave actualizarNave(Nave naveUp);
    public void agregarProductoNave(Long idNave, Long idProducto, int stock);
    public List<ProductoxNave> obtenerListaProductos(Long idNave);
    
}
