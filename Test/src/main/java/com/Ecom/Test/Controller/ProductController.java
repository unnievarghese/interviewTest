package com.Ecom.Test.Controller;

import com.Ecom.Test.IO.Request.ProductRequest;
import com.Ecom.Test.Service.ProductService;
import com.Ecom.Test.Utils.ApiResponse;
import com.Ecom.Test.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Secured({"ADMIN"})
    @PostMapping("/addProduct")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductRequest productRequest) {
        Map data = new HashMap();
        data.put(Constants.DATA, productService.addProduct(productRequest));
        return ResponseEntity.ok(new ApiResponse(data, Constants.PRODUCT_ADDED, Constants.SUCCESS));
    }

    @Secured({"ROLE_ADMIN"})
    @PatchMapping("/updateProduct")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductRequest productRequest, @RequestParam Integer id) throws Exception {
        Map data = new HashMap();
        data.put(Constants.DATA, productService.updateProduct(productRequest, id));
        return ResponseEntity.ok(new ApiResponse(data, Constants.PRODUCT_UPDATED, Constants.SUCCESS));
    }

    @GetMapping("/getProduct")
    public ResponseEntity<ApiResponse> getProductById(@RequestParam Integer id) throws Exception {
        Map data = new HashMap();
        data.put(Constants.DATA, productService.getProductById(id));
        return ResponseEntity.ok(new ApiResponse(data, Constants.PRODUCT_FOUND, Constants.SUCCESS));
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<ApiResponse> getAllProducts(@RequestParam int page, @RequestParam int size) throws Exception {
        Map data = new HashMap();
        data.put(Constants.DATA, productService.getAllProducts(page, size));
        return ResponseEntity.ok(new ApiResponse(data, Constants.PRODUCT_FOUND, Constants.SUCCESS));
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteProductById(@RequestParam Integer id){
        Map data = new HashMap();
        productService.deleteProductById(id);
        return ResponseEntity.ok(new ApiResponse(data, Constants.PRODUCT_DELETED, Constants.SUCCESS));
    }

    @GetMapping("/totalRevenue")
    public ResponseEntity<ApiResponse> getTotalRevenue(){
        Map data = new HashMap();
        data.put(Constants.DATA, productService.getTotalRevenue());
        return ResponseEntity.ok(new ApiResponse(data, Constants.PRODUCT_DELETED, Constants.SUCCESS));
    }

    @GetMapping("/totalRevenueByProduct")
    public ResponseEntity<ApiResponse> totalRevenueByProduct(@RequestParam Integer id){
        Map data = new HashMap();
        data.put(Constants.DATA, productService.totalRevenueByProduct(id));
        return ResponseEntity.ok(new ApiResponse(data, Constants.PRODUCT_DELETED, Constants.SUCCESS));
    }
}
