package com.ezycart.Controller;

import com.ezycart.Config.jwtProvider;
import com.ezycart.Entity.User;
import com.ezycart.Exception.UserException;
import com.ezycart.Response.AuthResponse;
import com.ezycart.Respository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserRepo userRepo;

  private jwtProvider jwtProvider;
  private PasswordEncoder passwordEncoder;

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user)
    throws UserException {
    String email = user.getEmail();
    String password = user.getPassword();
    String firstName = user.getFirstName();
    String lastName = user.getLastName();

    User isEmailExist = userRepo.findByEmail(email);
    if (isEmailExist != null) {
      throw new UserException("Email is already used");
      // throw new UserException();
    }

    User createdUser = new User();
    createdUser.setEmail(email);
    createdUser.setPassword(passwordEncoder.encode(password));
    createdUser.setFirstName(firstName);
    createdUser.setLastName(lastName);
    User savedUser = userRepo.save(createdUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(
      savedUser.getEmail(),
      savedUser.getPassword()
    );
    SecurityContextHolder.getContext().getAuthentication();

    String token = jwtProvider.generateToken(authentication);
    AuthResponse authResponse = new AuthResponse(token, "SignUp Successfully");

    return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
  }
}
