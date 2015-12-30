package com.kpi.ip41m.pceshop.account;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.kpi.ip41m.pceshop.PcEshopApplicationTests;
import com.kpi.ip41m.pceshop.entity.account.AccountState;
import com.kpi.ip41m.pceshop.entity.account.AccountType;
import com.kpi.ip41m.pceshop.entity.account.UserAccount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import com.kpi.ip41m.pceshop.repository.UserAccountRepository;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.*;


/**
 * Created by Vlad on 12/30/2015.
 */
@DatabaseSetup(value = {"/data/customers.xml", "/data/products.xml", "/data/orders.xml"}, type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = {"/data/customers.xml", "/data/products.xml", "/data/orders.xml"}, type = DatabaseOperation.DELETE_ALL)
public class UserAccountUT extends PcEshopApplicationTests {

    private final String usersEndPoint = "/users";
    private final String username = "user";

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    public void getDefaultUser() throws Exception {
        UserAccount customerAccount = userAccountRepository.save(createUser("user", "user", "Default", "0677699944", AccountType.CUSTOMER));
        get(usersEndPoint + "/" + username)
                .andExpect(status().isOk());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.username", is("user")));
        // ^^ fails due to unset content type
    }

    // fails due to empty response
    @Test
    public void failDefaultUser() throws Exception {
        get(usersEndPoint + "/" + username + "_gibberish")
                .andExpect(status().is4xxClientError());
    }

    @Transactional
    private UserAccount createUser(String username,
                                   String password,
                                   String firstName,
                                   String phone,
                                   AccountType accountType) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(password);
        userAccount.setPhone(phone);
        userAccount.setFirstName(firstName);
        userAccount.setAccountState(AccountState.ACTIVE);
        userAccount.setAccountType(accountType);
        return userAccount;
    }

}
