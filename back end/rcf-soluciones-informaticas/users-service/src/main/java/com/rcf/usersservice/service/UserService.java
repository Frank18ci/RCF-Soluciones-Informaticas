package com.rcf.usersservice.service;

import com.rcf.usersservice.dto.UserRequest;
import com.rcf.usersservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    UserResponse getUserByEmail(String email);
    List<UserResponse> getUserByRole_Id(Long rolId);
    List<UserResponse> getUserByFullNameContainingIgnoreCase(String fullName);
    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(Long id, UserRequest userRequest);
    void deleteUser(Long id);
}
