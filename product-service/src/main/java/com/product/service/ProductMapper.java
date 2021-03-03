package com.product.service;

import com.product.dto.ProductDto;
import com.product.model.Product;

public final class ProductMapper {
    private ProductMapper() {
    }

    public static ProductDto productEntityToProductDto(Product product, Currency currency) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());

        if (currency == Currency.USD) {
            productDto.setPrice(product.getPrice());
        } else {
            CurrencyAPIClient client = new CurrencyAPIClient();
            double price = client.getPriceForUSD(product.getPrice(), currency);
            productDto.setPrice(price);
        }

        productDto.setDescription(product.getDescription());
        productDto.setViews(product.getViews());
        return productDto;
    }
}
