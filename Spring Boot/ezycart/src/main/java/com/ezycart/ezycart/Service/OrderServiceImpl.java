package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Entities.Address;
import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.CartItem;
import com.ezycart.ezycart.Entities.Order;
import com.ezycart.ezycart.Entities.OrderItem;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.OrderException;
import com.ezycart.ezycart.Respository.AddressRepo;
import com.ezycart.ezycart.Respository.OrderItemRepo;
import com.ezycart.ezycart.Respository.OrderRepo;
import com.ezycart.ezycart.Respository.UserRepo;
import com.ezycart.ezycart.User.Domain.OrderStatus;
import com.ezycart.ezycart.User.Domain.PaymentStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepo orderRepo;

  @Autowired
  private CartService cartService;

  @Autowired
  private AddressRepo addressRepo;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private OrderItemRepo orderItemRepo;

  @Override
  public Order createOrder(User user, Address shippingAddress) {
    shippingAddress.setUser(user);
    Address address = addressRepo.save(shippingAddress);
    user.getAddresses().add(address);
    userRepo.save(user);

    Cart cart = cartService.findUserCart(user.getId());
    List<OrderItem> orderItems = new ArrayList<>();
    for (CartItem item : cart.getCartItems()) {
      OrderItem orderItem = new OrderItem();

      orderItem.setPrice(item.getPrice());
      orderItem.setQuantity(item.getQuantity());
      orderItem.setSize(item.getSize());
      orderItem.setProduct(item.getProduct());
      orderItem.setUserId(item.getUserId());
      orderItem.setDiscountedPrice(item.getDiscountPrice());

      OrderItem createdOrderItem = orderItemRepo.save(orderItem);
      orderItems.add(createdOrderItem);
    }

    Order createOrder = new Order();
    createOrder.setUser(user);
    createOrder.setOrderItems(orderItems);
    createOrder.setTotalPrice(cart.getTotalPrice());
    createOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
    createOrder.setDiscount(cart.getDiscount());
    createOrder.setTotalItems(cart.getTotalItem());
    createOrder.setShippingAddress(address);
    createOrder.setOrderDate(LocalDateTime.now());
    createOrder.setOrderStatus(OrderStatus.PENDING);
    createOrder.getPaymentDetails().setPaymentStatus(PaymentStatus.PENDING);
    createOrder.setCreatedAt(LocalDateTime.now());
    Order savedOrder = orderRepo.save(createOrder);

    for (OrderItem orderItem : orderItems) {
      orderItem.setOrder(savedOrder);
      orderItemRepo.save(orderItem);
    }
    return savedOrder;
  }

  @Override
  public Order findOrderById(Long orderId) throws OrderException {
    Optional<Order> opt = orderRepo.findById(orderId);
    if (opt.isPresent()) {
      return opt.get();
    }
    throw new OrderException("Order not exist with id : " + orderId);
  }

  @Override
  public List<Order> usersOrderHistory(Long userId) {
    List<Order> orders = orderRepo.getUsersOrders(userId);
    return orders;
  }

  @Override
  public Order placedOrder(Long orderId) throws OrderException {
    Order order = findOrderById(orderId);
    order.setOrderStatus(OrderStatus.PLACED);
    order.getPaymentDetails().setPaymentStatus(PaymentStatus.COMPLETED);
    return order;
  }

  @Override
  public Order confirmedOrder(Long orderId) throws OrderException {
    Order order = findOrderById(orderId);
    order.setOrderStatus(OrderStatus.CONFIRMED);
    return orderRepo.save(order);
  }

  @Override
  public Order shippedOrder(Long orderId) throws OrderException {
    Order order = findOrderById(orderId);
    order.setOrderStatus(OrderStatus.SHIPPED);
    return orderRepo.save(order);
  }

  @Override
  public Order deliveredOrder(Long orderId) throws OrderException {
    Order order = findOrderById(orderId);
    order.setOrderStatus(OrderStatus.DELIVERED);
    return orderRepo.save(order);
  }

  @Override
  public Order cancelledOrder(Long orderId) throws OrderException {
    Order order = findOrderById(orderId);
    order.setOrderStatus(OrderStatus.CANCELLED);
    return orderRepo.save(order);
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepo.findAll();
  }

  @Override
  public void deleteOrder(Long orderId) throws OrderException {
    // Order order = findOrderById(orderId);
    orderRepo.deleteById(orderId);
  }
}
