package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Category;
import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.CreateProductRequest;
import com.ezycart.ezycart.Respository.CategoryRepo;
import com.ezycart.ezycart.Respository.ProductRepo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepo productRepo;

  @Autowired
  private CategoryRepo categoryRepo;

  @Override
  public Product createProduct(CreateProductRequest req) {
    Category topLevel = categoryRepo.findByName(req.getTopLevelCategory());

    if (topLevel == null) {
      Category topLevelCategory = new Category();
      topLevelCategory.setName(req.getTopLevelCategory());
      topLevelCategory.setLevel(1);
      topLevel = categoryRepo.save(topLevelCategory);
    }

    Category secondLevel = categoryRepo.findByNameAndParent(
      req.getSecondLevelCategory(),
      topLevel.getName()
    );

    if (secondLevel == null) {
      Category secondLevelCategory = new Category();
      secondLevelCategory.setName(req.getSecondLevelCategory());
      secondLevelCategory.setLevel(2);
      secondLevelCategory.setParentCategory(topLevel);
      secondLevel = categoryRepo.save(secondLevelCategory);
    }

    Category thirdLevel = categoryRepo.findByNameAndParent(
      req.getThirdLevelCategory(),
      secondLevel.getName()
    );

    if (thirdLevel == null) {
      Category thirdLevelCategory = new Category();
      thirdLevelCategory.setName(req.getThirdLevelCategory());
      thirdLevelCategory.setLevel(3);
      thirdLevelCategory.setParentCategory(secondLevel);
      thirdLevel = categoryRepo.save(thirdLevelCategory);
    }

    Product product = new Product();
    product.setTitle(req.getTitle());
    product.setColor(req.getColor());
    product.setDescription(req.getDescription());
    product.setDiscountedPercent(req.getDiscountedPrcnt());
    product.setDiscountedPrice(req.getDiscountedPrice());
    product.setImageUrl(req.getImageUrl());
    product.setBrand(req.getBrand());
    product.setPrice(req.getPrice());
    product.setSizes(req.getSize());
    product.setQuantity(req.getQuantity());
    product.setCategory(thirdLevel);
    product.setCreatedAt(LocalDateTime.now());

    Product savedProduct = productRepo.save(product);

    return savedProduct;
  }

  @Override
  public String deleteProduct(Long productId) throws ProductException {
    Product product = findProductById(productId);
    product.getSizes().clear();
    productRepo.delete(product);
    return "Product deleted Successfully";
  }

  @Override
  public Product updateProduct(Long productId, Product reqProduct)
    throws ProductException {
    Product product = findProductById(productId);
    if (reqProduct.getQuantity() != 0) {
      product.setQuantity(reqProduct.getQuantity());
    }

    return productRepo.save(product);
  }

  @Override
  public Product findProductById(Long id) throws ProductException {
    Optional<Product> opt = productRepo.findById(id);
    if (opt.isPresent()) {
      return opt.get();
    }
    throw new ProductException("Product not found by id : " + id);
  }

  @Override
  public List<Product> findProductByCategory(String category) {
    return null;
  }

  @Override
  public Page<Product> getAllProduct(
    String category,
    List<String> colors,
    List<String> sizes,
    Integer minPrice,
    Integer maxPrice,
    Integer minDiscout,
    String sort,
    String stock,
    Integer pageNumber,
    Integer pageSize
  ) {
    Pageable pageable = PageRequest.of(pageNumber, pageSize);
    List<Product> products = productRepo.filterProducts(
      category,
      minPrice,
      maxPrice,
      minDiscout,
      sort
    );
    if (!colors.isEmpty()) {
      products =
        products
          .stream()
          .filter(p ->
            colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))
          )
          .collect(Collectors.toList());
    }
    if (stock != null) {
      if (stock.equals("in_stock")) {
        products =
          products
            .stream()
            .filter(p -> p.getQuantity() > 0)
            .collect(Collectors.toList());
      } else if (stock.equals("out_of_stock")) {
        products =
          products
            .stream()
            .filter(p -> p.getQuantity() < 0)
            .collect(Collectors.toList());
      }
    }

    int startIndex = (int) pageable.getOffset();
    int endIndex = Math.min(
      startIndex + pageable.getPageSize(),
      products.size()
    );
    List<Product> pageContent = products.subList(startIndex, endIndex);
    Page<Product> filteredProducts = new PageImpl<>(
      pageContent,
      pageable,
      products.size()
    );
    return filteredProducts;
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepo.findAll();
  }

  @Override
  public List<Product> searchProduct(String query) {
    List<Product> products = productRepo.searchProduct(query);
    return products;
  }
}
