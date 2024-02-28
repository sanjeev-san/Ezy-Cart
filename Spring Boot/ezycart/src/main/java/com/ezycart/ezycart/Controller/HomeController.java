package com.ezycart.ezycart.Controller;

import com.ezycart.ezycart.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping("/node_modules")
  public ResponseEntity<ApiResponse> homeController() {
    ApiResponse res = new ApiResponse("Welcome to EZY-Cart", true);
    return new ResponseEntity<>(res, HttpStatus.OK);
  }
}
