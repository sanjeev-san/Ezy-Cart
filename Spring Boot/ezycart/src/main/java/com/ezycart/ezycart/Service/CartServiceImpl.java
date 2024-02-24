package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.CartItem;
import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.AddItemRequest;
import com.ezycart.ezycart.Respository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

  @Autowired
  private CartRepo cartRepo;

  @Autowired
  private CartItemService cartItemService;

  @Autowired
  private ProductService productService;

  @Override
  public Cart createCart(User user) {
    Cart cart = new Cart();
    cart.setUser(user);
    return cartRepo.save(cart);
  }

  @Override
  public String addCartItem(Long userId, AddItemRequest req)
    throws ProductException {
    Cart cart = cartRepo.findByUserId(userId);
    Product product = productService.findProductById(req.getProductId());

    CartItem isPresent = cartItemService.isCartItemExist(
      cart,
      product,
      req.getSize(),
      userId
    );

    if (isPresent == null) {
      CartItem cartItem = new CartItem();
      cartItem.setProduct(product);
      cartItem.setCart(cart);
      cartItem.setQuantity(req.getQuantity());
      cartItem.setUserId(userId);
      int price = req.getQuantity() * product.getPrice();
      cartItem.setPrice(price);
      cartItem.setSize(req.getSize());
      CartItem createCartItem = cartItemService.createCartItem(cartItem);
      cart.getCartItems().add(createCartItem);
    }
    return "Item added to cart";
  }

  public Cart findUserCart(Long userId) {
    Cart cart = cartRepo.findByUserId(userId);

    int totalPrice = 0;
    int totalDiscountedPrice = 0;
    int totalItem = 0;

    for (CartItem cartItem : cart.getCartItems()) {
      totalPrice += cartItem.getPrice();
      totalDiscountedPrice += cartItem.getDiscountPrice();
      totalItem += cartItem.getQuantity();
    }
    cart.setTotalDiscountedPrice(totalDiscountedPrice);
    cart.setTotalPrice(totalPrice);
    cart.setTotalItem(totalItem);
    cart.setDiscount(totalPrice - totalDiscountedPrice);
    return cartRepo.save(cart);
  }
}
