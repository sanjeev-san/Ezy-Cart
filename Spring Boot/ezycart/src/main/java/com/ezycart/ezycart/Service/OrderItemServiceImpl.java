package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.OrderItem;
import com.ezycart.ezycart.Respository.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderItemServiceImpl implements OrderItemService {

  @Autowired
  private OrderItemRepo orderItemRepo;

  @Override
  public OrderItem createOrderItem(OrderItem orderItem) {
    return orderItemRepo.save(orderItem);
  }
}
