package com.rcf.usersservice.util;

import com.rcf.usersservice.dto.RolRequest;
import com.rcf.usersservice.dto.RolResponse;
import com.rcf.usersservice.model.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {
    Rol toEntity(RolRequest rolRequest);
    RolResponse toDto(Rol rol);
    List<RolResponse> toDtoList(List<Rol> rols);
}
