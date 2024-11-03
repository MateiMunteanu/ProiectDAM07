package com.msdproj.MSDProj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "service_teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String role; // e.g., "Mechanic", "Technician"

    private Boolean available; // Availability status for scheduling
}

