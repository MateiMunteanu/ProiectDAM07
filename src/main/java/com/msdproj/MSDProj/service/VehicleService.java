package com.msdproj.MSDProj.service;

import com.msdproj.MSDProj.entity.Vehicle;
import com.msdproj.MSDProj.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get a vehicle by ID
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Add a new vehicle
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Update an existing vehicle
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setBrand(updatedVehicle.getBrand());
            vehicle.setYearOfManufacture(updatedVehicle.getYearOfManufacture());
            vehicle.setPrice(updatedVehicle.getPrice());
            vehicle.setStatus(updatedVehicle.getStatus());
            vehicle.setDescription(updatedVehicle.getDescription());
            vehicle.setType(updatedVehicle.getType());
            vehicle.setAvailable(updatedVehicle.getAvailable());
            return vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found with ID " + id);
        }
    }

    // Delete a vehicle by ID
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    // Custom service methods using custom queries from VehicleRepository

    public List<Vehicle> getVehiclesByStatus(String status) {
        return vehicleRepository.findByStatus(status);
    }

    public List<Vehicle> getVehiclesByType(String type) {
        return vehicleRepository.findByType(type);
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailable(true);
    }

    public List<Vehicle> getVehiclesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return vehicleRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
