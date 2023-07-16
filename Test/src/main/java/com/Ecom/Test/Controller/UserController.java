package com.Ecom.Test.Controller;

import com.Ecom.Test.IO.Request.SaleRequest;
import com.Ecom.Test.IO.Request.UserLoginRequest;
import com.Ecom.Test.IO.Request.UserRequest;
import com.Ecom.Test.IO.Response.UserResponse;
import com.Ecom.Test.Model.User;
import com.Ecom.Test.Service.UserService;
import com.Ecom.Test.Utils.ApiResponse;
import com.Ecom.Test.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest userRequest) {
        Map data = new HashMap();
        data.put(Constants.DATA, userService.createUser(userRequest));
        return ResponseEntity.ok(new ApiResponse(data, Constants.USER_ADDED, Constants.SUCCESS));
    }

    @GetMapping("/getUser")
    public ResponseEntity<ApiResponse> getUser(@RequestParam Integer id) {
        Map data = new HashMap();
        data.put(Constants.DATA, userService.getUserById(id));
        return ResponseEntity.ok(new ApiResponse(data, Constants.USER_ADDED, Constants.SUCCESS));
    }
}
