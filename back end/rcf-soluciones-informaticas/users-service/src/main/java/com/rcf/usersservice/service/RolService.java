package com.rcf.usersservice.service;

import com.rcf.usersservice.dto.RolRequest;
import com.rcf.usersservice.dto.RolResponse;

import java.util.List;

public interface RolService {
    List<RolResponse> getAllRoles();
    RolResponse getRoleByCode(String code);
    RolResponse getRoleByName(String name);
    RolResponse getRoleById(Long id);
    RolResponse saveRole(RolRequest rolRequest);
    RolResponse updateRole(Long id, RolRequest rolRequest);
    void deleteRole(Long id);
}
