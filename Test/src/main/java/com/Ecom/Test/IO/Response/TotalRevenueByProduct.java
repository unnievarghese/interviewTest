package com.Ecom.Test.IO.Response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TotalRevenueByProduct {

    private String productName;

    private Integer quantity;

    private BigDecimal price;
}
