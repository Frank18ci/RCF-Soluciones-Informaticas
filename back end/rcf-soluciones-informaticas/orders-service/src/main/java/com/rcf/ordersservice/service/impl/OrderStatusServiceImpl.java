package com.rcf.ordersservice.service.impl;

import com.rcf.ordersservice.dto.OrderStatusRequest;
import com.rcf.ordersservice.dto.OrderStatusResponse;
import com.rcf.ordersservice.exception.ResourceNotFound;
import com.rcf.ordersservice.model.OrderStatus;
import com.rcf.ordersservice.repository.OrderStatusRepository;
import com.rcf.ordersservice.service.OrderStatusService;
import com.rcf.ordersservice.util.OrderStatusMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapper orderStatusMapper;

    @Override
    public List<OrderStatusResponse> getAllOrderStatuses() {
        return orderStatusMapper.toDtoList(orderStatusRepository.findAll());
    }

    @Override
    public OrderStatusResponse getOrderStatusById(Long id) {
        return orderStatusMapper.toDto(orderStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order status not found with id: " + id)
        ));
    }

    @Override
    public OrderStatusResponse saveOrderStatus(OrderStatusRequest orderStatusRequest) {
        return orderStatusMapper.toDto(orderStatusRepository.save(
                orderStatusMapper.toEntity(orderStatusRequest)
        ));
    }

    @Override
    public OrderStatusResponse updateOrderStatus(Long id, OrderStatusRequest orderStatusRequest) {
        OrderStatus orderStatusFound = orderStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order status not found with id: " + id)
        );
        orderStatusFound.setCode(orderStatusRequest.code());
        orderStatusFound.setSortOrder(orderStatusRequest.sortOrder());

        return orderStatusMapper.toDto(orderStatusRepository.save(orderStatusFound));
    }

    @Override
    public void deleteOrderStatus(Long id) {
        OrderStatus orderStatusFound = orderStatusRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order status not found with id: " + id)
        );
        orderStatusRepository.delete(orderStatusFound);
    }
}
