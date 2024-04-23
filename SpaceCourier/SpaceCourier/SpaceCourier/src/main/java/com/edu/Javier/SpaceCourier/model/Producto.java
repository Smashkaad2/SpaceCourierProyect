package com.edu.Javier.SpaceCourier.model;

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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombreProducto;
    private String description;
    
    @ManyToOne
    @JsonIgnore
    private Planeta planetProduct;


    @OneToMany(mappedBy = "producto")
    private List<ProductoxNave> productosNave;

    @OneToMany(mappedBy = "productoPlaneta")
    @JsonIgnore
    private List<ProductoxPlaneta> productosPlaneta;

    public List<ProductoxNave> getProductosNave() {
        return productosNave;
    }

    public boolean addProductoNave(ProductoxNave producto) {
        return productosNave.add(producto);
    }

    public List<ProductoxPlaneta> getProductosPlanetas() {
        return productosPlaneta;
    }

    public boolean addProductoPlaneta(ProductoxPlaneta productoPlaneta) {
        return productosPlaneta.add(productoPlaneta);
    }

}
