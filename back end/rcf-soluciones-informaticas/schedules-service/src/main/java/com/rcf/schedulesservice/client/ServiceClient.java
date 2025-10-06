package com.rcf.schedulesservice.client;

import com.rcf.schedulesservice.client.configuration.FeignConfig;
import com.rcf.schedulesservice.client.dto.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "services-service", configuration = FeignConfig.class)
public interface ServiceClient {
    @GetMapping("/services/{id}")
    ServiceResponse getServiceById(@PathVariable Long id);
}
