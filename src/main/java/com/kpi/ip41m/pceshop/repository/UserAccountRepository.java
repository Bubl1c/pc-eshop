package com.kpi.ip41m.pceshop.repository;

import com.kpi.ip41m.pceshop.entity.account.UserAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Andrii on 29.10.2015.
 */
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    UserAccount findByUsername(String username);

}
