package com.kpi.ip41m.pceshop.entity.order;

import com.kpi.ip41m.pceshop.entity.product.Product;

import javax.persistence.*;

/**
 * Created by Andrii on 17.11.2015.
 */
@Entity
public class OrderRow {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
