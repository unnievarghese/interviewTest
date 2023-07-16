package com.Ecom.Test.CustomExceptionHandler;

import com.Ecom.Test.Utils.ApiResponse;
import com.Ecom.Test.Utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex) {
        Map data = new HashMap();
        ApiResponse error = new ApiResponse(data, ex.getMessage(), Constants.FAILED);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
