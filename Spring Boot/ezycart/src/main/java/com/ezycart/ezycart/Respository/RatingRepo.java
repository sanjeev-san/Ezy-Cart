package com.ezycart.ezycart.Respository;

import com.ezycart.ezycart.Entities.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RatingRepo extends JpaRepository<Rating, Long> {
  @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
  public List<Rating> getAllProductsRatings(@Param("productId") Long productId);
}
