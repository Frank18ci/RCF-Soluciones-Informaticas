package com.rcf.ordersservice.service;

import com.rcf.ordersservice.dto.OrderItemRequest;
import com.rcf.ordersservice.dto.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponse> getAllOrderItems();
    OrderItemResponse getOrderItemById(Long id);
    OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest);
    OrderItemResponse updateOrderItem(Long id, OrderItemRequest orderItemRequest);
    void deleteOrderItem(Long id);
}
