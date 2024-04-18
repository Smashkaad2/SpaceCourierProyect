package com.edu.Javier.SpaceCourier.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductoxEstrella {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int cantidad;
    private float factor_Demanda ;
    private float stock;

    @ManyToOne
    @JsonIgnore
    private Producto productoEst;

    @ManyToOne
    @JsonIgnore
    private Estrella estrellaProd;

}
