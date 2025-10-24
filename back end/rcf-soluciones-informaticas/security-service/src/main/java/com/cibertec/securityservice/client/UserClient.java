package com.cibertec.securityservice.client;

import com.cibertec.securityservice.client.dto.UserRequest;
import com.cibertec.securityservice.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-service")
public interface UserClient {
    @GetMapping("/users/email/{email}")
    UserResponse getUserByEmail(@PathVariable String email);

    @PostMapping("/users")
    UserResponse createUser(@RequestBody UserRequest userRequest);
}
