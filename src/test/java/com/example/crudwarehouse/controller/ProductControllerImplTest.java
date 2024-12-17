package com.example.crudwarehouse.controller;

import com.example.crudwarehouse.constant.ErrorMessages;
import com.example.crudwarehouse.dto.CreateProductDto;
import com.example.crudwarehouse.dto.ProductDto;
import com.example.crudwarehouse.dto.ProductInfoDto;
import com.example.crudwarehouse.dto.UpdateProductDto;
import com.example.crudwarehouse.exception.ProductNotFoundException;
import com.example.crudwarehouse.exception.ProductWithThisVendorCodeAlreadyExistsException;
import com.example.crudwarehouse.motherobject.MotherObject;
import com.example.crudwarehouse.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ProductControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl productServiceImplMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateProduct() throws Exception {
        UUID uuid = UUID.randomUUID();
        CreateProductDto product = MotherObject.createRandomCreateProductDto().build();

        when(productServiceImplMock.createProduct(any(ProductDto.class))).thenReturn(uuid);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").value(uuid.toString()));

        when(productServiceImplMock.createProduct(any(ProductDto.class)))
                .thenThrow(new ProductWithThisVendorCodeAlreadyExistsException(product.getVendorCode()));

        String errorMessage = String.format(
                ErrorMessages.PRODUCT_WITH_THIS_VENDOR_CODE_ALREADY_EXISTS_EXCEPTION_MESSAGE,
                product.getVendorCode()
        );
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message", is(errorMessage)));
    }

    @Test
    public void testCreateProductWithIncorrectName() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withBlankName().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.NAME_NOT_BLANK_MESSAGE)));

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withNullName().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.NAME_NOT_NULL_MESSAGE)));

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withLongName().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.NAME_SIZE_MAX_MESSAGE)));
    }

    @Test
    public void testCreateProductWithIncorrectVendorCode() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withBlankVendorCode().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.VENDOR_CODE_NOT_BLANK_MESSAGE)));

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withNullVendorCode().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.VENDOR_CODE_NOT_NULL_MESSAGE)));

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withLongVendorCode().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.VENDOR_CODE_SIZE_MAX_MESSAGE)));
    }

    @Test
    public void testCreateProductWithIncorrectCategory() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withNullCategory().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.CATEGORY_NOT_NULL_MESSAGE)));
    }

    @Test
    public void testCreateProductWithIncorrectDescription() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withBlankDescription().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.DESCRIPTION_NOT_BLANK_MESSAGE)));

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withLongDescription().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.DESCRIPTION_SIZE_MAX_MESSAGE)));
    }

    @Test
    public void testCreateProductWithIncorrectPrice() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withNullPrice().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.PRICE_NOT_NULL_MESSAGE)));

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withNegativePrice().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.PRICE_MIN_VALUE_MESSAGE)));
    }

    @Test
    public void testCreateProductWithIncorrectQuantity() throws Exception {
        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withNullQuantity().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.QUANTITY_NOT_NULL_MESSAGE)));

        mockMvc.perform(
                        post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(
                                        MotherObject.createRandomCreateProductDto().withNegativeQuantity().build()
                                ))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.QUANTITY_MIN_VALUE_MESSAGE)));
    }

    @Test
    public void testGetAll() throws Exception {
        ProductInfoDto product1 = MotherObject.createRandomProductInfoDto().build();
        ProductInfoDto product2 = MotherObject.createRandomProductInfoDto().build();

        List<ProductInfoDto> productInfoDtoList = List.of(product1, product2);

        when(productServiceImplMock.getAllProducts(any(Pageable.class))).thenReturn(productInfoDtoList);

        mockMvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(productInfoDtoList.size())));
    }

    @Test
    public void testGetById() throws Exception {
        UUID uuid = UUID.randomUUID();
        ProductInfoDto product = MotherObject.createRandomProductInfoDto().withId(uuid).build();

        when(productServiceImplMock.getProductById(uuid)).thenReturn(product);

        mockMvc.perform(get(String.format("/products/%s", uuid)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(product.getId().toString())))
                .andExpect(jsonPath("$.vendor_code", is(product.getVendorCode())));

        when(productServiceImplMock.getProductById(uuid)).thenThrow(new ProductNotFoundException(uuid));

        mockMvc.perform(get(String.format("/products/%s", uuid)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath(
                        "$.message",
                        is(String.format(ErrorMessages.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE, uuid))
                ));
    }

    @Test
    public void testUpdate() throws Exception {
        UUID uuid = UUID.randomUUID();
        UpdateProductDto updateProductDto = MotherObject.createRandomUpdateProductDto().build();
        ProductInfoDto productInfoDto = MotherObject.createRandomProductInfoDto().withId(uuid).build();

        when(productServiceImplMock.updateProduct(any(UUID.class), any(ProductDto.class))).thenReturn(productInfoDto);

        mockMvc.perform(put(String.format("/products/%s", uuid))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateProductDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(uuid.toString())))
                .andExpect(jsonPath("$.vendor_code", is(productInfoDto.getVendorCode())));

        when(productServiceImplMock.updateProduct(any(UUID.class), any(ProductDto.class)))
                .thenThrow(new ProductNotFoundException(uuid));

        mockMvc.perform(
                        put(String.format("/products/%s", uuid))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(updateProductDto)))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath(
                        "$.message",
                        is(String.format(ErrorMessages.PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE, uuid))
                ));
    }

    @Test
    public void testUpdateProductWithIncorrectName() throws Exception {
        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withBlankName().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString((ErrorMessages.NAME_NOT_BLANK_MESSAGE))));

        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withNullName().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString((ErrorMessages.NAME_NOT_NULL_MESSAGE))));

        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withLongName().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString((ErrorMessages.NAME_SIZE_MAX_MESSAGE))));
    }

    @Test
    public void testUpdateProductWithIncorrectVendorCode() throws Exception {
        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withBlankVendorCode().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.VENDOR_CODE_NOT_BLANK_MESSAGE)));

        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withNullVendorCode().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.VENDOR_CODE_NOT_NULL_MESSAGE)));

        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withLongVendorCode().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.VENDOR_CODE_SIZE_MAX_MESSAGE)));
    }

    @Test
    public void testUpdateProductWithIncorrectCategory() throws Exception {
        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withNullCategory().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.CATEGORY_NOT_NULL_MESSAGE)));
    }

    @Test
    public void testUpdateProductWithIncorrectDescription() throws Exception {
        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withBlankDescription().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.DESCRIPTION_NOT_BLANK_MESSAGE)));

        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withLongDescription().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.DESCRIPTION_SIZE_MAX_MESSAGE)));
    }

    @Test
    public void testUpdateProductWithIncorrectPrice() throws Exception {
        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withNullPrice().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.PRICE_NOT_NULL_MESSAGE)));

        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withNegativePrice().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.PRICE_MIN_VALUE_MESSAGE)));
    }

    @Test
    public void testUpdateProductWithIncorrectQuantity() throws Exception {
        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withNullQuantity().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.QUANTITY_NOT_NULL_MESSAGE)));

        mockMvc.perform(put(String.format("/products/%s", UUID.randomUUID()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                MotherObject.createRandomUpdateProductDto().withNegativeQuantity().build()
                        )))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(ErrorMessages.QUANTITY_MIN_VALUE_MESSAGE)));
    }

    @Test
    public void testDeleteById() throws Exception {
        UUID uuid = UUID.randomUUID();

        mockMvc.perform(delete(String.format("/products/%s", uuid)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(productServiceImplMock, times(1)).deleteProductById(uuid);

        doThrow(new ProductNotFoundException(uuid)).when(productServiceImplMock).deleteProductById(any(UUID.class));

        mockMvc.perform(delete(String.format("/products/%s", uuid)))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}
