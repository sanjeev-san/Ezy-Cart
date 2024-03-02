package com.ezycart.ezycart.Controller;

import com.ezycart.ezycart.Entities.Address;
import com.ezycart.ezycart.Entities.Order;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.OrderException;
import com.ezycart.ezycart.Exception.UserException;
import com.ezycart.ezycart.Service.OrderService;
import com.ezycart.ezycart.Service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private UserService userService;

  @PostMapping()
  public ResponseEntity<Order> createOrderHandler(
    @RequestBody Address shippingAddress,
    @RequestHeader("Authorization") String jwt
  ) throws UserException {
    User user = userService.findUserProfileByJwt(jwt);
    Order order = orderService.createOrder(user, shippingAddress);
    return new ResponseEntity<Order>(order, HttpStatus.OK);
  }

  @GetMapping("/user")
  public ResponseEntity<List<Order>> usersOrderHistoryHandler(
    @RequestHeader("Authorization") String jwt
  ) throws OrderException, UserException {
    User user = userService.findUserProfileByJwt(jwt);
    List<Order> orders = orderService.usersOrderHistory(user.getId());
    return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<Order> findOrderHandler(
    @PathVariable Long orderId,
    @RequestHeader("Authorization") String jwt
  ) throws OrderException, UserException {
    // User user = userService.findUserProfileByJwt(jwt);
    Order orders = orderService.findOrderById(orderId);
    return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
  }
}
