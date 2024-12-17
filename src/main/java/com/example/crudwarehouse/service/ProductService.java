package com.example.crudwarehouse.service;

import com.example.crudwarehouse.dto.ProductDto;
import com.example.crudwarehouse.dto.ProductInfoDto;
import com.example.crudwarehouse.exception.ProductNotFoundException;
import com.example.crudwarehouse.exception.ProductWithThisVendorCodeAlreadyExistsException;
import com.example.crudwarehouse.search.criteria.SearchCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Service for working with products
 */
@Component
public interface ProductService {
    /**
     * Create a new product
     *
     * @param productDto product information
     * @return the created product
     * @throws ProductWithThisVendorCodeAlreadyExistsException thrown when a product with the specified vendor code
     *                                                         already exists in the database
     */
    UUID createProduct(ProductDto productDto) throws ProductWithThisVendorCodeAlreadyExistsException;

    /**
     * Get a list of all products
     *
     * @return a list of all products found in the database
     */
    List<ProductInfoDto> getAllProducts(Pageable pageable);

    /**
     * Get product by ID
     *
     * @param id product ID
     * @return the found product in the database
     * @throws ProductNotFoundException is thrown when a product with the given ID was not found
     */
    ProductInfoDto getProductById(UUID id) throws ProductNotFoundException;

    /**
     * Update product by ID
     *
     * @param id         product ID
     * @param productDto new product information
     * @return updated product
     * @throws ProductNotFoundException is thrown when a product with the given ID was not found
     */
    ProductInfoDto updateProduct(UUID id, ProductDto productDto) throws ProductNotFoundException,
            ProductWithThisVendorCodeAlreadyExistsException;

    /**
     * Delete a product from the database by ID
     *
     * @param id ID of the product
     */
    void deleteProductById(UUID id) throws ProductNotFoundException;

    @SuppressWarnings("rawtypes")
    List<ProductInfoDto> searchProducts(Pageable pageable, List<SearchCriteria> searchCriteriaList);
}
