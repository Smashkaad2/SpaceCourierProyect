package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Javier.SpaceCourier.exception.ExcepcionLlena;
import com.edu.Javier.SpaceCourier.model.Nave;
import com.edu.Javier.SpaceCourier.repository.NaveRepository;

@Service
public class NaveService implements INaveService {
    @Autowired
    private NaveRepository naveRepository;

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
        }
        else
        naveRepository.deleteById(idNave);
    }

}
