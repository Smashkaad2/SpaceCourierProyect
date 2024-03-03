package com.edu.Javier.SpaceCourier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.Javier.SpaceCourier.model.Estrella;


@Repository
public interface EstrellaRepository extends JpaRepository<Estrella,Long> {

}
