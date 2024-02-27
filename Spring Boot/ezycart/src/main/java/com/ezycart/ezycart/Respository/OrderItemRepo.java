package com.ezycart.ezycart.Respository;

import com.ezycart.ezycart.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {}
