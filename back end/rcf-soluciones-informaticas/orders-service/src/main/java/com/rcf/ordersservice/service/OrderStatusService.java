package com.rcf.ordersservice.service;

import com.rcf.ordersservice.dto.OrderStatusRequest;
import com.rcf.ordersservice.dto.OrderStatusResponse;

import java.util.List;

public interface OrderStatusService {
    List<OrderStatusResponse> getAllOrderStatuses();
    OrderStatusResponse getOrderStatusById(Long id);
    OrderStatusResponse saveOrderStatus(OrderStatusRequest orderStatusRequest);
    OrderStatusResponse updateOrderStatus(Long id, OrderStatusRequest orderStatusRequest);
    void deleteOrderStatus(Long id);
}
