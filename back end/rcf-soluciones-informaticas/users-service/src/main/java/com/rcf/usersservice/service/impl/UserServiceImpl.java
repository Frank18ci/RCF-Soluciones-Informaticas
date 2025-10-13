package com.rcf.usersservice.service.impl;

import com.rcf.usersservice.dto.UserRequest;
import com.rcf.usersservice.dto.UserResponse;
import com.rcf.usersservice.exception.ResourceNotFound;
import com.rcf.usersservice.model.User;
import com.rcf.usersservice.repository.UserRepository;
import com.rcf.usersservice.service.UserService;
import com.rcf.usersservice.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id: " + id)
        ));
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFound("User not found with email: " + email)
        ));
    }

    @Override
    public List<UserResponse> getUserByRole_Id(Long rolId) {
        return userMapper.toDtoList(userRepository.findByRole_Id(rolId));
    }

    @Override
    public List<UserResponse> getUserByFullNameContainingIgnoreCase(String fullName) {
        return userMapper.toDtoList(userRepository.findByFullNameContainingIgnoreCase(fullName));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userRequest)));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User userFound = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id: " + id)
        );
        userFound.setEmail(userRequest.email());
        if(userRequest.password() != null && !userRequest.password().isBlank()) {
            userFound.setPassword(userRequest.password());
        }
        userFound.setFullName(userRequest.fullName());
        userFound.setRole(userMapper.toEntity(userRequest).getRole());
        userFound.setPhone(userRequest.phone());
        userFound.setActive(userRequest.active());
        return userMapper.toDto(userRepository.save(userFound));
    }

    @Override
    public void deleteUser(Long id) {
        User userFound = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User not found with id: " + id)
        );
        userRepository.delete(userFound);
    }

    @Override
    public List<UserResponse> searchByEmail(String email) {
        return userMapper.toDtoList(userRepository.findByEmailContainingIgnoreCase(email));
    }
}
