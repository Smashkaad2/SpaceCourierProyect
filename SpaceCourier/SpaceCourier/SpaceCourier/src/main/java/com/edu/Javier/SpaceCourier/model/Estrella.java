package com.edu.Javier.SpaceCourier.model;

import java.util.ArrayList;
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


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estrella {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre_estrella;
    private int coordenada_X;
    private int coordenada_y;
    private int coordenada_z;

     @OneToMany(mappedBy = "estrella")
    private List<Planeta> planetas;

    @OneToMany(mappedBy = "estrellaProd")
    private List<ProductoxEstrella> productos;

}
