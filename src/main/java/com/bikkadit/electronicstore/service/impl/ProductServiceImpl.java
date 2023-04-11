package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.helper.Helper;
import com.bikkadit.electronicstore.model.Product;
import com.bikkadit.electronicstore.payload.ProductDto;
import com.bikkadit.electronicstore.repository.ProductRepository;
import com.bikkadit.electronicstore.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ModelMapper mapper;

    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public ProductDto create(ProductDto productDto) {
        logger.info("Initiating dao call to save product");
        Product product = mapper.map(productDto, Product.class);
        product.setIsactive(AppConstant.YES);
        Product save = repository.save(product);
        logger.info("Complete dao call to save product");
        return mapper.map(save,ProductDto.class);
    }

    @Override
    public ProductDto update(ProductDto productDto, Long productId) {
        logger.info("Initiating dao call to update product by productId{}",productId);
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isLive());
        Product updatedProduct = repository.save(product);
        logger.info("Complete dao call to update product by productId{}",productId);
        return mapper.map(updatedProduct,ProductDto.class);
    }

    @Override
    public void delete(Long productId) {
        logger.info("Initiating dao call to delete product by productId{}",productId);
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
        logger.info("Complete dao call to delete product by productId{}",productId);
        repository.delete(product);
    }

    @Override
    public ProductDto get(Long productId) {
        logger.info("Initiating dao call to get product by productId{}",productId);
        Product product = repository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
        logger.info("Complete dao call to get product by productId{}",productId);
        return mapper.map(product,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("Initiating dao call to get all product");
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = repository.findAll(pageable);
        logger.info("Complete dao call to get all product");
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("Initiating dao call to get all product");
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = repository.findByLiveTrue(pageable);
        logger.info("Complete dao call to get all product");
        return Helper.getPageableResponse(page,ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchByTitle(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("Initiating dao call to search by keyword", keyword);
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> page = repository.findByTitleContaining(keyword,pageable);
        logger.info("Complete dao call to search by keyword{}",keyword);
        return Helper.getPageableResponse(page,ProductDto.class);
    }

}
