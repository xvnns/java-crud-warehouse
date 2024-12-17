package com.example.crudwarehouse.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDto {

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Long quantity;

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;
}
