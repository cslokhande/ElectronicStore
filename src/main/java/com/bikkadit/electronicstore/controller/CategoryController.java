package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.apiResponce.ApiResponse;
import com.bikkadit.electronicstore.apiResponce.CategoryResponse;
import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.payload.CategoryDto;
import com.bikkadit.electronicstore.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        logger.info("Initiating request for create a category");
        CategoryDto categoryDto1 = this.categoryService.create(categoryDto);
        logger.info("Complete request for create a category");
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
        logger.info("Initiating request for update category for {categoryId}"+categoryId);
        CategoryDto update = this.categoryService.update(categoryDto, categoryId);
        logger.info("Complete request for update category for {categoryId}"+categoryId);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
        logger.info("Initiating request for get category for {categoryId}" + categoryId);
        CategoryDto byCategoryId = this.categoryService.getByCategoryId(categoryId);
        logger.info("Complete request for get category for {categoryId}" + categoryId);
        return new ResponseEntity<>(byCategoryId, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId) {
        logger.info("Initiating request for delete category for {categoryId}" + categoryId);
        this.categoryService.deleteByCategoryId(categoryId);
        logger.info("Complete request for delete category for {categoryId}"+ categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.CATEGORY_DELETE, true), HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public ResponseEntity<PageableResponse> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORTED_BY_CATEGORY_ID, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {

        logger.info("Initiating request for getAllCategory ");
        PageableResponse categories = this.categoryService.getCategories(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Complete request for getAllCategory ");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}





















