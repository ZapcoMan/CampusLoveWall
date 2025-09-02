package org.campuswall.springbootcampuslovewall.user.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.campuswall.springbootcampuslovewall.common.core.service.BaseService;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.user.entity.User;

import java.util.List;


public interface UserService extends BaseService<User, Long> {
    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 返回匹配的用户对象，如果未找到则返回null
     */
    User findByUsername(String username);

    /**
     * 用户登录
     *
     * @param account 账户信息
     * @return 登录成功的账户信息
     */
    Account login(Account account);

    /**
     * 更新用户密码
     *
     * @param account 账户信息
     */
    void updatePassword(Account account);



}
