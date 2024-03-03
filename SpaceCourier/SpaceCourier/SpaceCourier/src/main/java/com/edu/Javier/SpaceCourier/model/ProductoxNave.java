package com.edu.Javier.SpaceCourier.model;

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
public class ProductoxNave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int cantidad;

    @ManyToOne 
    private Producto producto;

    @ManyToOne
    private Nave naveProd;
}
