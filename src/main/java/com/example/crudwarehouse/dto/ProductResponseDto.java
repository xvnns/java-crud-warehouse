package com.example.crudwarehouse.dto;

import com.example.crudwarehouse.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProductResponseDto {

    private UUID id;

    private String name;

    @JsonProperty("vendor_code")
    private String vendorCode;

    private String description;

    private Category category;

    private BigDecimal price;

    private Long quantity;

    private LocalDateTime quantityUpdateDate;

    @JsonProperty("create_ts")
    private LocalDateTime createTs;
}
