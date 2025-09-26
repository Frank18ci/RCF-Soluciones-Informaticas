package com.rcf.usersservice.repository;

import com.rcf.usersservice.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    Optional<UserAddress> findByUserId(Long userId);
}
