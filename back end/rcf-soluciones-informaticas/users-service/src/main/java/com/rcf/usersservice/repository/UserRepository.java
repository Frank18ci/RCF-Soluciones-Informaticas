package com.rcf.usersservice.repository;

import com.rcf.usersservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    List<User> findByEmailContainingIgnoreCase(String email);
    List<User> findByFullNameContainingIgnoreCase(String fullName);
    List<User> findByRole_Id(Long rolId);
}
