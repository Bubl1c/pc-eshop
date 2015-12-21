package com.kpi.ip41m.pceshop.dto;

/**
 * Created by Andrii on 16.12.2015.
 */
public class OrderRowDto {
    private Integer quantity;
    private Long productId;

    public OrderRowDto() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
