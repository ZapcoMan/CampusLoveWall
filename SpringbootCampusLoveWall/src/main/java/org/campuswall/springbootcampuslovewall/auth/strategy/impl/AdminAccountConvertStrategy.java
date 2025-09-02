package org.campuswall.springbootcampuslovewall.auth.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.auth.strategy.AccountConvertStrategy;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.common.utils.TokenUtils;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.springframework.stereotype.Component;

@Slf4j
@Component("ADMIN")
public class AdminAccountConvertStrategy implements AccountConvertStrategy<Admin> {

    /**
     * 将领域对象 T 转换成 Account DTO，并生成 token
     *
     * @param domain 领域对象
     * @return Account DTO
     */
    @Override
    public Account convertToAccount(Admin admin) {
        Account account = new Account();
        account.setId(Math.toIntExact(admin.getId()));
        account.setUsername(admin.getUsername());
        account.setRole(RoleEnum.ADMIN.getCode());
        String token = TokenUtils.createToken(admin.getId() + "-" + RoleEnum.ADMIN.getCode(), admin.getPassword());
        log.info("token: {}", token);
        account.setToken(token);
        return account;
    }

    /**
     * 将 Account DTO 转换成领域对象 T
     *
     * @param account
     */
    @Override
    public Admin convertToDomain(Account account) {
        log.info("account: {}", account);
       
        Admin admin = new Admin();
        admin.setUsername(account.getUsername());
        admin.setPassword(account.getPassword());
        return admin;
    }
}
