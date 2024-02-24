package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.CartItem;
import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Exception.CartItemException;
import com.ezycart.ezycart.Exception.UserException;

public interface CartItemService {
  public CartItem createCartItem(CartItem cartItem);

  public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)
    throws CartItemException, UserException;

  public CartItem isCartItemExist(
    Cart cart,
    Product product,
    String size,
    Long userId
  );

  public void deleteCartItem(Long userId, Long cartItemId)
    throws CartItemException, UserException;

  public CartItem findCartItemById(Long cartItemid) throws CartItemException;
}
