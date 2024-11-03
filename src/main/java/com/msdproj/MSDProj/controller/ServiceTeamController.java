package com.msdproj.MSDProj.controller;

import com.msdproj.MSDProj.entity.ServiceTeam;
import com.msdproj.MSDProj.service.ServiceTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-teams")
public class ServiceTeamController {

    private final ServiceTeamService serviceTeamService;

    @Autowired
    public ServiceTeamController(ServiceTeamService serviceTeamService) {
        this.serviceTeamService = serviceTeamService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceTeam>> getAllServiceTeamMembers() {
        return ResponseEntity.ok(serviceTeamService.getAllServiceTeamMembers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceTeam> getServiceTeamMemberById(@PathVariable Long id) {
        return serviceTeamService.getServiceTeamMemberById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ServiceTeam> addServiceTeamMember(@RequestBody ServiceTeam serviceTeamMember) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceTeamService.addServiceTeamMember(serviceTeamMember));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceTeam> updateServiceTeamMember(@PathVariable Long id, @RequestBody ServiceTeam serviceTeamMember) {
        try {
            return ResponseEntity.ok(serviceTeamService.updateServiceTeamMember(id, serviceTeamMember));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceTeamMember(@PathVariable Long id) {
        serviceTeamService.deleteServiceTeamMember(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available/{available}")
    public ResponseEntity<List<ServiceTeam>> getAvailableTeamMembers(@PathVariable Boolean available) {
        return ResponseEntity.ok(serviceTeamService.getAvailableTeamMembers(available));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<ServiceTeam>> getTeamMembersByRole(@PathVariable String role) {
        return ResponseEntity.ok(serviceTeamService.getTeamMembersByRole(role));
    }
}

