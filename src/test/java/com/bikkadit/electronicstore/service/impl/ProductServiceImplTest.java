package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.BaseTest;
import com.bikkadit.electronicstore.model.Product;
import com.bikkadit.electronicstore.payload.ProductDto;
import com.bikkadit.electronicstore.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest extends BaseTest {

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private ModelMapper modelMapper;

    Product product;
    Product product1;
    List<Product> products;

    ProductDto productDto;

    @BeforeEach
    public void init(){
        productDto = ProductDto.builder().title("Air Conditioner").description("Energy  efficient with 4.5 star rating")
                .price(19400.0).discountedPrice(2823.0).quantity(1).stock(true).live(true).build();

        product = Product.builder().title("Freezer").description("Energy  efficient with 4.5 star rating").price(16000.0)
               .discountedPrice(1823.0).quantity(1).stock(true).live(true).build();

        product1 = Product.builder().title("LCD TV").description("Android with full HD Display").price(12000.0)
                .discountedPrice(1329.0).quantity(1).stock(true).live(true).build();

        products=new ArrayList<>();
        products.add(product);
        products.add(product1);
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getAllLive() {
    }

    @Test
    void searchByTitle() {
    }
}