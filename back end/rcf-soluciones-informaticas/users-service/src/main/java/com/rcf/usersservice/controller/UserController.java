package com.rcf.usersservice.controller;

import com.rcf.usersservice.dto.UserRequest;
import com.rcf.usersservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByFullNameContainingIgnoreCase(username));
    }
    @GetMapping("/roleId/{roleId}")
    public ResponseEntity<?> getUsersByRoleId(@PathVariable Long roleId) {
        return ResponseEntity.ok(userService.getUserByRole_Id(roleId));
    }
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
