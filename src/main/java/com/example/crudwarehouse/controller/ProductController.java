package com.example.crudwarehouse.controller;

import com.example.crudwarehouse.constant.SpringDocConst;
import com.example.crudwarehouse.dto.CreateProductDto;
import com.example.crudwarehouse.dto.ProductResponseDto;
import com.example.crudwarehouse.dto.UpdateProductDto;
import com.example.crudwarehouse.exception.ProductNotFoundException;
import com.example.crudwarehouse.exception.ProductWithThisVendorCodeAlreadyExistsException;
import com.example.crudwarehouse.search.criteria.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller for interacting with products
 */
@RequestMapping("/products")
@Tag(name = SpringDocConst.TAG_NAME, description = SpringDocConst.TAG_DESCRIPTION)
public interface ProductController {

    @PostMapping
    @Operation(
            summary = SpringDocConst.CREATE_OPERATION_SUMMARY,
            description = SpringDocConst.CREATE_OPERATION_DESCRIPTION
    )
    @ResponseStatus(HttpStatus.CREATED)
    UUID create(@Valid @RequestBody CreateProductDto createProductDto) throws ProductWithThisVendorCodeAlreadyExistsException;

    @GetMapping
    @Operation(
            summary = SpringDocConst.GET_ALL_OPERATION_SUMMARY,
            description = SpringDocConst.GET_ALL_OPERATION_DESCRIPTION
    )
    ResponseEntity<List<ProductResponseDto>> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(
            summary = SpringDocConst.GET_BY_ID_OPERATION_SUMMARY,
            description = SpringDocConst.GET_BY_ID_OPERATION_DESCRIPTION
    )
    ResponseEntity<ProductResponseDto> getById(
            @PathVariable("id")
            @Parameter(description = SpringDocConst.GET_BY_ID_ID_PARAMETER_DESCRIPTION, required = true)
            UUID id) throws ProductNotFoundException;

    @PutMapping("/{id}")
    @Operation(
            summary = SpringDocConst.UPDATE_OPERATION_SUMMARY,
            description = SpringDocConst.UPDATE_OPERATION_DESCRIPTION
    )
    ResponseEntity<ProductResponseDto> update(
            @PathVariable("id")
            @Parameter(description = SpringDocConst.UPDATE_ID_PARAMETER_DESCRIPTION, required = true)
            UUID id,

            @Valid @RequestBody UpdateProductDto updateProductDto
    ) throws ProductNotFoundException, ProductWithThisVendorCodeAlreadyExistsException;

    @DeleteMapping("/{id}")
    @Operation(
            summary = SpringDocConst.DELETE_BY_ID_OPERATION_SUMMARY,
            description = SpringDocConst.DELETE_BY_ID_OPERATION_DESCRIPTION
    )
    ResponseEntity<HttpStatus> deleteById(
            @PathVariable("id")
            @Parameter(description = SpringDocConst.DELETE_BY_ID_ID_PARAMETER_DESCRIPTION)
            UUID id
    );

    @SuppressWarnings("rawtypes")
    @PostMapping("/search")
    ResponseEntity<List<ProductResponseDto>> searchProducts(Pageable pageable,
                                                            @Valid @RequestBody List<SearchCriteria> searchCriteriaList);
}
