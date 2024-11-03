package com.msdproj.MSDProj.service;

import com.msdproj.MSDProj.entity.ServiceAppointment;
import com.msdproj.MSDProj.repository.ServiceAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceAppointmentService {

    private final ServiceAppointmentRepository serviceAppointmentRepository;

    @Autowired
    public ServiceAppointmentService(ServiceAppointmentRepository serviceAppointmentRepository) {
        this.serviceAppointmentRepository = serviceAppointmentRepository;
    }

    public List<ServiceAppointment> getAllAppointments() {
        return serviceAppointmentRepository.findAll();
    }

    public Optional<ServiceAppointment> getAppointmentById(Long id) {
        return serviceAppointmentRepository.findById(id);
    }

    @Autowired
    private NotificationService notificationService;
    public ServiceAppointment addAppointment(ServiceAppointment appointment) {
        ServiceAppointment savedAppointment = serviceAppointmentRepository.save(appointment);

        // Send reminder to client
        String appointmentDetails = "Date: " + appointment.getAppointmentDate() + ", Service: " + appointment.getServiceType();
        notificationService.sendServiceReminder(appointment.getVehicle().getClient().getEmail(), appointmentDetails);

        return savedAppointment;
    }

    public ServiceAppointment updateAppointment(Long id, ServiceAppointment updatedAppointment) {
        Optional<ServiceAppointment> existingAppointment = serviceAppointmentRepository.findById(id);
        if (existingAppointment.isPresent()) {
            ServiceAppointment appointment = existingAppointment.get();
            appointment.setVehicle(updatedAppointment.getVehicle());
            appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appointment.setServiceType(updatedAppointment.getServiceType());
            appointment.setStatus(updatedAppointment.getStatus());
            return serviceAppointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found with ID " + id);
        }
    }

    public void deleteAppointment(Long id) {
        serviceAppointmentRepository.deleteById(id);
    }

    public List<ServiceAppointment> getAppointmentsByVehicleId(Long vehicleId) {
        return serviceAppointmentRepository.findByVehicleId(vehicleId);
    }

    public List<ServiceAppointment> getAppointmentsByStatus(String status) {
        return serviceAppointmentRepository.findByStatus(status);
    }

}

