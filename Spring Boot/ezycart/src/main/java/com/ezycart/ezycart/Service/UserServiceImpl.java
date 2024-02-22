package com.ezycart.ezycart.Service;

import com.ezycart.ezycart.Config.jwtProvider;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.UserException;
import com.ezycart.ezycart.Respository.UserRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private jwtProvider jwtProvider;

  public User findUserById(Long userId) throws UserException {
    Optional<User> user = userRepo.findById(userId);
    if (user.isPresent()) {
      return user.get();
    }

    throw new UserException("User Not Found with id : " + userId);
  }

  public User findUserProfileByJwt(String jwt) throws UserException {
    String email = jwtProvider.getEmailFromToken(jwt);
    User user = userRepo.findByEmail(email);

    if (user == null) {
      throw new UserException("User not found with email : " + email);
    }
    return user;
  }
}
