package org.campuswall.springbootcampuslovewall.user.service;

import org.campuswall.springbootcampuslovewall.common.core.service.BaseService;
import org.campuswall.springbootcampuslovewall.user.entity.User;


public interface UserService extends BaseService<User, Long> {
    /**
     * 根据用户名查找用户信息
     *
     * @param username 用户名
     * @return 返回匹配的用户对象，如果未找到则返回null
     */
    User findByUsername(String username);




}
