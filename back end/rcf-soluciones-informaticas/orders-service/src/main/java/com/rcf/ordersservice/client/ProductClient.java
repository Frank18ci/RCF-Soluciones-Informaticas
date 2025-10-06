package com.rcf.ordersservice.client;

import com.rcf.ordersservice.client.configuration.FeignConfig;
import com.rcf.ordersservice.client.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service", configuration = FeignConfig.class)
public interface ProductClient {
    @GetMapping("/products/{id}")
    ProductResponse getProductById(@PathVariable Long id);
}
