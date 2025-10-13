package com.rcf.servicesservice.repository;

import com.rcf.servicesservice.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Optional<Service> findByCode(String code);
    List<Service> findByNameContaining(String name);
}
