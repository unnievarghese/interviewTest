package com.Ecom.Test.IO.Response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SaleResponse {

    private Integer quantity;

    private String productName;

    private BigDecimal price;
}
