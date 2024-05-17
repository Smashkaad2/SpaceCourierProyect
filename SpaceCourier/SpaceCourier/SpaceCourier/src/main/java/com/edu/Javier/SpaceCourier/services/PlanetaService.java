package com.edu.Javier.SpaceCourier.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Javier.SpaceCourier.dto.ProductoxPlanetadto;
import com.edu.Javier.SpaceCourier.model.Planeta;
import com.edu.Javier.SpaceCourier.model.ProductoxPlaneta;
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

    @Override
    public List<ProductoxPlanetadto> obtenerListaProductos(Long idPlaneta) {
        Planeta planet = planetaRepository.findById(idPlaneta).orElseThrow();
        List<ProductoxPlaneta> planetProductsList = planet.getProductosEnPlaneta();  
        List<ProductoxPlanetadto> listaProductosDto = new ArrayList<>();

        for (ProductoxPlaneta productoxPlaneta : planetProductsList) {
            ProductoxPlanetadto productoDto = new ProductoxPlanetadto();
            productoDto.setId(productoxPlaneta.getId());
            productoDto.setProductoNombre(productoxPlaneta.getProductoNombre());
            productoDto.setFactor_Demanda(productoxPlaneta.getFactor_Demanda());
            productoDto.setFactorOferta(productoxPlaneta.getFactorOferta());
            productoDto.setStock(productoxPlaneta.getStock());
            productoDto.setProductoid(productoxPlaneta.getProductoPlaneta().getId());
            productoDto.setPlanetaid(productoxPlaneta.getPlanetaProducto().getId());
            listaProductosDto.add(productoDto);
        }

        return listaProductosDto;
    }

    
}
