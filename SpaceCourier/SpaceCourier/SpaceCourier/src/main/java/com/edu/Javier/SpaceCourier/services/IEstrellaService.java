package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import com.edu.Javier.SpaceCourier.model.Estrella;


public interface IEstrellaService {

    public Estrella crearEstrella(Estrella nuevaEstrella);
    public Estrella obtenerEstrella(Long idEstrella);
    public List<Estrella> obtenerTodasEstrellas();
    public void borrarEstrella(Long idEstrella);

}
