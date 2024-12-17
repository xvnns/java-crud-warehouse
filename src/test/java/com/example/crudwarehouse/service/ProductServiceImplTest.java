package com.example.crudwarehouse.service;

import com.example.crudwarehouse.dto.ProductDto;
import com.example.crudwarehouse.dto.ProductInfoDto;
import com.example.crudwarehouse.exception.ProductNotFoundException;
import com.example.crudwarehouse.exception.ProductWithThisVendorCodeAlreadyExistsException;
import com.example.crudwarehouse.model.Product;
import com.example.crudwarehouse.motherobject.MotherObject;
import com.example.crudwarehouse.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @Mock
    private ProductRepository productRepositoryMock;

    @Test
    public void testCreateProduct() throws ProductWithThisVendorCodeAlreadyExistsException {
        ProductDto productDto = MotherObject.createRandomProductDto().build();
        Product product = MotherObject.createRandomProduct().build();

        when(productRepositoryMock.existsByVendorCode(any(String.class))).thenReturn(false);
        when(productRepositoryMock.save(any(Product.class))).thenReturn(product);

        UUID uuid = productServiceImpl.createProduct(productDto);

        assertThat(uuid).isEqualTo(product.getId());

        when(productRepositoryMock.existsByVendorCode(any(String.class))).thenReturn(true);

        assertThrows(
                ProductWithThisVendorCodeAlreadyExistsException.class, () -> productServiceImpl.createProduct(productDto)
        );

        verify(productRepositoryMock, times(1)).save(any(Product.class));
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = MotherObject.createRandomProduct().build();
        Product product2 = MotherObject.createRandomProduct().build();
        Page<Product> productPage = new PageImpl<>(List.of(product1, product2));
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("name").descending());

        when(productRepositoryMock.findAll(any(Pageable.class))).thenReturn(productPage);

        List<ProductInfoDto> productInfoDtoList = productServiceImpl.getAllProducts(pageRequest);

        assertThat(productInfoDtoList)
                .anySatisfy(productInfoDto -> {
                    assertThat(productInfoDto.getId()).isEqualTo(product1.getId());
                    assertThat(productInfoDto.getVendorCode()).isEqualTo(product1.getVendorCode());
                })
                .anySatisfy(productInfoDto -> {
                    assertThat(productInfoDto.getId()).isEqualTo(product2.getId());
                    assertThat(productInfoDto.getVendorCode()).isEqualTo(product2.getVendorCode());
                })
                .hasSize(productInfoDtoList.size());

        verify(productRepositoryMock, times(1)).findAll(any(Pageable.class));

    }

    @Test
    public void testGetProductById() throws ProductNotFoundException {
        Product product = MotherObject.createRandomProduct().build();

        when(productRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(product));

        ProductInfoDto productInfoDto = productServiceImpl.getProductById(product.getId());

        assertThat(productInfoDto.getId()).isEqualTo(product.getId());
        assertThat(productInfoDto.getVendorCode()).isEqualTo(product.getVendorCode());

        when(productRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());

        verify(productRepositoryMock, times(1)).findById(any(UUID.class));

        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.getProductById(product.getId()));
    }

    @Test
    public void testUpdateProduct() throws ProductNotFoundException, ProductWithThisVendorCodeAlreadyExistsException {
        Product product = MotherObject.createRandomProduct().build();
        ProductDto productDto = MotherObject.createRandomProductDto().build();

        when(productRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.of(product));
        when(productRepositoryMock.existsByVendorCode(any(String.class))).thenReturn(false);
        when(productRepositoryMock.save(any(Product.class))).thenReturn(product);

        ProductInfoDto productInfoDto = productServiceImpl.updateProduct(product.getId(), productDto);

        assertThat(productInfoDto.getId()).isEqualTo(product.getId());
        assertThat(productInfoDto.getVendorCode()).isEqualTo(productDto.getVendorCode());

        when(productRepositoryMock.existsByVendorCode(any(String.class))).thenReturn(true);

        assertThrows(
                ProductWithThisVendorCodeAlreadyExistsException.class,
                () -> productServiceImpl.updateProduct(product.getId(), productDto)
        );

        when(productRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.updateProduct(product.getId(), productDto));

        verify(productRepositoryMock, times(1)).save(any(Product.class));
    }

    @Test
    public void testDeleteProductById() throws ProductNotFoundException {
        when(productRepositoryMock.findById(any(UUID.class)))
                .thenReturn(Optional.of(MotherObject.createRandomProduct().build()));

        productServiceImpl.deleteProductById(UUID.randomUUID());

        verify(productRepositoryMock, times(1)).deleteById(any(UUID.class));

        when(productRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productServiceImpl.deleteProductById(UUID.randomUUID()));
    }
}
