package com.example.crudwarehouse.dto;

import com.example.crudwarehouse.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;

    private String vendorCode;

    private String description;

    private Category category;

    private BigDecimal price;

    private Long quantity;
}
