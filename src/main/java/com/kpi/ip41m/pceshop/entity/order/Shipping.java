package com.kpi.ip41m.pceshop.entity.order;

import com.kpi.ip41m.pceshop.entity.account.Address;

import javax.persistence.*;

/**
 * Created by Andrii on 17.11.2015.
 */
@Entity
public class Shipping {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ShippingType type;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address shipTo;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingType getType() {
        return type;
    }

    public void setType(ShippingType type) {
        this.type = type;
    }

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
