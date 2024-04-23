package com.edu.Javier.SpaceCourier.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombrePlaneta;
    private String descripcionPlaneta;

    @ManyToOne
    @JsonIgnore
    private Estrella estrella; 

     
    @OneToMany(mappedBy = "planetaProducto")
    @JsonIgnore
    private List<ProductoxPlaneta> productos;

    public List<ProductoxPlaneta> getProductosEnEstrella() {
        return productos;
    }

    public boolean addProductoenPlaneta(ProductoxPlaneta productoenPlaneta) {
        return productos.add(productoenPlaneta);
    }


}
