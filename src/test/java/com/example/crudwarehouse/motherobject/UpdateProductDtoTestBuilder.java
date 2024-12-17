package com.example.crudwarehouse.motherobject;

import com.example.crudwarehouse.dto.UpdateProductDto;
import com.example.crudwarehouse.model.Category;

import java.math.BigDecimal;

import static com.example.crudwarehouse.util.TestUtil.createRandomString;

public class UpdateProductDtoTestBuilder {
    private UpdateProductDto updateProductDto;

    public UpdateProductDtoTestBuilder() {
        this.updateProductDto = new UpdateProductDto();
    }

    public static UpdateProductDtoTestBuilder aUpdateProductDto() {
        return new UpdateProductDtoTestBuilder();
    }

    public UpdateProductDtoTestBuilder withRandomFields() {
        this.updateProductDto = UpdateProductDto.builder()
                .name(createRandomString(20))
                .vendorCode(createRandomString(30))
                .description(createRandomString(200))
                .category(Category.LAPTOP)
                .price(new BigDecimal(100))
                .quantity(20L)
                .build();
        return this;
    }

    public UpdateProductDtoTestBuilder withBlankName() {
        this.updateProductDto.setName("");
        return this;
    }

    public UpdateProductDtoTestBuilder withNullName() {
        this.updateProductDto.setName(null);
        return this;
    }

    public UpdateProductDtoTestBuilder withLongName() {
        this.updateProductDto.setName(createRandomString(300));
        return this;
    }

    public UpdateProductDtoTestBuilder withBlankVendorCode() {
        this.updateProductDto.setVendorCode("");
        return this;
    }

    public UpdateProductDtoTestBuilder withNullVendorCode() {
        this.updateProductDto.setVendorCode(null);
        return this;
    }

    public UpdateProductDtoTestBuilder withLongVendorCode() {
        this.updateProductDto.setVendorCode(createRandomString(300));
        return this;
    }

//    public static ProductDto getProductWithBlankCategory() {
//        ProductDto product = getRandomProductDto();
//        product.setCategory("");
//        return product;
//    }

    public UpdateProductDtoTestBuilder withNullCategory() {
        this.updateProductDto.setCategory(null);
        return this;
    }

    public UpdateProductDtoTestBuilder withBlankDescription() {
        this.updateProductDto.setDescription("");
        return this;
    }

    public UpdateProductDtoTestBuilder withLongDescription() {
        this.updateProductDto.setDescription(createRandomString(3000));
        return this;
    }

    public UpdateProductDtoTestBuilder withNullPrice() {
        this.updateProductDto.setPrice(null);
        return this;
    }

    public UpdateProductDtoTestBuilder withNegativePrice() {
        this.updateProductDto.setPrice(BigDecimal.valueOf(-100.00));
        return this;
    }

    public UpdateProductDtoTestBuilder withNegativeQuantity() {
        this.updateProductDto.setQuantity(-200L);
        return this;
    }

    public UpdateProductDtoTestBuilder withNullQuantity() {
        this.updateProductDto.setQuantity(null);
        return this;
    }

    public UpdateProductDto build() {
        return this.updateProductDto;
    }
}
