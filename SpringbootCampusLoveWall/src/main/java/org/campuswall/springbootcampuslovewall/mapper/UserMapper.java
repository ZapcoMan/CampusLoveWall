package org.campuswall.springbootcampuslovewall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.campuswall.springbootcampuslovewall.common.core.mapper.BaseMapperPlus;
import org.campuswall.springbootcampuslovewall.entity.User;

@Mapper
public interface UserMapper extends BaseMapperPlus<User, Integer> {
    User findByUsername(String username);
}
