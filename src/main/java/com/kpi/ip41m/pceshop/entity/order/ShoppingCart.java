package com.kpi.ip41m.pceshop.entity.order;

import com.kpi.ip41m.pceshop.entity.order.OrderRow;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 17.11.2015.
 */
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date created;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> rows;
}
