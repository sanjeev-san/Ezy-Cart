package com.ezycart.ezycart.Controller;

import com.ezycart.ezycart.Entities.Rating;
import com.ezycart.ezycart.Entities.User;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Exception.UserException;
import com.ezycart.ezycart.Request.RatingRequest;
import com.ezycart.ezycart.Service.RatingService;
import com.ezycart.ezycart.Service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {

  @Autowired
  private UserService userService;

  @Autowired
  private RatingService ratingService;

  @PostMapping("/create")
  public ResponseEntity<Rating> createRatingHandler(
    @RequestBody RatingRequest req,
    @RequestHeader("Authorization") String jwt
  ) throws UserException, ProductException {
    User user = userService.findUserProfileByJwt(jwt);
    Rating rating = ratingService.createRating(req, user);
    return new ResponseEntity<>(rating, HttpStatus.ACCEPTED);
  }

  @GetMapping("/product/{productId}")
  public ResponseEntity<List<Rating>> getProductsReviewHandler(
    @PathVariable Long productId
  ) {
    List<Rating> ratings = ratingService.getProductRating(productId);
    return new ResponseEntity<>(ratings, HttpStatus.OK);
  }
}
