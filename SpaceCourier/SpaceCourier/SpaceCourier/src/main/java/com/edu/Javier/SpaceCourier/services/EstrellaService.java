package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Javier.SpaceCourier.model.Estrella;
import com.edu.Javier.SpaceCourier.repository.EstrellaRepository;

@Service
public class EstrellaService implements IEstrellaService {
    @Autowired
    private EstrellaRepository estrellaRepository;

    @Override
    public Estrella crearEstrella(Estrella nuevaEstrella) {
        return estrellaRepository.save(nuevaEstrella);
    }

    @Override
    public Estrella obtenerEstrella(Long idEstrella) {
        return estrellaRepository.findById(idEstrella).orElseThrow();
    }

    @Override
    public List<Estrella> obtenerTodasEstrellas() {
        return estrellaRepository.findAll();
    }

    @Override
    public void borrarEstrella(Long idEstrella) {
        estrellaRepository.deleteById(idEstrella);
    }

}
