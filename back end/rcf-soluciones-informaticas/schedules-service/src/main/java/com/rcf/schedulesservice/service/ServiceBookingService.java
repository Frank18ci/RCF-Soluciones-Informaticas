package com.rcf.schedulesservice.service;

import com.rcf.schedulesservice.dto.ServiceBookingRequest;
import com.rcf.schedulesservice.dto.ServiceBookingResponse;

import java.util.List;

public interface ServiceBookingService {
    List<ServiceBookingResponse> getAllServiceBookings();
    ServiceBookingResponse getServiceBookingById(Long id);
    ServiceBookingResponse createServiceBooking(ServiceBookingRequest serviceBookingRequest);
    ServiceBookingResponse updateServiceBooking(Long id, ServiceBookingRequest serviceBookingRequest);
    void deleteServiceBooking(Long id);
}
