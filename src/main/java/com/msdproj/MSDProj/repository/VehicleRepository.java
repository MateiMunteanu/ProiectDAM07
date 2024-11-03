package com.msdproj.MSDProj.repository;

import com.msdproj.MSDProj.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    // Custom query method to find vehicles by status (new or second-hand)
    List<Vehicle> findByStatus(String status);

    // Custom query method to find vehicles by type (e.g., SUV, sedan, etc.)
    List<Vehicle> findByType(String type);

    // Custom query method to find available vehicles
    List<Vehicle> findByAvailable(Boolean available);

    // Custom query method to find vehicles within a price range
    List<Vehicle> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
