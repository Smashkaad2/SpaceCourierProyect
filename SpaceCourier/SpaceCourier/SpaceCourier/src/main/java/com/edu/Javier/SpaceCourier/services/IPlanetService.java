package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import com.edu.Javier.SpaceCourier.model.Planeta;


public interface IPlanetService {

    public Planeta crearPlaneta(Planeta nuevoPlaneta);
    public Planeta obtenerPlaneta(Long idPlaneta);
    public List<Planeta> obtenerTodosPlanetas();
    public void borrarPlaneta(Long idPlaneta);

}
