package com.rcf.servicesservice.controller;

import com.rcf.servicesservice.dto.ServiceRequest;
import com.rcf.servicesservice.service.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping
    public ResponseEntity<?> getAllServices(){
        return ResponseEntity.ok(serviceService.getAllServices());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceById(@PathVariable Long id){
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }
    @GetMapping("/code/{code}")
    public ResponseEntity<?> getServiceByCode(@PathVariable String code) {
        return ResponseEntity.ok(serviceService.getServiceByCode(code));
    }
    @PostMapping
    public ResponseEntity<?> createService(@RequestBody @Valid ServiceRequest serviceRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.createService(serviceRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateService(@PathVariable Long id, @RequestBody @Valid ServiceRequest serviceRequest) {
        return ResponseEntity.ok(serviceService.updateService(id, serviceRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchServices(@RequestParam String name) {
        return ResponseEntity.ok(serviceService.searchServicesByName(name));
    }
}
