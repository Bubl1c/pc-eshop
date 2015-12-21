package com.kpi.ip41m.pceshop.repository;

import com.kpi.ip41m.pceshop.entity.account.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andrii on 17.11.2015.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long>{
    Customer findByAccountUsername(String username);
}
