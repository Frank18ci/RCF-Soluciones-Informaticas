package com.rcf.usersservice.util;

import com.rcf.usersservice.dto.UserAddressRequest;
import com.rcf.usersservice.dto.UserAddressResponse;
import com.rcf.usersservice.model.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CityMapper.class})
public interface UserAddressMapper {
    @Mapping(source = "userId", target = "user.id")
    UserAddress toEntity(UserAddressRequest userAddressRequest);
    UserAddressResponse toDto(UserAddress userAddress);
    List<UserAddressResponse> toDtoList(List<UserAddress> userAddressList);
}
