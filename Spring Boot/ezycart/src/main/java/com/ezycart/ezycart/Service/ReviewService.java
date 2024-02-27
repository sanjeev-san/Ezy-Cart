package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Review;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.ReviewRequest;
import java.util.List;

public interface ReviewService {
  public Review createReview(ReviewRequest req, User user)
    throws ProductException;

  public List<Review> getAllProductReviews(Long productId);
}
