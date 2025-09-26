package com.rcf.usersservice.controller;

import com.rcf.usersservice.dto.UserAddressRequest;
import com.rcf.usersservice.service.UserAddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-addresses")
public class UserAddressController {
    private final UserAddressService userAddressService;

    @GetMapping
    public ResponseEntity<?> getAllUserAddresses() {
        return ResponseEntity.ok(userAddressService.getAllUserAddresses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(userAddressService.getUserAddressById(id));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserAddressesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userAddressService.getUserAddressByUserId(userId));
    }
    @PostMapping
    public ResponseEntity<?> addUserAddress(@RequestBody @Valid UserAddressRequest userAddressRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userAddressService.saveUserAddress(userAddressRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserAddress(@PathVariable Long id, @RequestBody @Valid UserAddressRequest userAddressRequest) {
        return ResponseEntity.ok(userAddressService.updateUserAddress(id, userAddressRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserAddress(@PathVariable Long id) {
        userAddressService.deleteUserAddress(id);
        return ResponseEntity.noContent().build();
    }
}
