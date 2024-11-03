package com.msdproj.MSDProj.repository;

import com.msdproj.MSDProj.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByEmail(String email);
}
