package com.edu.Javier.SpaceCourier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.Javier.SpaceCourier.model.ProductoxNave;


@Repository
public interface ProductoxNaveRepository extends JpaRepository<ProductoxNave,Long> {

}
