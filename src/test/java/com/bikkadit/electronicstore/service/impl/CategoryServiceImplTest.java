package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.BaseTest;
import com.bikkadit.electronicstore.model.Category;
import com.bikkadit.electronicstore.payload.CategoryDto;
import com.bikkadit.electronicstore.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceImplTest extends BaseTest{

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private ModelMapper modelMapper;

    Category category;
    Category category1;
    List<Category> categories;

    CategoryDto categoryDto;

    @BeforeEach
    public void init() {
        categoryDto = CategoryDto.builder().title("Android LCD TV").description("Android with gorilla screen")
                .coverImage("abc.png").build();

        category = Category.builder().title("Android LCD TV").description("Android with gorilla screen")
                .coverImage("abc.png").build();

        category1 = Category.builder().title("Android LCD TV").description("Android with gorilla screen")
                .coverImage("abc.png").build();

        categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);
    }

    @Test
    void create() {
//        Arrange
        Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(category);
//        Act
        CategoryDto category2 = categoryService.create(modelMapper.map(category, CategoryDto.class));
//        Assert
        Assertions.assertNotNull(category2);
        Assertions.assertEquals(category2.getTitle(), category2.getTitle());
    }

    @Test
    void update() {
//        Arrange

//        Act

//        Assert

    }

    @Test
    void getByCategoryId() {
//        Arrange

//        Act

//        Assert

    }

    @Test
    void getCategories() {
//        Arrange

//        Act

//        Assert

    }

    @Test
    void deleteByCategoryId() {
//        Arrange

//        Act

//        Assert

    }
}