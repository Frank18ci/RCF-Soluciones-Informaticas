package com.rcf.ordersservice.service.impl;

import com.rcf.ordersservice.dto.OrderItemRequest;
import com.rcf.ordersservice.dto.OrderItemResponse;
import com.rcf.ordersservice.exception.ResourceNotFound;
import com.rcf.ordersservice.model.OrderItem;
import com.rcf.ordersservice.repository.OrderItemRepository;
import com.rcf.ordersservice.service.OrderItemService;
import com.rcf.ordersservice.util.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemMapper.toDtoList(orderItemRepository.findAll());
    }

    @Override
    public OrderItemResponse getOrderItemById(Long id) {
        return orderItemMapper.toDto(orderItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order item not found with id: " + id)
        ));
    }

    @Override
    public OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest) {
        return orderItemMapper.toDto(orderItemRepository.save(orderItemMapper.toEntity(orderItemRequest)));
    }

    @Override
    public OrderItemResponse updateOrderItem(Long id, OrderItemRequest orderItemRequest) {
        OrderItem orderItemFound = orderItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order item not found with id: " + id)
        );

        orderItemFound.setOrder(orderItemMapper.toEntity(orderItemRequest).getOrder());
        orderItemFound.setServiceId(orderItemRequest.serviceId());
        orderItemFound.setProductId(orderItemRequest.productId());
        orderItemFound.setQty(orderItemRequest.qty());
        orderItemFound.setUnitPriceCents(orderItemRequest.unitPriceCents());
        orderItemFound.setTaxRate(orderItemRequest.taxRate());
        orderItemFound.setTotalLineCents(orderItemRequest.totalLineCents());
        orderItemFound.setDiscountLineCents(orderItemRequest.discountLineCents());

        return orderItemMapper.toDto(orderItemRepository.save(orderItemFound));
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItemFound = orderItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Order item not found with id: " + id)
        );
        orderItemRepository.delete(orderItemFound);
    }
}
