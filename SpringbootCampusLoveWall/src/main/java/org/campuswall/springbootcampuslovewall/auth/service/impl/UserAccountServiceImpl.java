package org.campuswall.springbootcampuslovewall.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.auth.service.AccountService;
import org.campuswall.springbootcampuslovewall.common.core.service.impl.BaseServiceImpl;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.mapper.UserMapper;

import org.campuswall.springbootcampuslovewall.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 用户账户服务实现类
 * 实现了AccountService接口，用于处理普通用户账户相关的业务逻辑
 */
@Service
public class UserAccountServiceImpl extends BaseServiceImpl<User, Integer, UserMapper> implements AccountService<User> {

    @Resource
    private UserMapper userMapper;

    /**
     * 构造函数，初始化用户映射器
     *
     * @param userMapper 用户数据映射器
     */
    public UserAccountServiceImpl(UserMapper userMapper) {
        super(userMapper);
    }


}
