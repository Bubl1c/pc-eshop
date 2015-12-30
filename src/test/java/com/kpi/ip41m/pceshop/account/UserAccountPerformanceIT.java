package com.kpi.ip41m.pceshop.account;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vlad on 12/30/2015.
 */
@PerfTest(duration = 10000, threads = 50, timer = RandomTimer.class, timerParams = { 5, 15 })
@Required(max = 1000, average = 200)
public class UserAccountPerformanceIT extends PcEshopApplicationTests {

    private final String usersEndPoint = "/users";
    private final String username = "user";

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    @Test
    public void getDefaultUser() throws Exception {
        get(usersEndPoint + "/" + username)
                .andExpect(status().isOk());
    }
}
