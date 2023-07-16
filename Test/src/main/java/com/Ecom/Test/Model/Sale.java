package com.Ecom.Test.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    private static final long serialVersionUID = -4444850416977012349L;

    @Id
    @GeneratedValue
    @Column(name = "sale_id")
    private Integer saleId;

    @Column(name = "saleDate")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate saleDate;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="product_id")
    @NonNull
    @JsonIgnore
    private Product productDetails;

}
