package com.ezycart.ezycart.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.CreateProductRequest;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);

    public String deleteProduct(Long productId) throws ProductException;

    public Product updateProduct(Long productId, Product reqProduct) throws ProductException;

    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductByCategory(String category);

    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscout, String sort, String stock, Integer pageNumber, Integer pageSize);




}
