package com.rcf.productsservice.service.impl;

import com.rcf.productsservice.dto.DiscountRequest;
import com.rcf.productsservice.dto.DiscountResponse;
import com.rcf.productsservice.exception.ResourceNotFound;
import com.rcf.productsservice.model.Discount;
import com.rcf.productsservice.repository.DiscountRepository;
import com.rcf.productsservice.service.DiscountService;
import com.rcf.productsservice.util.DiscountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;
    @Override
    public List<DiscountResponse> getAllDiscounts() {
        return discountMapper.toDtoList(discountRepository.findAll());
    }

    @Override
    public DiscountResponse getDiscountById(Long id) {
        return discountMapper.toDto(discountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound ("Discount not found with id:"+ id))
        );
    }

    @Override
    public DiscountResponse createDiscount(DiscountRequest discountRequest) {
        return discountMapper.toDto(discountRepository.save(discountMapper.toEntity(discountRequest)));
    }

    @Override
    public DiscountResponse updateDiscount(Long id, DiscountRequest discountRequest) {
        Discount discountFound = discountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound ("Discount not found with id:"+ id)
        );
        discountFound.setCode(discountRequest.code());
        discountFound.setDescription(discountRequest.description());
        discountFound.setDiscountType(discountMapper.toEntity(discountRequest).getDiscountType());
        discountFound.setValue(discountRequest.value());
        discountFound.setActive(discountRequest.active());
        discountFound.setStartDate(discountRequest.startDate());
        discountFound.setEndDate(discountRequest.endDate());
        return discountMapper.toDto(discountRepository.save(discountFound));
    }

    @Override
    public void deleteDiscount(Long id) {
        Discount discountFount = discountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound ("Discount not found with id:"+ id)
        );
        discountRepository.delete(discountFount);

    }
}
