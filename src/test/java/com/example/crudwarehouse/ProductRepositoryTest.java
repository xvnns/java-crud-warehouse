package com.example.crudwarehouse;

import com.example.crudwarehouse.model.Category;
import com.example.crudwarehouse.model.Product;
import com.example.crudwarehouse.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    List<Product> productList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        Product product2 = new Product();
        product2.setPrice(new BigDecimal(20));
        product2.setName("test2");
        product2.setVendorCode("2");
        product2.setCategory(Category.LAPTOP);
        product2.setQuantity(2L);

        Product product3 = new Product();
        product3.setPrice(new BigDecimal(20));
        product3.setName("test2");
        product3.setVendorCode("2");
        product3.setCategory(Category.LAPTOP);
        product3.setQuantity(2L);

        productList.add(product2);
        productList.add(product3);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll(productList);
    }

    @Test
    void testSaveProduct() {
        Product product1 = new Product();
        product1.setPrice(new BigDecimal(10));
        product1.setName("test1");
        product1.setVendorCode("1");
        product1.setCategory(Category.LAPTOP);
        product1.setQuantity(1L);

        Product product = productRepository.save(product1);

        assertThat(product).hasFieldOrPropertyWithValue("price", new BigDecimal(10));
        assertThat(product).hasFieldOrPropertyWithValue("name", "test1");
        assertThat(product).hasFieldOrPropertyWithValue("vendorCode", "1");
        assertThat(product).hasFieldOrPropertyWithValue("category", Category.LAPTOP);
        assertThat(product).hasFieldOrPropertyWithValue("quantity", 1L);
    }
}
