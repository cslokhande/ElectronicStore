package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.apiResponce.CategoryResponse;
import com.bikkadit.electronicstore.payload.CategoryDto;


public interface CategoryService {

     //Create
     CategoryDto create(CategoryDto categoryDto);

     //Update
     CategoryDto update(CategoryDto categoryDto, Long categoryId);

     //Get
     CategoryDto getByCategoryId(Long categoryId);

     //GetAll
     //PageableResponse<CategoryDto> getAllCategory(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);

     CategoryResponse getCategories( int pageNumber,int pageSize, String sortBy, String sortDir);
     //Delete

     void deleteByCategoryId(Long categoryId);


}
