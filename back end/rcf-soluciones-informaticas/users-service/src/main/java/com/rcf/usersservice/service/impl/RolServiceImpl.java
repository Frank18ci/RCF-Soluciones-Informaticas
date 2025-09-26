package com.rcf.usersservice.service.impl;

import com.rcf.usersservice.dto.RolRequest;
import com.rcf.usersservice.dto.RolResponse;
import com.rcf.usersservice.exception.ResourceNotFound;
import com.rcf.usersservice.model.Rol;
import com.rcf.usersservice.repository.RolRepository;
import com.rcf.usersservice.service.RolService;
import com.rcf.usersservice.util.RolMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Override
    public List<RolResponse> getAllRoles() {
        return rolMapper.toDtoList(rolRepository.findAll());
    }

    @Override
    public RolResponse getRoleByCode(String code) {
        return rolMapper.toDto(rolRepository.findByCode(code).orElseThrow(
                () -> new ResourceNotFound("Role not found with code: " + code)
        ));
    }

    @Override
    public RolResponse getRoleByName(String name) {
        return rolMapper.toDto(rolRepository.findByName(name).orElseThrow(
                () -> new ResourceNotFound("Role not found with name: " + name)
        ));
    }

    @Override
    public RolResponse getRoleById(Long id) {
        return rolMapper.toDto(rolRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Role not found with id: " + id)
        ));
    }

    @Override
    public RolResponse saveRole(RolRequest rolRequest) {
        return rolMapper.toDto(rolRepository.save(rolMapper.toEntity(rolRequest)));
    }

    @Override
    public RolResponse updateRole(Long id, RolRequest rolRequest) {
        Rol rolFound = rolRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Role not found with id: " + id)
        );
        rolFound.setName(rolRequest.name());
        rolFound.setCode(rolRequest.code());
        return rolMapper.toDto(rolRepository.save(rolFound));
    }

    @Override
    public void deleteRole(Long id) {
        Rol rolFound = rolRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Role not found with id: " + id)
        );
        rolRepository.delete(rolFound);
    }
}
