package com.example.crudwarehouse.motherobject;

import com.example.crudwarehouse.dto.CreateProductDto;
import com.example.crudwarehouse.model.Category;

import java.math.BigDecimal;

import static com.example.crudwarehouse.util.TestUtil.createRandomString;

public class CreateProductDtoTestBuilder {
    private CreateProductDto createProductDto;

    public CreateProductDtoTestBuilder() {
        this.createProductDto = new CreateProductDto();
    }

    public static CreateProductDtoTestBuilder aCreateProductDto() {
        return new CreateProductDtoTestBuilder();
    }

    public CreateProductDtoTestBuilder withRandomFields() {
        this.createProductDto = CreateProductDto.builder()
                .name(createRandomString(20))
                .vendorCode(createRandomString(30))
                .description(createRandomString(200))
                .category(Category.LAPTOP)
                .price(new BigDecimal(100))
                .quantity(20L)
                .build();
        return this;
    }

    public CreateProductDtoTestBuilder withBlankName() {
        this.createProductDto.setName("");
        return this;
    }

    public CreateProductDtoTestBuilder withNullName() {
        this.createProductDto.setName(null);
        return this;
    }

    public CreateProductDtoTestBuilder withLongName() {
        this.createProductDto.setName(createRandomString(300));
        return this;
    }

    public CreateProductDtoTestBuilder withBlankVendorCode() {
        this.createProductDto.setVendorCode("");
        return this;
    }

    public CreateProductDtoTestBuilder withNullVendorCode() {
        this.createProductDto.setVendorCode(null);
        return this;
    }

    public CreateProductDtoTestBuilder withLongVendorCode() {
        this.createProductDto.setVendorCode(createRandomString(300));
        return this;
    }

    public CreateProductDtoTestBuilder withNullCategory() {
        this.createProductDto.setCategory(null);
        return this;
    }

    public CreateProductDtoTestBuilder withBlankDescription() {
        this.createProductDto.setDescription("");
        return this;
    }

    public CreateProductDtoTestBuilder withLongDescription() {
        this.createProductDto.setDescription(createRandomString(3000));
        return this;
    }

    public CreateProductDtoTestBuilder withNullPrice() {
        this.createProductDto.setPrice(null);
        return this;
    }

    public CreateProductDtoTestBuilder withNegativePrice() {
        this.createProductDto.setPrice(BigDecimal.valueOf(-100.00));
        return this;
    }

    public CreateProductDtoTestBuilder withNegativeQuantity() {
        this.createProductDto.setQuantity(-200L);
        return this;
    }

    public CreateProductDtoTestBuilder withNullQuantity() {
        this.createProductDto.setQuantity(null);
        return this;
    }

    public CreateProductDto build() {
        return this.createProductDto;
    }
}
