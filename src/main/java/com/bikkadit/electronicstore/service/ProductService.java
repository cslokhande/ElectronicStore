package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.apiResponce.UserPageableResponse;
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
    UserPageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get all:live
    UserPageableResponse<ProductDto>getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);

    //search product
    UserPageableResponse<ProductDto>searchByTitle(String keyword,int pageNumber,int pageSize,String sortBy,String sortDir);

}
