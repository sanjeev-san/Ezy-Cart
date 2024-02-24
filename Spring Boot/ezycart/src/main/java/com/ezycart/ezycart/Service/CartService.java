package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.AddItemRequest;

public interface CartService {
  public Cart createCart(User user);

  public String addCartItem(Long userId, AddItemRequest req)
    throws ProductException;

  public Cart findUserCart(Long userId);
}
