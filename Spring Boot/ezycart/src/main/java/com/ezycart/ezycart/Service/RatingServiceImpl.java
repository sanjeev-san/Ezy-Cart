package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Entities.Rating;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Respository.RatingRepo;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingServiceImpl implements RatingService {

  @Autowired
  private RatingRepo ratingRepo;

  @Autowired
  private ProductService productService;

  @Override
  public Rating createRating(Rating req, User user) throws ProductException {
    Product product = productService.findProductById(req.getId());
    Rating rating = new Rating();
    rating.setProduct(product);
    rating.setUser(user);
    rating.setRating(req.getRating());
    rating.setCreatedAt(LocalDateTime.now());
    return ratingRepo.save(rating);
  }

  @Override
  public List<Rating> getProductRating(Long productId) {
    return ratingRepo.getAllProductsRatings(productId);
  }
}
