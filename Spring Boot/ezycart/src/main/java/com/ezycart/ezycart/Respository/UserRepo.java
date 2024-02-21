package com.ezycart.ezycart.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezycart.ezycart.Entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
  public User findByEmail(String email);
}
