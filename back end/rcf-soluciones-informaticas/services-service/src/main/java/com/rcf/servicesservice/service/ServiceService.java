package com.rcf.servicesservice.service;

import com.rcf.servicesservice.dto.ServiceRequest;
import com.rcf.servicesservice.dto.ServiceResponse;

import java.util.List;

public interface ServiceService {
    List<ServiceResponse> getAllServices();
    ServiceResponse getServiceById(Long id);
    ServiceResponse getServiceByCode(String code);
    ServiceResponse createService(ServiceRequest serviceRequest);
    ServiceResponse updateService(Long id, ServiceRequest serviceRequest);
    void deleteService(Long id);
}
