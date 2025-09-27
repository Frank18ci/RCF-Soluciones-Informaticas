package com.rcf.ordersservice.util;

import com.rcf.ordersservice.dto.OrderItemRequest;
import com.rcf.ordersservice.dto.OrderItemResponse;
import com.rcf.ordersservice.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface OrderItemMapper {
    @Mapping(source = "orderId", target = "order.id")
    OrderItem toEntity(OrderItemRequest orderItemRequest);
    OrderItemResponse toDto(OrderItem orderItem);
    List<OrderItemResponse> toDtoList(List<OrderItem> orderItems);
}
