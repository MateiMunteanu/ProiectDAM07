package com.msdproj.MSDProj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String brand;

    @Column(name = "year_of_manufacture")
    private int yearOfManufacture;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String status; // e.g., "new", "second-hand"

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(length = 1000)
    private String description;

    @Column
    private String type; // e.g., "SUV", "sedan", etc.

    @Column
    private Boolean available; // to manage inventory availability
}

