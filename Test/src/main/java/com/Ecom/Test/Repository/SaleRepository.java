package com.Ecom.Test.Repository;

import com.Ecom.Test.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
