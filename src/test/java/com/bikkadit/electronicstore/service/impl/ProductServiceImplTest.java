package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.BaseTest;
import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.model.Product;
import com.bikkadit.electronicstore.payload.ProductDto;
import com.bikkadit.electronicstore.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void init() {
        productDto = ProductDto.builder().title("Air Conditioner").description("Energy  efficient with 4.5 star rating")
                .price(19400.0).discountedPrice(2823.0).quantity(1).stock(true).live(true).build();

        product = Product.builder().title("Freezer").description("Energy  efficient with 4.5 star rating").price(16000.0)
                .discountedPrice(1823.0).quantity(1).stock(true).live(true).build();

        product1 = Product.builder().title("LCD TV").description("Android with full HD Display").price(12000.0)
                .discountedPrice(1329.0).quantity(1).stock(true).live(true).build();

        products = new ArrayList<>();
        products.add(product);
        products.add(product1);
    }

    @Test
    void create() {
//        Arrange
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
//        Act
        ProductDto product2 = productService.create(modelMapper.map(product, ProductDto.class));
//        Assert
        Assertions.assertNotNull(product2);
        Assertions.assertEquals(product.getTitle(), product2.getTitle());
    }

    @Test
    void update() {
        Long id = 10l;
//        Arrange
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);
//        Act
        ProductDto update = productService.update(productDto, id);
//        Assert
        Assertions.assertEquals(productDto.getTitle(), update.getTitle());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.update(productDto, 111l));
    }

    @Test
    void delete() {
        Long id = 10l;
//        Arrange
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
//        Act
        productService.delete(id);
//        Assert
        Mockito.verify(productRepository, Mockito.times(1)).delete(product);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.delete(111l));
    }

    @Test
    void get() {
        long id = 10l;
//        Arrange
        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
//        Act
        ProductDto product2 = productService.get(id);
//        Assert
        Assertions.assertNotNull(product2);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> productService.get(111l));
    }

    @Test
    void getAll() {
        Page<Product> page = new PageImpl<>(products);
//        Arrange
        Mockito.when(productRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
//        Act
        PageableResponse<ProductDto> all = productService.getAll(1, 5, AppConstant.SORT_BY_PRODUCT_ID, AppConstant.SORT_DIR);
//        Assert
        Assertions.assertEquals(2, all.getContent().size());
    }

    @Test
    void getAllLive() {
        Page<Product> page = new PageImpl<>(products);
//        Arrange
        Mockito.when(productRepository.findByLiveTrue((Pageable) Mockito.any())).thenReturn(page);
//        Act
        PageableResponse<ProductDto> allLive = productService.getAllLive(1, 5, AppConstant.SORT_BY_PRODUCT_ID, AppConstant.SORT_DIR);
//        Assert
        Assertions.assertEquals(2, allLive.getContent().size());
    }

    @Test
    void searchByTitle() {
        String key = "r";
        Page<Product> page = new PageImpl<>(products);
//        Arrange
        Mockito.when(productRepository.findByTitleContaining(key, (Pageable) Mockito.any())).thenReturn(page);
//        Act
        PageableResponse<ProductDto> all = productService.searchByTitle(key, 1, 5, AppConstant.SORT_BY_PRODUCT_ID, AppConstant.SORT_DIR);
//        Assert
        Assertions.assertEquals(2, all.getContent().size());
    }
}