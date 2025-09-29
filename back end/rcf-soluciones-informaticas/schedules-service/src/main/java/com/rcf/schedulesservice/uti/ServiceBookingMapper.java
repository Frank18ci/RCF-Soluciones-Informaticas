package com.rcf.schedulesservice.uti;

import com.rcf.schedulesservice.dto.ServiceBookingRequest;
import com.rcf.schedulesservice.dto.ServiceBookingResponse;
import com.rcf.schedulesservice.model.ServiceBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookingStatusMapper.class})
public interface ServiceBookingMapper {
    @Mapping(source = "bookingStatusId", target = "bookingStatus.id")
    ServiceBooking toEntity(ServiceBookingRequest serviceBookingRequest);
    ServiceBookingResponse toDto(ServiceBooking serviceBooking);
    List<ServiceBookingResponse> toDtoList(List<ServiceBooking> serviceBookings);
}
