package org.campuswall.springbootcampuslovewall.user.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.common.core.service.impl.BaseServiceImpl;
import org.campuswall.springbootcampuslovewall.common.exception.CustomerException;
import org.campuswall.springbootcampuslovewall.common.utils.TokenUtils;
import org.campuswall.springbootcampuslovewall.entity.Account;

import org.campuswall.springbootcampuslovewall.mapper.UserMapper;

import org.campuswall.springbootcampuslovewall.user.entity.User;
import org.campuswall.springbootcampuslovewall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 用户服务实现类
 * 提供用户相关的业务逻辑实现
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserMapper> implements UserService {

    @Resource
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        super(userMapper);
    }


    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 返回匹配的用户对象，如果未找到则返回null
     */
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 根据ID查询用户信息
     *
     * @param id 用户ID
     * @return 返回匹配的用户对象，如果未找到则返回null
     */
    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据主键ID删除实体对象
     *
     * @param id 主键ID（Integer类型）
     */
    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public List<User> selectAll() {
        return List.of();
    }

    /**
     * 用户登录方法
     *
     * @param account 包含用户名和密码的账户信息
     * @return 登录成功的用户对象
     * @throws CustomerException 如果账号不存在或密码错误，则抛出此异常
     */
    @Override
    public Account login(Account account) {
        // 验证账号是否存在
        User dbUser = userMapper.findByUsername(account.getUsername());
        if (dbUser == null) {
            throw new CustomerException("账号不存在");
        }

        // 获取输入的密码和数据库中的密码
        String inputPassword = account.getPassword();
        String dbUserPassword = dbUser.getPassword();

        // 生成输入密码的摘要
        String inputHash = DigestUtil.md5Hex(inputPassword);
        // 验证密码是否匹配
        boolean isValid = dbUserPassword.equals(inputHash);

        // 如果密码不正确，抛出异常
        if (!isValid) {
            throw new CustomerException("账号或密码错误");
        }

        // 创建token并返回给前端
        String token = TokenUtils.createToken(dbUser.getId() + "-" + "USER", dbUser.getPassword());
        Account loginAccount = new Account();
        loginAccount.setId(Math.toIntExact(dbUser.getId()));
        loginAccount.setUsername(dbUser.getUsername());
        loginAccount.setRole("USER");
        loginAccount.setToken(token);
        return loginAccount;
    }

    /**
     * 更新用户密码
     *
     * @param account 包含新密码信息的账户对象
     */
    @Override
    public void updatePassword(Account account) {
        // 可根据需要实现密码更新逻辑
    }



    /**
     * 根据ID更新用户信息
     *
     * @param id 用户ID
     */
    @Override
    public void updateById(Long id) {

    }

    /**
     * 插入用户信息
     *
     * @param user 用户实体对象
     */
    @Override
    public void insert(User user) {

    }



    /**
     * 批量保存或更新用户信息
     *
     * @param userList  用户实体列表
     * @param batchSize 批量大小
     * @return 是否操作成功
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<User> userList, int batchSize) {
        return false;
    }

    /**
     * 根据实体更新用户信息
     *
     * @param user 用户实体对象
     * @return 是否更新成功
     */
    @Override
    public boolean updateById(User user) {
        return false;
    }


    /**
     * 保存或更新用户信息
     *
     * @param user 用户实体对象
     * @return 是否操作成功
     */
    @Override
    public boolean saveOrUpdate(User user) {
        return false;
    }


    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList, int batchSize) {
        return false;
    }

    @Override
    public User getOne(Wrapper<User> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<User> queryWrapper) {
        return Map.of();
    }

    @Override
    public <V> V getObj(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    /**
     * 获取基础Mapper
     *
     * @return BaseMapper对象
     */
    @Override
    public BaseMapper<User> getBaseMapper() {
        return null;
    }

    /**
     * 获取实体类类型
     *
     * @return User类的Class对象
     */
    @Override
    public Class<User> getEntityClass() {
        return null;
    }

    /**
     * 根据ID删除用户
     *
     * @param id 用户ID
     */
    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    /**
     * 分页查询管理员信息列表
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param admin    查询条件对象
     * @return 分页信息对象
     */
    @Override
    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin) {
        return null;
    }
}
