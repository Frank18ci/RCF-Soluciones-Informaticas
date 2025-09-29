package com.rcf.schedulesservice.service.impl;

import com.rcf.schedulesservice.dto.ServiceBookingRequest;
import com.rcf.schedulesservice.dto.ServiceBookingResponse;
import com.rcf.schedulesservice.exception.ResourceNotFound;
import com.rcf.schedulesservice.model.ServiceBooking;
import com.rcf.schedulesservice.repository.ServiceBookingRepository;
import com.rcf.schedulesservice.service.ServiceBookingService;
import com.rcf.schedulesservice.uti.ServiceBookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceBookingServiceImpl implements ServiceBookingService {
    private final ServiceBookingRepository serviceBookingRepository;
    private final ServiceBookingMapper serviceBookingMapper;

    @Override
    public List<ServiceBookingResponse> getAllServiceBookings() {
        return serviceBookingMapper.toDtoList(serviceBookingRepository.findAll());
    }

    @Override
    public ServiceBookingResponse getServiceBookingById(Long id) {
        return serviceBookingMapper.toDto(serviceBookingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Service booking not found with id: " + id)
        ));
    }

    @Override
    public ServiceBookingResponse createServiceBooking(ServiceBookingRequest serviceBookingRequest) {
        return serviceBookingMapper.toDto(serviceBookingRepository.save(serviceBookingMapper.toEntity(serviceBookingRequest)));
    }

    @Override
    public ServiceBookingResponse updateServiceBooking(Long id, ServiceBookingRequest serviceBookingRequest) {
        ServiceBooking serviceBookingFound = serviceBookingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Service booking not found with id: " + id)
        );
        serviceBookingFound.setOrderItemId(serviceBookingRequest.orderItemId());
        serviceBookingFound.setUserId(serviceBookingRequest.userId());
        serviceBookingFound.setServiceId(serviceBookingRequest.serviceId());
        serviceBookingFound.setTechnicianUserId(serviceBookingRequest.technicianUserId());
        serviceBookingFound.setBookingStatus(serviceBookingMapper.toEntity(serviceBookingRequest).getBookingStatus());
        serviceBookingFound.setScheduledStart(serviceBookingMapper.toEntity(serviceBookingRequest).getScheduledStart());
        serviceBookingFound.setScheduledEnd(serviceBookingMapper.toEntity(serviceBookingRequest).getScheduledEnd());
        serviceBookingFound.setAddressSnapshot(serviceBookingMapper.toEntity(serviceBookingRequest).getAddressSnapshot());
        serviceBookingFound.setNotes(serviceBookingRequest.notes());

        return serviceBookingMapper.toDto(serviceBookingRepository.save(serviceBookingFound));
    }

    @Override
    public void deleteServiceBooking(Long id) {
        ServiceBooking serviceBookingFound = serviceBookingRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Service booking not found with id: " + id)
        );
        serviceBookingRepository.delete(serviceBookingFound);
    }
}
