package org.campuswall.springbootcampuslovewall.auth.service;

import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;

/**
 * AccountService接口定义了账户服务的通用操作，包括登录、密码更新和根据角色的不同行为
 *
 * @param <T> 账户类型泛型参数
 */
public interface AccountService<T,ID> {
    /**
     * 获取账户角色枚举
     *
     * @return RoleEnum 角色枚举值
     */
    RoleEnum getRole();

    /**
     * 根据ID查询账户信息
     *
     * @param id 账户ID
     * @return T 账户对象
     */
    T selectById(ID id);

    /**
     * 账户登录
     *
     * @param entity 账户信息
     * @return T 登录成功的账户对象
     */
    T login(T entity);

    /**
     * 更新账户密码
     *
     * @param entity 包含密码信息的账户对象
     */
    void updatePassword(T entity);

    /**
     * 注册新账户
     *
     * @param entity 用户对象
     */
    void register(T entity);

    /**
     * 重置账户密码
     *
     * @param email 用户邮箱
     */
    void resetPassword(String email);

    /**
     * 锁定账户
     *
     * @param id 账户ID
     */
    void lockAccount(ID id);

    /**
     * 解锁账户
     *
     * @param id 账户ID
     */
    void unlockAccount(ID id);

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
