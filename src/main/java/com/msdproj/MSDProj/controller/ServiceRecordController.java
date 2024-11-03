package com.msdproj.MSDProj.controller;

import com.msdproj.MSDProj.entity.ServiceRecord;
import com.msdproj.MSDProj.service.ServiceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    @Autowired
    public ServiceRecordController(ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceRecord>> getAllServiceRecords() {
        return ResponseEntity.ok(serviceRecordService.getAllServiceRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceRecord> getServiceRecordById(@PathVariable Long id) {
        return serviceRecordService.getServiceRecordById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceRecord> addServiceRecord(@RequestBody ServiceRecord serviceRecord) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceRecordService.addServiceRecord(serviceRecord));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceRecord> updateServiceRecord(@PathVariable Long id, @RequestBody ServiceRecord serviceRecord) {
        try {
            return ResponseEntity.ok(serviceRecordService.updateServiceRecord(id, serviceRecord));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceRecord(@PathVariable Long id) {
        serviceRecordService.deleteServiceRecord(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ServiceRecord>> getServiceRecordsByVehicleId(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(serviceRecordService.getServiceRecordsByVehicleId(vehicleId));
    }

    @GetMapping("/completed/{completed}")
    public ResponseEntity<List<ServiceRecord>> getServiceRecordsByCompletionStatus(@PathVariable Boolean completed) {
        return ResponseEntity.ok(serviceRecordService.getServiceRecordsByCompletionStatus(completed));
    }
}

