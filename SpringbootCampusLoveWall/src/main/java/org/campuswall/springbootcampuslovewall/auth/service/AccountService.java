package org.campuswall.springbootcampuslovewall.auth.service;

import org.campuswall.springbootcampuslovewall.common.core.service.BaseService;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.entity.Account;

/**
 * AccountService接口定义了账户服务的通用操作，包括登录、密码更新和根据角色的不同行为
 */
public interface AccountService<T> extends BaseService<Account, Integer> {

    RoleEnum getRole();  // 标明服务支持哪个角色


    T selectById(Integer id);


    void updatePassword(Account account);


    T login(Account account);

//    /**
//     * 注册新用户默认实现抛出异常，因为不是所有角色都支持注册操作
//     *
//     * @param user 用户对象，包含注册信息
//     * @throws UnsupportedOperationException 如果该角色不支持注册操作
//     */
//    default void register(User user) {
//        throw new UnsupportedOperationException("该角色不支持注册");
//    }
}
