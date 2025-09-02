package org.campuswall.springbootcampuslovewall.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.common.core.mapper.BaseMapperPlus;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.user.entity.User;

@Mapper
public interface UserMapper extends BaseMapperPlus<User, Integer> {
        /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户对象，如果未找到返回null
     */
    User findByUsername(String username);

    /**
     * 保存用户信息
     *
     * @param user 用户对象
     * @return 是否保存成功
     */
    @Insert("insert into sys_user(username,password) values(#{username},#{password})")
    boolean save(User user);

    void updatePassword(User user);
}
