package com.rcf.ordersservice.service;

import com.rcf.ordersservice.dto.OrderRequest;
import com.rcf.ordersservice.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse updateOrder(Long id, OrderRequest orderRequest);
    void deleteOrder(Long id);

    List<OrderResponse> searchOrdersByEstadoOrder(String code);
}
