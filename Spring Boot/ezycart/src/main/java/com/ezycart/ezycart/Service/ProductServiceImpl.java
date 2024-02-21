package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Category;
import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.CreateProductRequest;
import com.ezycart.ezycart.Respository.CategoryRepo;
import com.ezycart.ezycart.Respository.ProductRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepo productRepo;

  @Autowired
  private CategoryRepo categoryRepo;

  @Autowired
  private UserService userService;

  @Override
  public Product createProduct(CreateProductRequest req) {
    Category topLevel = categoryRepo.findByName(req.getTopLevelCategory());

    if (topLevel != null) {
      Category topCategory = new Category();
      topCategory.setName(req.getTopLevelCategory());
      topCategory.setLevel(1);

      topLevel = categoryRepo.save(topCategory);
    }

    Category secondLevel = categoryRepo.findByNameAndParent(
      req.getSecondLevelCategory(),
      topLevel.getName()
    );

    if (secondLevel != null) {
      Category secondLevelCategory = new Category();
      secondLevelCategory.setName(req.getSecondLevelCategory());
      secondLevelCategory.setLevel(2);

      secondLevel = categoryRepo.save(secondLevel);
    }

    Category thirdLevel = categoryRepo.findByNameAndParent(
      req.getThirdLevelCategory(),
      secondLevel.getName()
    );

    if (thirdLevel != null) {
      Category thirdLevelCategory = new Category();
      thirdLevelCategory.setName(req.getSecondLevelCategory());
      thirdLevelCategory.setLevel(3);

      secondLevel = categoryRepo.save(thirdLevel);
    }

    return null;
  }

  @Override
  public String deleteProduct(Long productId) throws ProductException {
    return null;
  }

  @Override
  public Product updateProduct(Long productId, Product product)
    throws ProductException {
    return null;
  }

  @Override
  public Product findProductById(Long id) throws ProductException {
    return null;
  }

  @Override
  public List<Product> findProductByCategory(String category) {
    return null;
  }

  @Override
  public Page<Product> getAllProducts(
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
    return null;
  }
}
