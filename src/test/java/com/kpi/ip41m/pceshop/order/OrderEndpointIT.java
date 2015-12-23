package com.kpi.ip41m.pceshop.order;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kpi.ip41m.pceshop.PcEshopApplicationTests;
import com.kpi.ip41m.pceshop.dto.OrderDto;
import com.kpi.ip41m.pceshop.dto.OrderRowDto;
import com.kpi.ip41m.pceshop.dto.ShippingDto;
import com.kpi.ip41m.pceshop.entity.order.Payment;
import org.junit.Test;


import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

/**
 * Created by Dmytro on 23.12.2015.
 */
@DatabaseSetup(value = {"/data/customers.xml", "/data/products.xml", "/data/orders.xml"}, type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = {"/data/customers.xml", "/data/products.xml", "/data/orders.xml"}, type = DatabaseOperation.DELETE_ALL)
public class OrderEndpointIT extends PcEshopApplicationTests{

    private final String ordersEndpointURL = "/orders/";

    private final String username = "test";

    @Test
    public void shouldReturnAllOrdersForUser() throws Exception {
        get(ordersEndpointURL + username)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void shouldSaveNewOrder() throws Exception {
        post(ordersEndpointURL, newOrder())
                .andExpect(status().isOk());


    }

    private OrderDto newOrder(){
        ShippingDto shippingDto = new ShippingDto();
        shippingDto.setCountry("USA");

        Payment payment = new Payment();
        payment.setDetails("Free for bosses");

        OrderRowDto orderRowDto = new OrderRowDto();
        orderRowDto.setProductId(2l);
        orderRowDto.setQuantity(3);

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId("8");
        orderDto.setPayment(payment);
        orderDto.setShipping(shippingDto);
//        orderDto.setRows(Arrays.asList(orderRowDto));

        return orderDto;
    }

}
