package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.CartItem;
import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.CartItemException;
import com.ezycart.ezycart.Exception.UserException;
import com.ezycart.ezycart.Respository.CartItemRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {

  @Autowired
  private CartItemRepo cartItemRepo;

  @Autowired
  private UserService userService;

  @Override
  public CartItem createCartItem(CartItem cartItem) {
    cartItem.setQuantity(1);
    cartItem.setPrice(
      cartItem.getProduct().getPrice() * cartItem.getQuantity()
    );
    cartItem.setDiscountPrice(
      cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity()
    );
    CartItem createdCartItem = cartItemRepo.save(cartItem);
    return createdCartItem;
  }

  @Override
  public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)
    throws CartItemException, UserException {
    CartItem item = findCartItemById(id);
    User user = userService.findUserById(item.getUserId());

    if (user.getId().equals(userId)) {
      item.setQuantity(cartItem.getQuantity());
      item.setPrice(item.getProduct().getPrice() * item.getQuantity());

      item.setDiscountPrice(
        item.getProduct().getDiscountedPrice() * item.getQuantity()
      );
    }
    return cartItemRepo.save(item);
  }

  @Override
  public CartItem isCartItemExist(
    Cart cart,
    Product product,
    String size,
    Long userId
  ) {
    CartItem cartItem = cartItemRepo.isCartItemExist(
      cart,
      product,
      size,
      userId
    );
    return cartItem;
  }

  @Override
  public void deleteCartItem(Long userId, Long cartItemId)
    throws CartItemException, UserException {
    CartItem cartItem = findCartItemById(cartItemId);
    User user = userService.findUserById(cartItem.getUserId());

    User reqUser = userService.findUserById(userId);

    if (user.getId().equals(reqUser.getId())) {
      cartItemRepo.deleteById(cartItemId);
    } else {
      throw new UserException("You cant remove another user items");
    }
  }

  public CartItem findCartItemById(Long cartItemid) throws CartItemException {
    Optional<CartItem> opt = cartItemRepo.findById(cartItemid);

    if (opt.isPresent()) {
      return opt.get();
    }
    throw new CartItemException("Cart Item not found with id : " + cartItemid);
  }
}
