package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.repository.JugadorRepository;

@Service
public class JugadorService implements IJugadorService {
    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public Jugador crearJugador(Jugador nuevoJugador) { // Si detecta un Id que ya existe el crud simplemente lo va a actualizar con su id especifico.
        return jugadorRepository.save(nuevoJugador);
    }

    @Override
    public Jugador obtenerJugador(Long idJugador) {
       return jugadorRepository.findById(idJugador).orElseThrow();
    }

    @Override
    public List<Jugador> obtenerTodosJugadores() {
       return jugadorRepository.findAll();
    }


    @Override
    public void borrarJugador(Long idJugador) {
        jugadorRepository.deleteById(idJugador);
    }    
}
