package com.rcf.servicesservice.util;

import com.rcf.servicesservice.dto.ServiceRequest;
import com.rcf.servicesservice.dto.ServiceResponse;
import com.rcf.servicesservice.model.Service;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    Service toEntity(ServiceRequest serviceRequest);
    ServiceResponse toDto(Service service);
    List<ServiceResponse> toDtoList(List<Service> services);
}
