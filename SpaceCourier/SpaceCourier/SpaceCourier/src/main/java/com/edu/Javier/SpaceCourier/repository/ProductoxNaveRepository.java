package com.edu.Javier.SpaceCourier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edu.Javier.SpaceCourier.model.ProductoxNave;


@Repository
public interface ProductoxNaveRepository extends JpaRepository<ProductoxNave,Long> {

    @Query("SELECT pn FROM ProductoxNave pn WHERE pn.naveProd.id = :idNave AND pn.producto.id = :idProducto")
    ProductoxNave findByNaveIdAndProductoId(@Param("idNave") Long idNave, @Param("idProducto") Long idProducto);

}
