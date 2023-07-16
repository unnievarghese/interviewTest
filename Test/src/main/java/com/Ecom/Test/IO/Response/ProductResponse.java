package com.Ecom.Test.IO.Response;

import com.Ecom.Test.Model.Sale;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private List<Sale> saleList;
}
