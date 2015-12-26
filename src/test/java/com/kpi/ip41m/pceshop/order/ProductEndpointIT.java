package com.kpi.ip41m.pceshop.order;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kpi.ip41m.pceshop.PcEshopApplicationTests;
import com.kpi.ip41m.pceshop.entity.product.Product;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vova on 26.12.2015.
 */
@DatabaseSetup(value = {"/data/customers.xml", "/data/products.xml", "/data/orders.xml"}, type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = {"/data/customers.xml", "/data/products.xml", "/data/orders.xml"}, type = DatabaseOperation.DELETE_ALL)
public class ProductEndpointIT extends PcEshopApplicationTests {
    private String productEndPoint = "/products";

    @Test
    public void shouldAddNewProduct() throws Exception {
        post(productEndPoint, getNewProduct()).andExpect(status().isOk());
    }

    private Product getNewProduct(){
        Product product = new Product();
        product.setId(3L);
        product.setName("Super new product");
        product.setDescription("Description for super new product");
        product.setPrice(123.23);
        return product;
    }
}
