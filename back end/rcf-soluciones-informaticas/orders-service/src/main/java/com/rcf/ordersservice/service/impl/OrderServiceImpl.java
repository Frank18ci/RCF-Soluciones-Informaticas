package com.rcf.ordersservice.service.impl;

import com.rcf.ordersservice.dto.OrderRequest;
import com.rcf.ordersservice.dto.OrderResponse;
import com.rcf.ordersservice.exception.ResourceNotFound;
import com.rcf.ordersservice.model.Order;
import com.rcf.ordersservice.repository.OrderRepository;
import com.rcf.ordersservice.service.OrderService;
import com.rcf.ordersservice.util.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order not found with id: " + id)
        ));
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        return orderMapper.toDto(orderRepository.save(orderMapper.toEntity(orderRequest)));
    }

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest orderRequest) {
        Order orderFound = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order not found with id: " + id)
        );

        orderFound.setUserId(orderRequest.userId());
        orderFound.setCurrencyCode(orderRequest.currencyCode());
        orderFound.setPaymentMethodCode(orderRequest.paymentMethodCode());
        orderFound.setSubtotalCents(orderRequest.subtotalCents());
        orderFound.setTaxTotalCents(orderRequest.taxTotalCents());
        orderFound.setTotalCents(orderRequest.totalCents());
        orderFound.setDiscountCents(orderRequest.discountCents());
        orderFound.setNotes(orderRequest.notes());

        return orderMapper.toDto(orderRepository.save(orderFound));
    }

    @Override
    public void deleteOrder(Long id) {
        Order orderFound = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order not found with id: " + id)
        );
        orderRepository.delete(orderFound);
    }
}
