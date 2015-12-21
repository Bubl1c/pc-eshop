package com.kpi.ip41m.pceshop.repository;

import com.kpi.ip41m.pceshop.entity.product.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andrii on 17.11.2015.
 */
public interface ProductRepository extends CrudRepository<Product, Long>{
}
