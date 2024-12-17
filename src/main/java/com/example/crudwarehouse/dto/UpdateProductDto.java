package com.example.crudwarehouse.dto;

import com.example.crudwarehouse.constant.ErrorMessages;
import com.example.crudwarehouse.constant.SpringDocConst;
import com.example.crudwarehouse.model.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductDto {

    @NotBlank(message = ErrorMessages.NAME_NOT_BLANK_MESSAGE)
    @NotNull(message = ErrorMessages.NAME_NOT_NULL_MESSAGE)
    @Size(max = 90, message = ErrorMessages.NAME_SIZE_MAX_MESSAGE)
    @Schema(
            description = SpringDocConst.NAME_SCHEMA_DESCRIPTION
    )
    private String name;

    @NotBlank(message = ErrorMessages.VENDOR_CODE_NOT_BLANK_MESSAGE)
    @NotNull(message = ErrorMessages.VENDOR_CODE_NOT_NULL_MESSAGE)
    @Size(max = 255, message = ErrorMessages.VENDOR_CODE_SIZE_MAX_MESSAGE)
    @JsonProperty("vendor_code")
    @Schema(
            description = SpringDocConst.VENDOR_CODE_SCHEMA_DESCRIPTION
    )
    private String vendorCode;

    @Size(max = 2000, message = ErrorMessages.DESCRIPTION_SIZE_MAX_MESSAGE)
    @NotBlank(message = ErrorMessages.DESCRIPTION_NOT_BLANK_MESSAGE)
    @Schema(
            description = SpringDocConst.DESCRIPTION_SCHEMA_DESCRIPTION,
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String description;

    @NotNull(message = ErrorMessages.CATEGORY_NOT_NULL_MESSAGE)
    @Schema(
            description = SpringDocConst.CATEGORY_SCHEMA_DESCRIPTION
    )
    private Category category;

    @NotNull(message = ErrorMessages.PRICE_NOT_NULL_MESSAGE)
    @DecimalMin(value = "0.0", message = ErrorMessages.PRICE_MIN_VALUE_MESSAGE)
    @Schema(
            description = SpringDocConst.PRICE_SCHEMA_DESCRIPTION
    )
    private BigDecimal price;

    @NotNull(message = ErrorMessages.QUANTITY_NOT_NULL_MESSAGE)
    @Min(value = 0, message = ErrorMessages.QUANTITY_MIN_VALUE_MESSAGE)
    @Schema(
            description = SpringDocConst.QUANTITY_SCHEMA_DESCRIPTION
    )
    private Long quantity;
}
