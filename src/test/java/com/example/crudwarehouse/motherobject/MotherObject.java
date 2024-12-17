package com.example.crudwarehouse.motherobject;

public class MotherObject {
    public static CreateProductDtoTestBuilder createRandomCreateProductDto() {
        return CreateProductDtoTestBuilder.aCreateProductDto().withRandomFields();
    }

    public static ProductInfoDtoTestBuilder createRandomProductInfoDto() {
        return ProductInfoDtoTestBuilder.aProductInfoDto().withRandomFields();
    }

    public static UpdateProductDtoTestBuilder createRandomUpdateProductDto() {
        return UpdateProductDtoTestBuilder.aUpdateProductDto().withRandomFields();
    }

    public static ProductTestBuilder createRandomProduct() {
        return ProductTestBuilder.aProduct().withRandomFields();
    }

    public static ProductDtoTestBuilder createRandomProductDto() {
        return ProductDtoTestBuilder.aProductDto().withRandomFields();
    }
}
