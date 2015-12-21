package com.kpi.ip41m.pceshop.dto;

import com.kpi.ip41m.pceshop.entity.order.ShippingType;

/**
 * Created by Andrii on 16.12.2015.
 */
public class ShippingDto {
    private ShippingType type;

    private String country;
    private String city;
    private String street;
    private String buildingNumber;
    private String apartment;
    private String zip;

    private Double price;

    public ShippingDto() {
    }

    public ShippingType getType() {
        return type;
    }

    public void setType(ShippingType type) {
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
