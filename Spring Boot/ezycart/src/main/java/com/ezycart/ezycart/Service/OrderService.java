package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Address;
import com.ezycart.ezycart.Entities.Order;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.OrderException;
import java.util.List;

public interface OrderService {
  public Order createOrder(User user, Address shippingAddress);

  public Order findOrderById(Long orderId) throws OrderException;

  public List<Order> usersOrderHistory(Long userId);

  public Order placedOrder(Long orderId) throws OrderException;

  public Order confirmedOrder(Long orderId) throws OrderException;

  public Order shippedOrder(Long orderId) throws OrderException;

  public Order deliveredOrder(Long orderId) throws OrderException;

  public Order cancelledOrder(Long orderId) throws OrderException;

  public List<Order> getAllOrders();

  public void deleteOrder(Long orderId) throws OrderException;
}
