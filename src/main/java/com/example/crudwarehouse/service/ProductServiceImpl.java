package com.example.crudwarehouse.service;

import com.example.crudwarehouse.dto.ProductDto;
import com.example.crudwarehouse.dto.ProductInfoDto;
import com.example.crudwarehouse.exception.ProductNotFoundException;
import com.example.crudwarehouse.exception.ProductWithThisVendorCodeAlreadyExistsException;
import com.example.crudwarehouse.mapper.ProductMapper;
import com.example.crudwarehouse.model.Product;
import com.example.crudwarehouse.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.crudwarehouse.search.criteria.SearchCriteria;
import com.example.crudwarehouse.search.ProductSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public UUID createProduct(ProductDto productDto) throws ProductWithThisVendorCodeAlreadyExistsException {
        if (productRepository.existsByVendorCode(productDto.getVendorCode())) {
            throw new ProductWithThisVendorCodeAlreadyExistsException(productDto.getVendorCode());
        } else {
            Product product = ProductMapper.mapper.mapToProduct(productDto);
            return productRepository.save(product).getId();
        }
    }

    public List<ProductInfoDto> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return ProductMapper.mapper.mapToProductInfoDto(productPage.getContent());
    }

    public ProductInfoDto getProductById(UUID id) throws ProductNotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.mapper.mapToProductInfoDto(product);
    }

    public ProductInfoDto updateProduct(UUID id, ProductDto productDto) throws ProductNotFoundException,
            ProductWithThisVendorCodeAlreadyExistsException {

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
//        if (productRepository.existsByVendorCode(productDto.getVendorCode())) {
//            throw new ProductWithThisVendorCodeAlreadyExistsException(productDto.getVendorCode());
//        }
        product.setName(productDto.getName());
        product.setVendorCode(productDto.getVendorCode());
        product.setDescription(productDto.getDescription());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        if (!Objects.equals(productDto.getQuantity(), product.getQuantity())) {
            product.setQuantityUpdateDate(LocalDateTime.now());
        }
        product.setQuantity(productDto.getQuantity());
        return ProductMapper.mapper.mapToProductInfoDto(productRepository.save(product));
    }

    public void deleteProductById(UUID id) throws ProductNotFoundException {
        productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.deleteById(id);
    }

    @SuppressWarnings("rawtypes")
    public List<ProductInfoDto> searchProducts(Pageable pageable,
                                               List<SearchCriteria> searchCriteriaList) {
        Specification<Product> specification = new ProductSpecification(searchCriteriaList);
        Page<Product> products = productRepository.findAll(specification, pageable);
        return ProductMapper.mapper.mapToProductInfoDto(products.stream().collect(Collectors.toList()));
    }
}
