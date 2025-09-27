package com.rcf.ordersservice.controller;

import com.rcf.ordersservice.dto.OrderStatusRequest;
import com.rcf.ordersservice.service.OrderStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-status")
@RequiredArgsConstructor
public class OrderStatusController {
    private final OrderStatusService orderStatusService;
    @GetMapping
    public ResponseEntity<?> getAllOrderStatuses() {
        return ResponseEntity.ok(orderStatusService.getAllOrderStatuses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderStatusById(@PathVariable Long id) {
        return ResponseEntity.ok(orderStatusService.getOrderStatusById(id));
    }
    @PostMapping
    public ResponseEntity<?> createOrderStatus(@RequestBody @Valid OrderStatusRequest orderStatusRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderStatusService.saveOrderStatus(orderStatusRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody @Valid OrderStatusRequest orderStatusRequest) {
        return ResponseEntity.ok(orderStatusService.updateOrderStatus(id, orderStatusRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderStatus(@PathVariable Long id) {
        orderStatusService.deleteOrderStatus(id);
        return ResponseEntity.noContent().build();
    }
}
