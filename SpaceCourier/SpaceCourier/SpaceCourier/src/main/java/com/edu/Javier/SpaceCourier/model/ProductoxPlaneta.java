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
public class ProductoxPlaneta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productoNombre;
    private float factor_Demanda ;
    private float stock;

    @ManyToOne
    @JsonIgnore
    private Producto productoPlaneta;

    @ManyToOne
    @JsonIgnore
    private Planeta planetaProducto;

}
