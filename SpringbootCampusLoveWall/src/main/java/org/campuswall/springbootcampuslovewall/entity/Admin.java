package org.campuswall.springbootcampuslovewall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员信息
 * 继承自Account类，包含管理员的基本信息和认证相关信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends Account {
    /**
     * 管理员ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;

    /**
     * 姓名
     */
    private String name;

    /**
     * 认证令牌
     */
    private String token;

    /**
     * 获取认证令牌
     * @return 认证令牌
     */
    @Override
    public String getToken() {
        return token;
    }

    /**
     * 设置认证令牌
     * @param token 认证令牌
     */
    @Override
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 获取头像URL
     * @return 头像URL
     */
    @Override
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像URL
     * @param avatar 头像URL
     */
    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
