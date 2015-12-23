package com.kpi.ip41m.pceshop.controller;

import com.kpi.ip41m.pceshop.dto.OrderDto;
import com.kpi.ip41m.pceshop.dto.OrderRowDto;
import com.kpi.ip41m.pceshop.dto.ShippingDto;
import com.kpi.ip41m.pceshop.entity.account.Address;
import com.kpi.ip41m.pceshop.entity.account.Customer;
import com.kpi.ip41m.pceshop.entity.order.*;
import com.kpi.ip41m.pceshop.entity.product.Product;
import com.kpi.ip41m.pceshop.repository.CustomerRepository;
import com.kpi.ip41m.pceshop.repository.OrderRepository;
import com.kpi.ip41m.pceshop.repository.ProductRepository;
import com.sun.istack.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Andrii on 16.12.2015.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public Iterable<Order> getByUsername(@PathVariable("username") String username) {
        return orderRepository.findByCustomerAccountUsername(username);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Order> get() {
        return orderRepository.findAll();
    }

    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.POST)
    public void setStatus(@PathVariable("id") Long id, @PathVariable("status") OrderStatus status) {
        Order order = orderRepository.findOne(id);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody OrderDto orderDto) {
        Customer customer = customerRepository.findByAccountUsername(orderDto.getCustomerId());
        Order order = new Order();
        order.setCustomer(customer);
        order.setPayment(orderDto.getPayment());
        order.setShipping(prepareShipping(orderDto));
        order.setRows(prepareRows(orderDto));
        orderRepository.save(order);
        return order.getId();
    }

    private Shipping prepareShipping(OrderDto orderDto) {
        Shipping shipping = new Shipping();
        ShippingDto dto = orderDto.getShipping();

        Address address = new Address();
        address.setCountry(dto.getCountry());
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setBuildingNumber(dto.getBuildingNumber());
        address.setApartment(dto.getApartment());

        shipping.setShipTo(address);
        shipping.setType(dto.getType());
        shipping.setPrice(dto.getPrice());

        return shipping;
    }

    private List<OrderRow> prepareRows(OrderDto dto) {
        List<OrderRow> orderRows = new ArrayList<>();
        List<OrderRowDto> orderRowDtos = Optional.ofNullable(dto.getRows()).orElseGet(ArrayList::new);
        for(OrderRowDto orderRowDto : orderRowDtos) {
            Product product = productRepository.findOne(orderRowDto.getProductId());
            OrderRow row = new OrderRow();
            row.setProduct(product);
            row.setQuantity(orderRowDto.getQuantity());
            orderRows.add(row);
        }
        return orderRows;
    }

}
