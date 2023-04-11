package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.payload.ProductDto;

public interface ProductService {

    //create
    ProductDto create(ProductDto productDto);

    //update
    ProductDto update(ProductDto productDto,Long productId);

    //delete
    void delete(Long productId);

    //get single

    ProductDto get(Long productId);

    //get all
    PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get all:live
    PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search product
    PageableResponse<ProductDto> searchByTitle(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir);

}
