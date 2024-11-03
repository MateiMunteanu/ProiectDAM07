package com.msdproj.MSDProj.controller;

import com.msdproj.MSDProj.entity.ServiceAppointment;
import com.msdproj.MSDProj.service.ServiceAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-appointments")
public class ServiceAppointmentController {

    private final ServiceAppointmentService serviceAppointmentService;

    @Autowired
    public ServiceAppointmentController(ServiceAppointmentService serviceAppointmentService) {
        this.serviceAppointmentService = serviceAppointmentService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceAppointment>> getAllAppointments() {
        return ResponseEntity.ok(serviceAppointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceAppointment> getAppointmentById(@PathVariable Long id) {
        return serviceAppointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceAppointment> addAppointment(@RequestBody ServiceAppointment appointment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceAppointmentService.addAppointment(appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceAppointment> updateAppointment(@PathVariable Long id, @RequestBody ServiceAppointment appointment) {
        try {
            return ResponseEntity.ok(serviceAppointmentService.updateAppointment(id, appointment));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        serviceAppointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceAppointment>> getAppointmentsByVehicleId(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(serviceAppointmentService.getAppointmentsByVehicleId(vehicleId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ServiceAppointment>> getAppointmentsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(serviceAppointmentService.getAppointmentsByStatus(status));
    }
}
