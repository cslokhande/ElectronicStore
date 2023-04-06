package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.apiResponce.ApiResponse;
import com.bikkadit.electronicstore.apiResponce.UserPageableResponse;
import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.payload.ProductDto;
import com.bikkadit.electronicstore.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService service;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        logger.info("Initiating request for create a product");
        ProductDto createProduct = service.create(productDto);
        logger.info("Complete request for create a category");
        return new ResponseEntity<>(createProduct, HttpStatus.CREATED);

    }

    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable Integer productId){
        logger.info("Initiating request for update a product by productId{}",productId);
        ProductDto updateProduct = service.update(productDto, productId);
        logger.info("Complete request for update a product by productId{}",productId);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);

    }

    //delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId){
        logger.info("Initiating request for delete a product by productId{}",productId);
        service.delete(productId);
        logger.info("Complete request for delete a product by productId{}",productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.CATEGORY_DELETE, true), HttpStatus.OK);
    }

    //get single
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer productId){
        logger.info("Initiating request for get product by productId{}",productId);
        ProductDto getProduct = service.get(productId);
        logger.info("Complete request for get product by productId{}",productId);
        return new ResponseEntity<>(getProduct, HttpStatus.OK);

    }

    //get all
    @GetMapping
    public ResponseEntity<UserPageableResponse> getAll(
            @RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY_PRODUCT_ID,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir
    ){
        logger.info("Initiating request for get all product");
        UserPageableResponse<ProductDto> pagealeResponse = service.getAll(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Complete request for get all product");
        return new ResponseEntity<>(pagealeResponse,HttpStatus.OK);

    }

    //get all live
    @GetMapping("/live")
    public ResponseEntity<UserPageableResponse> getAllLive(
            @RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY_PRODUCT_ID,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir
    ){
        logger.info("Initiating request for get all live product");
        UserPageableResponse<ProductDto> pagealeResponse = service.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Complete request for get all live product");
        return new ResponseEntity<>(pagealeResponse,HttpStatus.OK);

    }
    //search all
    @GetMapping("/search/{keyword}")
    public ResponseEntity<UserPageableResponse> search(
            @PathVariable String keyword,
            @RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY_PRODUCT_ID,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir
    ){
        logger.info("Initiating request for search by keyword{}",keyword);
        UserPageableResponse<ProductDto> pagealeResponse = service.searchByTitle(keyword,pageNumber, pageSize, sortBy, sortDir);
        logger.info("Complete request for search by keyword{}",keyword);
        return new ResponseEntity<>(pagealeResponse,HttpStatus.OK);

    }

}
