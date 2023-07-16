package com.Ecom.Test.IO.Request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;
}
