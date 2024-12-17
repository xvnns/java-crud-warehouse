package com.example.crudwarehouse.motherobject;

import com.example.crudwarehouse.model.Category;
import com.example.crudwarehouse.model.Product;
import jakarta.annotation.Nonnull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.crudwarehouse.util.TestUtil.createRandomString;

public class ProductTestBuilder {

    private Product product;

    public ProductTestBuilder() {
        this.product = new Product();
    }

    public static ProductTestBuilder aProduct() {
        return new ProductTestBuilder();
    }

    @Nonnull
    public ProductTestBuilder withRandomFields() {
        this.product = Product.builder()
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

    public Product build() {
        return this.product;
    }
}
