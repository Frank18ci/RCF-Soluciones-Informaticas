package com.rcf.usersservice.service;

import com.rcf.usersservice.dto.UserAddressRequest;
import com.rcf.usersservice.dto.UserAddressResponse;
import com.rcf.usersservice.model.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddressResponse> getAllUserAddresses();
    UserAddressResponse getUserAddressById(Long id);
    UserAddressResponse getUserAddressByUserId(Long userId);
    UserAddressResponse saveUserAddress(UserAddressRequest userAddressRequest);
    UserAddressResponse updateUserAddress(Long id, UserAddressRequest userAddressRequest);
    void deleteUserAddress(Long id);
}
