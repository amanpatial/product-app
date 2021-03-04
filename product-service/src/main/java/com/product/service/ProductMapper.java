package com.product.service;

import com.product.dto.ProductDto;
import com.product.model.Product;

public final class ProductMapper {
    private ProductMapper() {
    }

    public static ProductDto productEntityToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setViews(product.getViews());
        return productDto;
    }
}
