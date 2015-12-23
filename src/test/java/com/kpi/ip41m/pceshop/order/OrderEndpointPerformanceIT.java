package com.kpi.ip41m.pceshop.order;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kpi.ip41m.pceshop.PcEshopApplicationTests;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Dmytro on 23.12.2015.
 */
@PerfTest(duration = 10000, threads = 50, timer = RandomTimer.class, timerParams = { 5, 15 })
@Required(max = 500, average = 100)
public class OrderEndpointPerformanceIT extends PcEshopApplicationTests {

    private final String ordersEndpointURL = "/orders/";

    private final String username = "test";

    @Rule
    public ContiPerfRule i = new ContiPerfRule();


    @Test
    public void shouldReturnAllOrdersForUser() throws Exception {
        get(ordersEndpointURL + username)
                .andExpect(status().isOk());
    }
}
