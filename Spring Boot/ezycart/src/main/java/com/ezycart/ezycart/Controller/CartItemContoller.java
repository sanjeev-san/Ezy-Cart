package com.ezycart.ezycart.Controller;

import com.ezycart.ezycart.Entities.CartItem;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.CartItemException;
import com.ezycart.ezycart.Exception.UserException;
import com.ezycart.ezycart.Response.ApiResponse;
import com.ezycart.ezycart.Service.CartItemService;
import com.ezycart.ezycart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemContoller {

  @Autowired
  private CartItemService cartItemService;

  @Autowired
  private UserService userService;

  @DeleteMapping("/{cartItemId}")
  public ResponseEntity<ApiResponse> deleteCartItemHandler(
    @PathVariable Long cartItemId,
    @RequestHeader("Authorization") String jwt
  ) throws CartItemException, UserException {
    User user = userService.findUserProfileByJwt(jwt);
    cartItemService.deleteCartItem(user.getId(), cartItemId);

    ApiResponse res = new ApiResponse("Item remove from cart", true);
    return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
  }

  @PutMapping("/{cartItemId}")
  public ResponseEntity<CartItem> updateCartItemHandler(
    @PathVariable Long cartItemId,
    @RequestBody CartItem cartItem,
    @RequestHeader("Authorization") String jwt
  ) throws CartItemException, UserException {
    User user = userService.findUserProfileByJwt(jwt);

    CartItem updatedCartItem = cartItemService.updateCartItem(
      user.getId(),
      cartItemId,
      cartItem
    );
    return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
  }
}
