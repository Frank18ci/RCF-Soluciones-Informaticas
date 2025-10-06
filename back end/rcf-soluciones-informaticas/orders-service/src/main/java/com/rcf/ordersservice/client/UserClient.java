package com.rcf.ordersservice.client;

import com.rcf.ordersservice.client.configuration.FeignConfig;
import com.rcf.ordersservice.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-service", configuration = FeignConfig.class)
public interface UserClient {
    @GetMapping("/users/{id}")
    UserResponse findById(@PathVariable Long id);
}
