package com.ezycart.ezycart.Respository;

import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.CartItem;
import com.ezycart.ezycart.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
  @Query(
    "SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.size = :size AND ci.userId = :userId"
  )
  public CartItem isCartItemExist(
    @Param("cart") Cart cart,
    @Param("product") Product product,
    @Param("size") String size,
    @Param("userId") Long userId
  );
}
