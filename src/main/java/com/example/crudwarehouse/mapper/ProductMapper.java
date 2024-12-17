package com.example.crudwarehouse.mapper;

import com.example.crudwarehouse.dto.*;
import com.example.crudwarehouse.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Class for mapping a Product entity
 */
@Mapper
public interface ProductMapper {

    ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    /**
     * Map ProductDto to Product
     *
     * @param productDto ProductDto to match
     * @return mapped Product
     */
    Product mapToProduct(ProductDto productDto);

    /**
     * Map CreateProductDto to ProductDto
     *
     * @param createProductDto CreateProductDto to match
     * @return mapped Product
     */
    ProductDto mapToProductDto(CreateProductDto createProductDto);

    /**
     * Map UpdateProductDto to ProductDto
     *
     * @param updateProductDto UpdateProductDto to match
     * @return mapped Product
     */
    ProductDto mapToProductDto(UpdateProductDto updateProductDto);

    /**
     * Map Product to ProductInfoDto
     *
     * @param product Product to match
     * @return mapped Product
     */
    ProductInfoDto mapToProductInfoDto(Product product);

    /**
     * Map list of Product to ProductInfoDto
     *
     * @param productList list of Product to match
     * @return mapped Product
     */
    List<ProductInfoDto> mapToProductInfoDto(List<Product> productList);

    /**
     * Map ProductInfoDto to ProductResponseDto
     *
     * @param productResponseDto ProductInfoDto to match
     * @return mapped Product
     */
    ProductResponseDto mapToProductResponseDto(ProductInfoDto productResponseDto);

    /**
     * Map list of ProductInfoDto to ProductResponseDto
     *
     * @param productList list of ProductInfoDto to match
     * @return mapped Product
     */
    List<ProductResponseDto> mapToProductResponseDto(List<ProductInfoDto> productList);
}
