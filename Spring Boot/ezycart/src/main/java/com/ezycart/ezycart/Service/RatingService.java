package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Rating;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.RatingRequest;
import java.util.List;

public interface RatingService {
  public Rating createRating(RatingRequest req, User user)
    throws ProductException;

  public List<Rating> getProductRating(Long productId);
}
