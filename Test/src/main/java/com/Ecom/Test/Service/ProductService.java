package com.Ecom.Test.Service;

import com.Ecom.Test.IO.Request.ProductRequest;
import com.Ecom.Test.IO.Response.ProductResponse;
import com.Ecom.Test.IO.Response.TotalRevenueByProduct;
import com.Ecom.Test.IO.Response.TotalRevenueResponse;
import com.Ecom.Test.Model.Product;
import com.Ecom.Test.Model.Sale;
import com.Ecom.Test.Repository.ProductRepository;
import com.Ecom.Test.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    public ProductResponse addProduct(ProductRequest productRequest){
        Product product = saveProduct(createProductObject(productRequest));
        return convertToResponse(product);
    }

    public ProductResponse updateProduct(ProductRequest productRequest, Integer id) {
        Product product = productRepository.getById(id);
        product.setQuantity(productRequest.getQuantity());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        return convertToResponse(saveProduct(product));
    }

    public ProductResponse getProductById(Integer id){
        return convertToResponse(productRepository.getById(id));
    }

    public List<ProductResponse> getAllProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> list = productRepository.findAll(pageable);
        return list.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }

    public TotalRevenueResponse getTotalRevenue(){
        List<Sale> saleList = saleRepository.findAll();
        List<TotalRevenueByProduct> totalRevenueByProductList = new ArrayList<>();
        TotalRevenueResponse totalRevenueResponse = new TotalRevenueResponse();
        BigDecimal totalRevenue = new BigDecimal(0);
        for(Sale sale : saleList){
            TotalRevenueByProduct totalRevenueByProduct = new TotalRevenueByProduct();
            totalRevenueByProduct.setProductName(sale.getProductDetails().getName());
            totalRevenueByProduct.setQuantity(sale.getQuantity());
            BigDecimal salePrice = sale.getProductDetails().getPrice().multiply(new BigDecimal(sale.getQuantity()));
            totalRevenueByProduct.setPrice(salePrice);
            totalRevenue = totalRevenue.add(salePrice);
            totalRevenueByProductList.add(totalRevenueByProduct);
            totalRevenueResponse.setTotalRevenueByProduct(totalRevenueByProductList);
        }
        totalRevenueResponse.setTotalRevenue(totalRevenue);
        return totalRevenueResponse;
    }

    public TotalRevenueByProduct totalRevenueByProduct(Integer id){
        List<Sale> saleList = productRepository.getById(id).getSaleList();
        TotalRevenueByProduct totalRevenueByProduct = new TotalRevenueByProduct();
        totalRevenueByProduct.setProductName(saleList.get(0).getProductDetails().getName());
        Integer quantity = 0;
        for(Sale sale : saleList){
            quantity += sale.getQuantity();
        }
        totalRevenueByProduct.setQuantity(quantity);
        totalRevenueByProduct.setPrice(saleList.get(0).getProductDetails().getPrice().multiply(new BigDecimal(quantity)));
        return totalRevenueByProduct;
    }

    public Product createProductObject(ProductRequest productRequest){
        Product product = new Product();
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setQuantity(productRequest.getQuantity());
        product.setPrice(productRequest.getPrice());
        return product;
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public ProductResponse convertToResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setPrice(product.getPrice());
        if(!product.getSaleList().isEmpty())
            productResponse.setSaleList(product.getSaleList());
        return productResponse;
    }
}
