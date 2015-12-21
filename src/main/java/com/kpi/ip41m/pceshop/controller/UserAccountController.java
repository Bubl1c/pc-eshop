package com.kpi.ip41m.pceshop.controller;

import com.kpi.ip41m.pceshop.entity.account.UserAccount;
import com.kpi.ip41m.pceshop.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * Created by Andrii on 16.12.2015.
 */
@RestController
@RequestMapping("/users")
public class UserAccountController {
    @Autowired
    UserAccountRepository userAccountRepository;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public UserAccount execute(@PathVariable("username") String username) {
        return userAccountRepository.findByUsername(username);
    }
}
