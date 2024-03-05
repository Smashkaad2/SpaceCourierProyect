package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.repository.PlanetaRepository;



@Service
public class PlanetaService implements IPlanetService {
    @Autowired
    private PlanetaRepository planetaRepository;

    @Override
    public Planeta crearPlaneta(Planeta nuevoPlaneta) {
        return planetaRepository.save(nuevoPlaneta);
    }

    @Override
    public Planeta obtenerPlaneta(Long idPlaneta) {
        return planetaRepository.findById(idPlaneta).orElseThrow();
    }

    @Override
    public List<Planeta> obtenerTodosPlanetas() {
       return planetaRepository.findAll();
    }

    @Override
    public void borrarPlaneta(Long idPlaneta) {
        planetaRepository.deleteById(idPlaneta);
    }

    
}
