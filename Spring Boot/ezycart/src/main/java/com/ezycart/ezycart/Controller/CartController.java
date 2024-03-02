package com.ezycart.ezycart.Controller;

import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Exception.UserException;
import com.ezycart.ezycart.Request.AddItemRequest;
import com.ezycart.ezycart.Response.ApiResponse;
import com.ezycart.ezycart.Service.CartService;
import com.ezycart.ezycart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  @Autowired
  private CartService cartService;

  @Autowired
  private UserService userService;

  @GetExchange()
  public ResponseEntity<Cart> findUserCartHandler(
    @RequestHeader("Authorization") String jwt
  ) throws UserException {
    User user = userService.findUserProfileByJwt(jwt);
    Cart cart = cartService.findUserCart(user.getId());

    System.out.println("cart : " + cart.getUser().getEmail());

    return new ResponseEntity<Cart>(cart, HttpStatus.OK);
  }

  @PutMapping("/add")
  public ResponseEntity<ApiResponse> addItemToCart(
    @RequestBody AddItemRequest req,
    @RequestHeader("Authorization") String jwt
  ) throws UserException, ProductException {
    User user = userService.findUserProfileByJwt(jwt);

    cartService.addCartItem(user.getId(), req);

    ApiResponse res = new ApiResponse("Item Added To Cart Successfully", true);

    return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
  }
}
