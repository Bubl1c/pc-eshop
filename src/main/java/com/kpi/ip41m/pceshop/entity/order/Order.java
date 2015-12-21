package com.kpi.ip41m.pceshop.entity.order;

import com.kpi.ip41m.pceshop.entity.account.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii on 17.11.2015.
 */
@Entity
@Table(name = "CustomerOrder")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Date ordered = new Date();
    private Date shipped;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Shipping shipping;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderRow> rows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public Date getShipped() {
        return shipped;
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public List<OrderRow> getRows() {
        return rows;
    }

    public void setRows(List<OrderRow> rows) {
        this.rows = rows;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
