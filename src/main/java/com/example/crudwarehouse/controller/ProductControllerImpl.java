package com.example.crudwarehouse.controller;

import com.example.crudwarehouse.dto.CreateProductDto;
import com.example.crudwarehouse.dto.ProductDto;
import com.example.crudwarehouse.dto.ProductResponseDto;
import com.example.crudwarehouse.dto.UpdateProductDto;
import com.example.crudwarehouse.exception.ProductNotFoundException;
import com.example.crudwarehouse.exception.ProductWithThisVendorCodeAlreadyExistsException;
import com.example.crudwarehouse.mapper.ProductMapper;
import com.example.crudwarehouse.search.criteria.SearchCriteria;
import com.example.crudwarehouse.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductControllerImpl(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    public UUID create(CreateProductDto createProductDto) throws ProductWithThisVendorCodeAlreadyExistsException {
        ProductDto productDto = ProductMapper.mapper.mapToProductDto(createProductDto);
        return productServiceImpl.createProduct(productDto);
    }

    public ResponseEntity<List<ProductResponseDto>> getAll(Pageable pageable) {
        List<ProductResponseDto> productResponseDtoList =
                ProductMapper.mapper.mapToProductResponseDto(productServiceImpl.getAllProducts(pageable));
        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponseDto> getById(UUID id) throws ProductNotFoundException {
        ProductResponseDto productResponseDto =
                ProductMapper.mapper.mapToProductResponseDto(productServiceImpl.getProductById(id));
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<ProductResponseDto> update(UUID id, UpdateProductDto updateProductDto)
            throws ProductNotFoundException, ProductWithThisVendorCodeAlreadyExistsException {
        ProductDto productDto = ProductMapper.mapper.mapToProductDto(updateProductDto);
        ProductResponseDto productResponseDto =
                ProductMapper.mapper.mapToProductResponseDto(productServiceImpl.updateProduct(id, productDto));
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteById(UUID id) {
        try {
            productServiceImpl.deleteProductById(id);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity<List<ProductResponseDto>> searchProducts(Pageable pageable,
                                                                   @Valid @RequestBody List<SearchCriteria> searchCriteriaList) {
        List<ProductResponseDto> productResponseDtoList =
                ProductMapper.mapper.mapToProductResponseDto(
                        productServiceImpl.searchProducts(pageable, searchCriteriaList)
                );
        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }
}
