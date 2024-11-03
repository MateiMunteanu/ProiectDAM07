package com.msdproj.MSDProj.service;

import com.msdproj.MSDProj.entity.ServiceTeam;
import com.msdproj.MSDProj.repository.ServiceTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceTeamService {

    private final ServiceTeamRepository serviceTeamRepository;

    @Autowired
    public ServiceTeamService(ServiceTeamRepository serviceTeamRepository) {
        this.serviceTeamRepository = serviceTeamRepository;
    }

    public List<ServiceTeam> getAllServiceTeamMembers() {
        return serviceTeamRepository.findAll();
    }

    public Optional<ServiceTeam> getServiceTeamMemberById(Long id) {
        return serviceTeamRepository.findById(id);
    }

    public ServiceTeam addServiceTeamMember(ServiceTeam serviceTeamMember) {
        return serviceTeamRepository.save(serviceTeamMember);
    }

    public ServiceTeam updateServiceTeamMember(Long id, ServiceTeam updatedMember) {
        Optional<ServiceTeam> existingMember = serviceTeamRepository.findById(id);
        if (existingMember.isPresent()) {
            ServiceTeam member = existingMember.get();
            member.setName(updatedMember.getName());
            member.setRole(updatedMember.getRole());
            member.setAvailable(updatedMember.getAvailable());
            return serviceTeamRepository.save(member);
        } else {
            throw new RuntimeException("Service Team Member not found with ID " + id);
        }
    }

    public void deleteServiceTeamMember(Long id) {
        serviceTeamRepository.deleteById(id);
    }

    public List<ServiceTeam> getAvailableTeamMembers(Boolean available) {
        return serviceTeamRepository.findByAvailable(available);
    }

    public List<ServiceTeam> getTeamMembersByRole(String role) {
        return serviceTeamRepository.findByRole(role);
    }
}

