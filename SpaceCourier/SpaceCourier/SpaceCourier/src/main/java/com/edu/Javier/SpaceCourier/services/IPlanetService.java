package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.model.ProductoxPlaneta;


public interface IPlanetService {

    public Planeta crearPlaneta(Planeta nuevoPlaneta);
    public Planeta obtenerPlaneta(Long idPlaneta);
    public List<Planeta> obtenerTodosPlanetas();
    public List<ProductoxPlaneta> obtenerListaProductos(Long idPlaneta);
    public void borrarPlaneta(Long idPlaneta);

}
