package com.rcf.usersservice.service.impl;

import com.rcf.usersservice.dto.UserAddressRequest;
import com.rcf.usersservice.dto.UserAddressResponse;
import com.rcf.usersservice.exception.ResourceNotFound;
import com.rcf.usersservice.model.UserAddress;
import com.rcf.usersservice.repository.UserAddressRepository;
import com.rcf.usersservice.service.UserAddressService;
import com.rcf.usersservice.util.UserAddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressRepository userAddressRepository;
    private final UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddressResponse> getAllUserAddresses() {
        return userAddressMapper.toDtoList(userAddressRepository.findAll());
    }

    @Override
    public UserAddressResponse getUserAddressById(Long id) {
        return userAddressMapper.toDto(userAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User address not found with id: " + id)
        ));
    }

    @Override
    public UserAddressResponse getUserAddressByUserId(Long userId) {
        return userAddressMapper.toDto(userAddressRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFound("User address not found with user id: " + userId)
        ));
    }

    @Override
    public UserAddressResponse saveUserAddress(UserAddressRequest userAddressRequest) {
        return userAddressMapper.toDto(userAddressRepository.save(userAddressMapper.toEntity(userAddressRequest)));
    }

    @Override
    public UserAddressResponse updateUserAddress(Long id, UserAddressRequest userAddressRequest) {
        UserAddress userAddressFound = userAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User address not found with id: " + id)
        );
        userAddressFound.setUser(userAddressMapper.toEntity(userAddressRequest).getUser());
        userAddressFound.setAddress(userAddressRequest.address());
        userAddressFound.setReferenceAddress(userAddressRequest.referenceAddress());
        userAddressFound.setCity(userAddressMapper.toEntity(userAddressRequest).getCity());
        userAddressFound.setPostalCode(userAddressRequest.postalCode());

        return userAddressMapper.toDto(userAddressRepository.save(userAddressFound));
    }

    @Override
    public void deleteUserAddress(Long id) {
        UserAddress userAddressFound = userAddressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("User address not found with id: " + id)
        );
        userAddressRepository.delete(userAddressFound);

    }
}
