package com.rcf.usersservice.controller;

import com.rcf.usersservice.dto.CityRequest;
import com.rcf.usersservice.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(cityService.getAllCities());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }
    @GetMapping("name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        return ResponseEntity.ok(cityService.getCityByName(name));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CityRequest cityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.saveCity(cityRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CityRequest cityRequest) {
        return ResponseEntity.ok(cityService.updateCity(id, cityRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}
