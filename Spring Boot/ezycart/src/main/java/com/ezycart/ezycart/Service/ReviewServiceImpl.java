package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Entities.Review;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.ReviewRequest;
import com.ezycart.ezycart.Respository.ReviewRepo;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

  @Autowired
  private ReviewRepo reviewRepo;

  @Autowired
  private ProductService productService;

  public Review createReview(ReviewRequest req, User user)
    throws ProductException {
    Product product = productService.findProductById(req.getProductId());

    Review review = new Review();
    review.setUser(user);
    review.setProduct(product);
    review.setReview(req.getReview());
    review.setCreatedAt(LocalDateTime.now());
    return reviewRepo.save(review);
  }

  public List<Review> getAllProductReviews(Long productId) {
    return reviewRepo.getAllProductsReviews(productId);
  }
}
