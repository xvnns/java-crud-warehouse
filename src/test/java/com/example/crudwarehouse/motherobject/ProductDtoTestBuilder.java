package com.example.crudwarehouse.motherobject;

import com.example.crudwarehouse.dto.ProductDto;
import com.example.crudwarehouse.model.Category;

import java.math.BigDecimal;
import static com.example.crudwarehouse.util.TestUtil.createRandomString;

public class ProductDtoTestBuilder {
    private ProductDto productDto;

    public ProductDtoTestBuilder() {
        this.productDto = new ProductDto();
    }

    public static ProductDtoTestBuilder aProductDto() {
        return new ProductDtoTestBuilder();
    }

    public ProductDtoTestBuilder withRandomFields() {
        this.productDto = ProductDto.builder()
                .name(createRandomString(20))
                .vendorCode(createRandomString(30))
                .description(createRandomString(200))
                .category(Category.LAPTOP)
                .price(new BigDecimal(100))
                .quantity(20L)
                .build();
        return this;
    }

    public ProductDto build() {
        return this.productDto;
    }
}
