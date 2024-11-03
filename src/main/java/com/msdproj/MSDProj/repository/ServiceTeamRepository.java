package com.msdproj.MSDProj.repository;

import com.msdproj.MSDProj.entity.ServiceTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceTeamRepository extends JpaRepository<ServiceTeam, Long> {

    List<ServiceTeam> findByAvailable(Boolean available);

    List<ServiceTeam> findByRole(String role);
}

