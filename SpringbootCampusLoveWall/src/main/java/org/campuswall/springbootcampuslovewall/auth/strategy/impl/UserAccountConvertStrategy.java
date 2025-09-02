package org.campuswall.springbootcampuslovewall.auth.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.auth.strategy.AccountConvertStrategy;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.common.utils.TokenUtils;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.user.entity.User;
import org.springframework.stereotype.Component;

@Slf4j
@Component("USER")
public class UserAccountConvertStrategy implements AccountConvertStrategy<User> {


    /**
     * 将领域对象 T 转换成 Account DTO，并生成 token
     *
     * @param user 领域对象
     * @return user DTO
     */
    @Override
    public Account convertToAccount(User user) {
        Account account = new Account();
        account.setId(Math.toIntExact(user.getId()));
        account.setUsername(user.getUsername());
        account.setRole(RoleEnum.USER.getCode());
        String token = TokenUtils.createToken(user.getId() + "-" + RoleEnum.USER.getCode(), user.getPassword());
        account.setToken(token);
        return account;
    }

    /**
     * 将 Account DTO 转换成领域对象 T
     *
     * @param account
     */
    @Override
    public User convertToDomain(Account account) {
        log.info("account: {}", account);
        User user = new User();
        user.setUsername(account.getUsername());
        user.setPassword(account.getPassword()); // 注意：需要前端传过来密码
        return user;
    }
}
