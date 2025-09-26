package com.rcf.usersservice.repository;

import com.rcf.usersservice.model.StateRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRegionRepository extends JpaRepository<StateRegion, Long> {
    Optional<StateRegion> findByName(String name);
}
