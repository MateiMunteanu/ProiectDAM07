package com.msdproj.MSDProj.repository;

import com.msdproj.MSDProj.entity.ServiceAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceAppointmentRepository extends JpaRepository<ServiceAppointment, Long> {

    List<ServiceAppointment> findByVehicleId(Long vehicleId);

    List<ServiceAppointment> findByStatus(String status);
}

