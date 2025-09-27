package com.rcf.ordersservice.util;

import com.rcf.ordersservice.dto.OrderStatusRequest;
import com.rcf.ordersservice.dto.OrderStatusResponse;
import com.rcf.ordersservice.model.OrderStatus;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderStatusMapper {
    OrderStatus toEntity(OrderStatusRequest orderStatusRequest);
    OrderStatusResponse toDto(OrderStatus orderStatus);
    List<OrderStatusResponse> toDtoList(List<OrderStatus> orderStatuses);
}
