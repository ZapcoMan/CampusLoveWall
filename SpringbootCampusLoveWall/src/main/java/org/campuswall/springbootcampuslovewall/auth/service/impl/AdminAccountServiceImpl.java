package org.campuswall.springbootcampuslovewall.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.common.core.service.impl.BaseServiceImpl;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.mapper.AdminMapper;
import org.campuswall.springbootcampuslovewall.auth.service.AccountService;
import org.campuswall.springbootcampuslovewall.admin.service.AdminService;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 管理员账户服务实现类
 * 实现了AccountService接口，用于处理管理员账户相关的业务逻辑
 */
@Service
public class AdminAccountServiceImpl extends BaseServiceImpl<Account, String, AdminMapper> implements AccountService {


    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AdminService adminService;

    /**
     * 构造函数，初始化管理员映射器
     *
     * @param adminMapper 管理员数据映射器
     */
    public AdminAccountServiceImpl(AdminMapper adminMapper) {
        super(adminMapper);
    }

    /**
     * 获取角色枚举值
     *
     * @return RoleEnum.ADMIN 管理员角色枚举
     */
    @Override
    public RoleEnum getRole() {
        return RoleEnum.ADMIN;
    }

    @Override
    public Account selectById(Integer id) {
        Admin admin = adminMapper.selectById(id);

        Account account = new Account();
        account.setId(Math.toIntExact(admin.getId()));
        account.setUsername(admin.getUsername());
        account.setPassword(admin.getPassword());
        account.setRole(admin.getRole());
        return account;
    }

    /**
     * 根据ID查询账户信息
     *
     * @param id 账户ID
     * @return Account 账户信息
     */
    @Override
    public Account selectById(Long id) {
        Admin admin = adminMapper.selectById(id);

        Account account = new Account();
        account.setId(Math.toIntExact(admin.getId()));
        account.setUsername(admin.getUsername());
        account.setPassword(admin.getPassword());
        account.setRole(admin.getRole());
        return account;
    }

    /**
     * 根据ID查询账户信息
     *
     * @param id 账户ID
     * @return Account 账户信息
     */
    @Override
    public Account selectById(String id) {
        Admin admin = adminService.selectById(Long.valueOf(id));
        Account account = new Account();
        account.setId(Math.toIntExact(admin.getId()));
        account.setUsername(admin.getUsername());
        account.setPassword(admin.getPassword());
        account.setRole(admin.getRole());
        return account;
    }

    @Override
    public void deleteById(Integer id) {

    }

    /**
     * 查询所有账户信息
     *
     * @return List<Account> 账户信息列表
     */
    @Override
    public List<Account> selectAll() {
        return List.of();
    }

    /**
     * 根据ID更新账户信息
     *
     * @param id 账户ID
     */
    @Override
    public void updateById(Long id) {

    }

    /**
     * 插入账户信息
     *
     * @param account 账户信息
     */
    @Override
    public void insert(Account account) {

    }

    /**
     * 批量保存账户信息
     *
     * @param entityList 账户信息集合
     * @param batchSize  批处理大小
     * @return boolean 是否保存成功
     */
    @Override
    public boolean saveBatch(Collection<Account> entityList, int batchSize) {
        return false;
    }

    /**
     * 批量保存或更新账户信息
     *
     * @param entityList 账户信息集合
     * @param batchSize  批处理大小
     * @return boolean 是否操作成功
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<Account> entityList, int batchSize) {
        return false;
    }

    /**
     * 根据ID更新账户信息
     *
     * @param account 账户信息
     * @return boolean 是否更新成功
     */
    @Override
    public boolean updateById(Account account) {

        return false;
    }

    /**
     * 根据ID批量更新账户信息
     *
     * @param entityList 账户信息集合
     * @param batchSize  批处理大小
     * @return boolean 是否更新成功
     */
    @Override
    public boolean updateBatchById(Collection<Account> entityList, int batchSize) {
        return false;
    }

    /**
     * 保存或更新账户信息
     *
     * @param entity 账户信息
     * @return boolean 是否操作成功
     */
    @Override
    public boolean saveOrUpdate(Account entity) {
        return false;
    }

    /**
     * 根据查询条件获取单个账户信息
     *
     * @param queryWrapper 查询条件包装器
     * @param throwEx      是否抛出异常
     * @return Account 账户信息
     */
    @Override
    public Account getOne(Wrapper<Account> queryWrapper, boolean throwEx) {
        return null;
    }

    /**
     * 根据查询条件获取Map格式结果
     *
     * @param queryWrapper 查询条件包装器
     * @return Map<String, Object> 结果映射
     */
    @Override
    public Map<String, Object> getMap(Wrapper<Account> queryWrapper) {
        return Map.of();
    }

    /**
     * 根据查询条件和映射函数获取对象
     *
     * @param queryWrapper 查询条件包装器
     * @param mapper       映射函数
     * @param <V>          返回值类型
     * @return V 映射后的结果
     */
    @Override
    public <V> V getObj(Wrapper<Account> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    /**
     * 获取基础映射器
     *
     * @return BaseMapper<Account> 基础映射器
     */
    @Override
    public BaseMapper<Account> getBaseMapper() {
        return null;
    }

    /**
     * 获取实体类类型
     *
     * @return Class<Account> 实体类类型
     */
    @Override
    public Class<Account> getEntityClass() {
        return null;
    }

    /**
     * 根据ID删除账户信息
     *
     * @param id 账户ID
     */
    @Override
    public void deleteById(String id) {

    }

    @Override
    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin) {
        return null;
    }

    /**
     * 管理员登录验证
     *
     * @param account 包含用户名和密码的账户信息
     * @return Account 登录成功的账户信息
     */
    @Override
    public Account login(Account account) {
        Admin admin = adminService.login(account);
        Account accounts = new Account();
        accounts.setId(Math.toIntExact(admin.getId()));
        accounts.setUsername(admin.getUsername());
        accounts.setPassword(admin.getPassword());
        accounts.setRole(admin.getRole());
        return accounts;
    }

//    /**
//     * 管理员注册
//     *
//     * @param user 用户信息
//     */
//    @Override
//    public void register(User user) {
//        AccountService.super.register(user);
//    }

    /**
     * 更新管理员密码
     *
     * @param account 包含新密码信息的账户对象
     */
    @Override
    public void updatePassword(Account account) {
        adminService.updatePassword(account);
    }
}
