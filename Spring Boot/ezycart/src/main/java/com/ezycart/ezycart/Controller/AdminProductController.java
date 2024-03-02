package com.ezycart.ezycart.Controller;

import com.ezycart.ezycart.Entities.Product;
import com.ezycart.ezycart.Exception.ProductException;
import com.ezycart.ezycart.Request.CreateProductRequest;
import com.ezycart.ezycart.Response.ApiResponse;
import com.ezycart.ezycart.Service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("/")
  public ResponseEntity<Product> createProductHandler(
    @RequestBody CreateProductRequest req
  ) throws ProductException {
    System.out.println();
    System.out.println("Up product service");
    Product createdProduct = productService.createProduct(req);
    System.out.println("below product service");
    return new ResponseEntity<Product>(createdProduct, HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/{productId}/delete")
  public ResponseEntity<ApiResponse> deleteProductHandler(
    @PathVariable Long productId
  ) throws ProductException {
    String msg = productService.deleteProduct(productId);
    System.out.println("delete product " + msg);
    ApiResponse res = new ApiResponse(msg, true);
    return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
  }

  @GetMapping("/all")
  public ResponseEntity<List<Product>> findAllProduct() {
    List<Product> products = productService.getAllProducts();
    return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
  }

  @PutMapping("/{productId}/update")
  public ResponseEntity<Product> updateProductHandler(
    @RequestBody Product req,
    @PathVariable Long productId
  ) throws ProductException {
    Product updatedProduct = productService.updateProduct(productId, req);

    return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
  }

  @PostMapping("/creates")
  public ResponseEntity<ApiResponse> createMultipleProduct(
    @RequestBody CreateProductRequest[] reqs
  ) throws ProductException {
    for (CreateProductRequest product : reqs) {
      productService.createProduct(product);
    }

    ApiResponse res = new ApiResponse("products created successfully", true);
    return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
  }
}
