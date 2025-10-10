package com.rcf.productsservice.util;

import com.rcf.productsservice.dto.ProductRequest;
import com.rcf.productsservice.dto.ProductResponse;
import com.rcf.productsservice.model.Product;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, DiscountMapper.class})
public interface ProductMapper {

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "discountId", target = "discount.id")
    @Mapping(source = "image", target = "imgUrl", qualifiedByName = "extractImageName")
    Product toEntity(ProductRequest productRequest, @Context String sku);

    ProductResponse toDto(Product product);

    List<ProductResponse> toDtoList(List<Product> products);

    @Named("extractImageName")
    static String extractImageName(MultipartFile image, @Context String sku) {
        if (image == null || image.isEmpty()) {
            return null;
        }
        // obtiene la extensión del archivo
        String originalName = image.getOriginalFilename();
        if(originalName == null) {
            return null;
        }
        // devuelve solo el nombre compuesto (sku + extensión)
        return sku + ".jpg";
    }
}