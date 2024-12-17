package com.example.crudwarehouse.motherobject;

import com.example.crudwarehouse.dto.ProductInfoDto;
import com.example.crudwarehouse.model.Category;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.crudwarehouse.util.TestUtil.createRandomString;

public class ProductInfoDtoTestBuilder {
    private ProductInfoDto productInfoDto;

    public ProductInfoDtoTestBuilder() {
        this.productInfoDto = new ProductInfoDto();
    }

    public static ProductInfoDtoTestBuilder aProductInfoDto() {
        return new ProductInfoDtoTestBuilder();
    }

    public ProductInfoDtoTestBuilder withRandomFields() {
        this.productInfoDto = ProductInfoDto.builder()
                .id(UUID.randomUUID())
                .name(createRandomString(20))
                .vendorCode(createRandomString(30))
                .description(createRandomString(200))
                .category(Category.LAPTOP)
                .price(new BigDecimal(100))
                .quantity(20L)
                .createTs(LocalDateTime.now())
                .quantityUpdateDate(LocalDateTime.now())
                .build();
        return this;
    }

    public ProductInfoDtoTestBuilder withId(UUID uuid) {
        this.productInfoDto.setId(uuid);
        return this;
    }
    public ProductInfoDto build() {
        return this.productInfoDto;
    }
}
