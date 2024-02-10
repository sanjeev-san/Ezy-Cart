package com.ezycart.Respository;

import com.ezycart.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
  public User findByEmail(String email);
}
