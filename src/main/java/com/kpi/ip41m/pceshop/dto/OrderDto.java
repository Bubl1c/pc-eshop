package com.kpi.ip41m.pceshop.dto;

import com.kpi.ip41m.pceshop.entity.order.Payment;

import java.util.List;

/**
 * Created by Andrii on 16.12.2015.
 */
public class OrderDto {
    private String customerId;
    private Payment payment;
    private ShippingDto shipping;
    private List<OrderRowDto> rows;

    public OrderDto() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public ShippingDto getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDto shipping) {
        this.shipping = shipping;
    }

    public List<OrderRowDto> getRows() {
        return rows;
    }

    public void setRows(List<OrderRowDto> rows) {
        this.rows = rows;
    }
}
