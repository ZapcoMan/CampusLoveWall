package org.campuswall.springbootcampuslovewall.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.common.core.mapper.BaseMapperPlus;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.user.entity.User;

/**
 * 用户数据访问接口
 * 提供对用户数据的基本操作，包括查询、保存和更新密码等
 */
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

    /**
     * 根据用户ID更新密码
     *
     * @param id 用户ID
     * @param hash 经过加密的密码字符串
     */
    void updatePasswordById(Long id, String hash);

}
