package com.ezycart.ezycart.Controller;

import com.ezycart.ezycart.Config.jwtProvider;
import com.ezycart.ezycart.Entities.Cart;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.UserException;
import com.ezycart.ezycart.Request.LoginRequest;
import com.ezycart.ezycart.Response.AuthResponse;
import com.ezycart.ezycart.Respository.UserRepo;
import com.ezycart.ezycart.Service.CartService;
import com.ezycart.ezycart.Service.CustomUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

  @Autowired
  private jwtProvider jwtProvider;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private CustomUserServiceImp customUserServiceImp;

  @Autowired
  private CartService cartService;

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

    Cart cart = cartService.createCart(savedUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(
      savedUser.getEmail(),
      savedUser.getPassword()
    );
    SecurityContextHolder.getContext().getAuthentication();

    String token = jwtProvider.generateToken(authentication);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(token);
    authResponse.setMessage("Signup Sucessfully");
    return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
  }

  @PostMapping("/sigin")
  public ResponseEntity<AuthResponse> loginUserHandler(
    @RequestBody LoginRequest loginRequest
  ) {
    String userName = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    Authentication authentication = authenticate(userName, password);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtProvider.generateToken(authentication);
    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(token);
    authResponse.setMessage("Signin Sucessfully");

    return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
  }

  private Authentication authenticate(String userName, String password) {
    UserDetails userDetails = customUserServiceImp.loadUserByUsername(userName);
    if (userDetails == null) {
      throw new BadCredentialsException("invalid Username and Password");
    }

    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid Password");
    }

    return new UsernamePasswordAuthenticationToken(
      userDetails,
      null,
      userDetails.getAuthorities()
    );
  }
}
