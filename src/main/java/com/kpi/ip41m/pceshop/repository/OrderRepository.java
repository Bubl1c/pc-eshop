package com.kpi.ip41m.pceshop.repository;

import com.kpi.ip41m.pceshop.entity.order.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andrii on 17.11.2015.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findByCustomerAccountUsername(String username);
}
