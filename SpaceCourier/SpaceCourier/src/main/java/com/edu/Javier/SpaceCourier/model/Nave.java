package com.edu.Javier.SpaceCourier.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Nave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ship_name;
    private int velocidadMax;
    private int capacidadCarga;

    @OneToMany(mappedBy = "nave")
    private List<Jugador> jugador;

    @OneToMany(mappedBy = "naveProd")
    private List<ProductoxNave> productos;

}
