package com.msdproj.MSDProj.service;

import com.msdproj.MSDProj.entity.ServiceRecord;
import com.msdproj.MSDProj.repository.ServiceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;

    @Autowired
    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository) {
        this.serviceRecordRepository = serviceRecordRepository;
    }

    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }

    public Optional<ServiceRecord> getServiceRecordById(Long id) {
        return serviceRecordRepository.findById(id);
    }

    public ServiceRecord addServiceRecord(ServiceRecord serviceRecord) {
        return serviceRecordRepository.save(serviceRecord);
    }

    public ServiceRecord updateServiceRecord(Long id, ServiceRecord updatedRecord) {
        Optional<ServiceRecord> existingRecord = serviceRecordRepository.findById(id);
        if (existingRecord.isPresent()) {
            ServiceRecord record = existingRecord.get();
            record.setVehicle(updatedRecord.getVehicle());
            record.setServiceDate(updatedRecord.getServiceDate());
            record.setServiceType(updatedRecord.getServiceType());
            record.setDescription(updatedRecord.getDescription());
            record.setCompleted(updatedRecord.getCompleted());
            return serviceRecordRepository.save(record);
        } else {
            throw new RuntimeException("Service Record not found with ID " + id);
        }
    }

    public void deleteServiceRecord(Long id) {
        serviceRecordRepository.deleteById(id);
    }

    public List<ServiceRecord> getServiceRecordsByVehicleId(Long vehicleId) {
        return serviceRecordRepository.findByVehicleId(vehicleId);
    }

    public List<ServiceRecord> getServiceRecordsByCompletionStatus(Boolean completed) {
        return serviceRecordRepository.findByCompleted(completed);
    }
}

