package com.edu.Javier.SpaceCourier.services;

import java.util.List;

import com.edu.Javier.SpaceCourier.model.Jugador;
import com.edu.Javier.SpaceCourier.model.Nave;

public interface IJugadorService {

    public Jugador crearJugador(Jugador nuevoJugador);
    public Jugador obtenerJugador(Long idJugador);
    public List<Jugador> obtenerTodosJugadores();
    public void borrarJugador(Long idJugador);
    public Jugador actualizarJugador(Jugador player);
    public Nave obtenerNaveJugador(Long idJugador);

}
