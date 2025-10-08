package com.rcf.usersservice.controller;

import com.rcf.usersservice.dto.RolRequest;
import com.rcf.usersservice.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("roles")
public class RolController {
    private final RolService rolService;
    @GetMapping
    public ResponseEntity<?> getAllRol(){
        return ResponseEntity.ok(rolService.getAllRoles());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRolById(@PathVariable Long id){
        return ResponseEntity.ok(rolService.getRoleById(id));
    }
    @PostMapping
    public ResponseEntity<?> addRol(@RequestBody @Valid RolRequest rolRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(rolService.saveRole(rolRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRol(@PathVariable Long id, @RequestBody @Valid RolRequest rolRequest) {
        return ResponseEntity.ok(rolService.updateRole(id, rolRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable Long id) {
        rolService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
