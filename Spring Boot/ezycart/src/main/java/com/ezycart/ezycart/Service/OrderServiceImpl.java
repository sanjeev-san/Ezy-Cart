package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Address;
import com.ezycart.ezycart.Entities.Order;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.OrderException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartService cartItemService;
    @Autowired
    private ProductService productService;

  @Override
  public Order createOrder(User user, Address shippingAddress) {

  }

  @Override
  public Order findOrderById(Long orderId) throws OrderException;
  @Override
  public List<Order> usersOrderHistory(Long userId);
  @Override
  public Order placedOrder(Long orderId) throws OrderException;
  @Override
  public Order confirmedOrder(Long orderId) throws OrderException;
  @Override
  public Order shippedOrder(Long orderId) throws OrderException;
  @Override
  public Order deliveredOrder(Long orderId) throws OrderException;
  @Override
  public Order cancelledOrder(Long orderId) throws OrderException;
  @Override
  public List<Order> getAllOrders();
  @Override
  public void deleteOrder(Long orderId) throws OrderException;
}
