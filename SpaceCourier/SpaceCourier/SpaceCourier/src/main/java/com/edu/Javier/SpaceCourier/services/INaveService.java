package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import com.edu.Javier.SpaceCourier.model.Nave;

public interface INaveService {

    public Nave crearNave(Nave nuevaNave);
    public Nave obtenerNave(Long idNave);
    public List<Nave> obtenerTodasNaves();
    public void borrarNave(Long idNave);
    
}
