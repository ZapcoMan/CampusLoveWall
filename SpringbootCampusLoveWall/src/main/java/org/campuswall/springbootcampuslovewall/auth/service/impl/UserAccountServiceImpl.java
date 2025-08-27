package org.campuswall.springbootcampuslovewall.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.auth.service.AccountService;
import org.campuswall.springbootcampuslovewall.common.core.service.impl.BaseServiceImpl;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.mapper.UserMapper;

import org.campuswall.springbootcampuslovewall.user.entity.User;
import org.campuswall.springbootcampuslovewall.user.service.UserService;
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
public class UserAccountServiceImpl extends BaseServiceImpl<Account, String, UserMapper> implements AccountService {

    @Resource
    private UserService userService;

    public UserAccountServiceImpl(UserMapper userMapper) {
        super(userMapper);
    }

    @Override
    public Account selectById(Long aLong) {
        User user = userService.selectById(aLong);
        if (user == null) {
            return null;
        }
        Account account = new Account();
        account.setId(Math.toIntExact(user.getId()));
        account.setUsername(user.getUsername());
        account.setPassword(user.getPassword());
        account.setRole(user.getRole());
        return account;
    }

    @Override
    public RoleEnum getRole() {
        return RoleEnum.USER;
    }

    @Override
    public Account selectById(String s) {
        org.campuswall.springbootcampuslovewall.user.entity.User user = userService.selectById(Long.valueOf(s));
        if (user == null) {
            return null;
        }
        Account account = new Account();
        account.setId(Math.toIntExact(user.getId()));
        account.setUsername(user.getUsername());
        account.setPassword(user.getPassword());
        account.setRole(user.getRole());
        return account;
    }

    @Override
    public void updatePassword(Account account) {
        userService.updatePassword(account);
    }

    @Override
    public Account login(Account account) {
        return userService.login(account);
    }

    @Override
    public List<Account> selectAll() {
        return List.of();
    }

    @Override
    public void updateById(Long id) {

    }

    @Override
    public void insert(Account entity) {

    }

    @Override
    public boolean saveBatch(Collection<Account> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Account> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateById(Account entity) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Account> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Account entity) {
        return false;
    }

    @Override
    public Account getOne(Wrapper<Account> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Account> queryWrapper) {
        return Map.of();
    }

    @Override
    public <V> V getObj(Wrapper<Account> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Account> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Account> getEntityClass() {
        return null;
    }

    @Override
    public void deleteById(String s) {

    }
}
