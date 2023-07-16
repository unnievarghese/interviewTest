package com.Ecom.Test.IO.Response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TotalRevenueResponse {

    private BigDecimal totalRevenue;

    private List<TotalRevenueByProduct> TotalRevenueByProduct;
}
