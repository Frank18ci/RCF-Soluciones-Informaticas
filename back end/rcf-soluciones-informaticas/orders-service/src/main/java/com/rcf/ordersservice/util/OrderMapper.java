package com.rcf.ordersservice.util;

import com.rcf.ordersservice.dto.OrderRequest;
import com.rcf.ordersservice.dto.OrderResponse;
import com.rcf.ordersservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderStatusMapper.class})
public interface OrderMapper {
    @Mapping(source = "orderStatusId", target = "orderStatus.id")
    Order toEntity(OrderRequest orderRequest);
    OrderResponse toDto(Order order);
    List<OrderResponse> toDtoList(List<Order> orders);
}
