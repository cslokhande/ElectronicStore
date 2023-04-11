package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.helper.Helper;
import com.bikkadit.electronicstore.model.Category;
import com.bikkadit.electronicstore.apiResponce.CategoryResponse;
import com.bikkadit.electronicstore.model.Product;
import com.bikkadit.electronicstore.payload.CategoryDto;
import com.bikkadit.electronicstore.payload.ProductDto;
import com.bikkadit.electronicstore.repository.CategoryRepo;
import com.bikkadit.electronicstore.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        logger.info("Initiating dao call to save create");
        Category category = this.mapper.map(categoryDto, Category.class);
        category.setIsactive(AppConstant.YES);
        Category save = this.categoryRepo.save(category);
        logger.info("Complete dao call to save create");
        return this.mapper.map(save, CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Long categoryId) {
        logger.info("Initiating dao call to update category by categoryId{}", categoryId);
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category update = this.categoryRepo.save(category);
        logger.info("Complete dao call to update category by categoryId{}", categoryId);
        return this.mapper.map(update, CategoryDto.class);
    }

    @Override
    public CategoryDto getByCategoryId(Long categoryId) {
        logger.info("Initiating dao call to get category by categoryId{}", categoryId);
        Category getCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND));
        logger.info("Complete dao call to get category by categoryId{}", categoryId);
        return this.mapper.map(getCategory, CategoryDto.class);
    }

    //Get all category
    @Override
    public PageableResponse <CategoryDto> getCategories(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("Initiating dao call to get all category");
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> categories = categoryRepo.findAll(pageable);
        logger.info("Complete dao call to get all category");
        return Helper.getPageableResponse(categories, CategoryDto.class);
    }

    @Override
    public void deleteByCategoryId(Long categoryId) {
        logger.info("Initiating dao call to delete by categoryId{}", categoryId);
        Category deleteCategory = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.CATEGORY_NOT_FOUND));
        deleteCategory.setIsactive(AppConstant.NO);
        this.categoryRepo.save(deleteCategory);
        logger.info("Complete dao call to delete by categoryId{}", categoryId);
    }


}
