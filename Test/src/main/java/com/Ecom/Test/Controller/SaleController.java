package com.Ecom.Test.Controller;

import com.Ecom.Test.IO.Request.SaleRequest;
import com.Ecom.Test.Service.SaleService;
import com.Ecom.Test.Utils.ApiResponse;
import com.Ecom.Test.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/sale")
public class SaleController {

    @Autowired
    SaleService saleService;

    @PostMapping("/addSale")
    public ResponseEntity<ApiResponse> addSale(@RequestBody SaleRequest saleRequest) {
        Map data = new HashMap();
        data.put(Constants.DATA, saleService.addSale(saleRequest));
        return ResponseEntity.ok(new ApiResponse(data, Constants.SALE_ADDED, Constants.SUCCESS));
    }

    @Secured({"ROLE_ADMIN"})
    @PatchMapping("/updateSale")
    public ResponseEntity<ApiResponse> updateSale(@RequestBody SaleRequest saleRequest, @RequestParam Integer id) throws Exception {
        Map data = new HashMap();
        data.put(Constants.DATA, saleService.updateSale(saleRequest, id));
        return ResponseEntity.ok(new ApiResponse(data, Constants.SALE_UPDATED, Constants.SUCCESS));
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteSaleById(@RequestParam Integer id){
        Map data = new HashMap();
        saleService.deleteSaleById(id);
        return ResponseEntity.ok(new ApiResponse(data, Constants.SALE_DELETED, Constants.SUCCESS));
    }
}
