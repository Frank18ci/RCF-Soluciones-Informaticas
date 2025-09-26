package com.rcf.usersservice.controller;

import com.rcf.usersservice.dto.StateRegionRequest;
import com.rcf.usersservice.service.StateRegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/state-regions")
@RequiredArgsConstructor
public class StateRegionController {
    private final StateRegionService stateRegionService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(stateRegionService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(stateRegionService.findById(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByCountryId(@PathVariable String name){
        return ResponseEntity.ok(stateRegionService.findByName(name));
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid StateRegionRequest stateRegionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(stateRegionService.createStateRegion(stateRegionRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid StateRegionRequest stateRegionRequest){
        return ResponseEntity.ok(stateRegionService.updateStateRegion(id, stateRegionRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        stateRegionService.deleteStateRegion(id);
        return ResponseEntity.noContent().build();
    }
}
