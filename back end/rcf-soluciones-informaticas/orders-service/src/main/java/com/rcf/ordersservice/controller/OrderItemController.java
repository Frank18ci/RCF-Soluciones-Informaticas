package com.rcf.ordersservice.controller;

import com.rcf.ordersservice.dto.OrderItemRequest;
import com.rcf.ordersservice.service.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;
    @GetMapping
    public ResponseEntity<?> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemsByOrderId(@PathVariable Long id) {
        return ResponseEntity.ok(orderItemService.getOrderItemById(id));
    }
    @PostMapping
    public ResponseEntity<?> addOrderItem(@RequestBody @Valid OrderItemRequest orderItemRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.createOrderItem(orderItemRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable Long id, @RequestBody @Valid OrderItemRequest orderItemRequest) {
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItemRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
