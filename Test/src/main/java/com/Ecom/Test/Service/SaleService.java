package com.Ecom.Test.Service;

import com.Ecom.Test.IO.Request.SaleRequest;
import com.Ecom.Test.IO.Response.SaleResponse;
import com.Ecom.Test.Model.Product;
import com.Ecom.Test.Model.Sale;
import com.Ecom.Test.Repository.ProductRepository;
import com.Ecom.Test.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public SaleResponse addSale(SaleRequest saleRequest){
        Sale sale = saveSale(createSaleObject(saleRequest));
        return convertToResponse(sale);
    }

    public SaleResponse updateSale(SaleRequest saleRequest, Integer id){
        Sale sale = saleRepository.getById(id);
        sale.setQuantity(saleRequest.getQuantity());
        sale.setSaleDate(LocalDate.now());
        Product product = productRepository.getById(saleRequest.getProductId());
        List<Sale> saleList = product.getSaleList();
        saleList.add(sale);
        product.setSaleList(saleList);
        productRepository.save(product);
        sale.setProductDetails(product);
        return convertToResponse(saleRepository.save(sale));
    }

    public void deleteSaleById(Integer id){
        saleRepository.deleteById(id);
    }
    public Sale saveSale(Sale sale){
        return saleRepository.save(sale);
    }

    public Sale createSaleObject(SaleRequest saleRequest){
        Sale sale = new Sale();
        Product product = productRepository.getById(saleRequest.getProductId());
        List<Sale> saleList = product.getSaleList();
        saleList.add(sale);
        product.setSaleList(saleList);
        productRepository.save(product);
        sale.setProductDetails(product);
        sale.setQuantity(saleRequest.getQuantity());
        sale.setSaleDate(LocalDate.now());
        return sale;
    }

    public SaleResponse convertToResponse(Sale sale){
        SaleResponse saleResponse = new SaleResponse();
        saleResponse.setProductName(sale.getProductDetails().getName());
        saleResponse.setQuantity(sale.getQuantity());
        saleResponse.setPrice((sale.getProductDetails().getPrice()).multiply(new BigDecimal(sale.getQuantity())));
        return saleResponse;
    }
}
