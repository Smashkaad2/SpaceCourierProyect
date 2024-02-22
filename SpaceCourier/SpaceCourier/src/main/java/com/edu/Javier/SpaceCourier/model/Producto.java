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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre_Producto;
    private String description;

    @OneToMany(mappedBy = "producto")
    private List<ProductoxNave> productosNave;

    @OneToMany(mappedBy = "productoEst")
    private List<ProductoxEstrella> productosEstrella;
}
