package com.rcf.usersservice.util;

import com.rcf.usersservice.dto.UserRequest;
import com.rcf.usersservice.dto.UserResponse;
import com.rcf.usersservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "roleId", target = "role.id")
    User toEntity(UserRequest userRequest);
    UserResponse toDto(User user);
    List<UserResponse> toDtoList(List<User> users);
}
