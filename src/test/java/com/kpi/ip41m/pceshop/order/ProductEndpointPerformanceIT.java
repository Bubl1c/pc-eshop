package com.kpi.ip41m.pceshop.order;

import com.kpi.ip41m.pceshop.PcEshopApplicationTests;
import com.kpi.ip41m.pceshop.entity.product.Product;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vova on 26.12.2015.
 */
@PerfTest(duration = 10000, threads = 50, timer = RandomTimer.class, timerParams = {5, 15})
@Required(max = 500, average = 100)
public class ProductEndpointPerformanceIT  extends PcEshopApplicationTests {

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
