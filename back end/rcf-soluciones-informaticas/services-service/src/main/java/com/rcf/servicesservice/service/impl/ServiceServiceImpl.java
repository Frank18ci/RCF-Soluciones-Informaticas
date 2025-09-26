package com.rcf.servicesservice.service.impl;

import com.rcf.servicesservice.dto.ServiceRequest;
import com.rcf.servicesservice.dto.ServiceResponse;
import com.rcf.servicesservice.exception.ResourceNotFound;
import com.rcf.servicesservice.repository.ServiceRepository;
import com.rcf.servicesservice.service.ServiceService;
import com.rcf.servicesservice.util.ServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public List<ServiceResponse> getAllServices() {
        return serviceMapper.toDtoList(serviceRepository.findAll());
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        return serviceMapper.toDto(serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Service not found with id: " + id)
        ));
    }

    @Override
    public ServiceResponse getServiceByCode(String code) {
        return serviceMapper.toDto(serviceRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFound("Service not found with code: " + code)
        ));
    }

    @Override
    public ServiceResponse createService(ServiceRequest serviceRequest) {
        return serviceMapper.toDto(serviceRepository.save(serviceMapper.toEntity(serviceRequest)));
    }

    @Override
    public ServiceResponse updateService(Long id, ServiceRequest serviceRequest) {
        com.rcf.servicesservice.model.Service serviceFound = serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Service not found with id: " + id)
        );
        serviceFound.setCode(serviceRequest.code());
        serviceFound.setName(serviceRequest.name());
        serviceFound.setDescription(serviceRequest.description());
        serviceFound.setBasePriceCents(serviceRequest.basePriceCents());
        serviceFound.setTaxRate(serviceRequest.taxRate());
        serviceFound.setDurationMinutes(serviceRequest.durationMinutes());
        serviceFound.setRequiresOnSite(serviceRequest.requiresOnSite());

        return serviceMapper.toDto(serviceRepository.save(serviceFound));
    }

    @Override
    public void deleteService(Long id) {
        com.rcf.servicesservice.model.Service serviceFound = serviceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Service not found with id: " + id)
        );
        serviceRepository.delete(serviceFound);
    }
}
