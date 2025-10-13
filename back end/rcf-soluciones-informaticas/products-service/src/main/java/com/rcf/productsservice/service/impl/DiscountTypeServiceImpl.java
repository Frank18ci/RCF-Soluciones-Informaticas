package com.rcf.productsservice.service.impl;

import com.rcf.productsservice.dto.DiscountTypeRequest;
import com.rcf.productsservice.dto.DiscountTypeResponse;
import com.rcf.productsservice.exception.ResourceNotFound;
import com.rcf.productsservice.model.DiscountType;
import com.rcf.productsservice.repository.DiscountTypeRepository;
import com.rcf.productsservice.service.DiscountTypeService;
import com.rcf.productsservice.util.DiscountTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountTypeServiceImpl implements DiscountTypeService {
    private final DiscountTypeRepository discountTypeRepository;
    private final DiscountTypeMapper discountTypeMapper;

    @Override
    public List<DiscountTypeResponse> getAllDiscountTypes() {
        return discountTypeMapper.tpDtoList(discountTypeRepository.findAll());
    }

    @Override
    public DiscountTypeResponse getDiscountTypeById(Long id) {
        return discountTypeMapper.toDto(discountTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Discount type not found with id: " + id)
        ));
    }

    @Override
    public DiscountTypeResponse saveDiscountType(DiscountTypeRequest discountTypeRequest) {
        return discountTypeMapper.toDto(discountTypeRepository.save(
                discountTypeMapper.toEntity(discountTypeRequest)
        ));
    }

    @Override
    public DiscountTypeResponse updateDiscountType(Long id, DiscountTypeRequest discountTypeRequest) {
        DiscountType discountTypeFound = discountTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Discount type not found with id: " + id)
        );
        discountTypeFound.setName(discountTypeRequest.name());

        return discountTypeMapper.toDto(discountTypeRepository.save(discountTypeFound));
    }

    @Override
    public void deleteDiscountType(Long id) {
        DiscountType discountTypeFound = discountTypeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Discount type not found with id: " + id)
        );
        discountTypeRepository.delete(discountTypeFound);
    }


}
