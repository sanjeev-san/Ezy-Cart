package com.ezycart.ezycart.Respository;

import com.ezycart.ezycart.Entities.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepo extends JpaRepository<Review, Long> {
  @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
  public List<Review> getAllProductsReviews(@Param("productId") Long productId);
}
