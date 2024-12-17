package com.example.crudwarehouse.dto;

import com.example.crudwarehouse.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoDto {
    private UUID id;

    private String name;

    private String vendorCode;

    private String description;

    private Category category;

    private BigDecimal price;

    private Long quantity;

    private LocalDateTime quantityUpdateDate;

    @JsonProperty("create_ts")
    private LocalDateTime createTs;
}
