package org.campuswall.springbootcampuslovewall.auth.service.impl;


import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.auth.service.AccountService;
import org.campuswall.springbootcampuslovewall.common.core.service.impl.BaseServiceImpl;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.common.exception.CustomerException;
import org.campuswall.springbootcampuslovewall.mapper.AdminMapper;
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
public class AdminAccountServiceImpl extends BaseServiceImpl<Admin, Integer, AdminMapper> implements AccountService<Admin,Integer> {


    @Resource
    private AdminMapper adminMapper;

    /**
     * 构造函数，初始化Mapper对象
     *
     * @param adminMapper 数据访问层Mapper对象
     */
    public AdminAccountServiceImpl(AdminMapper adminMapper) {
        super(adminMapper);
    }

    /**
     * 获取账户角色枚举
     *
     * @return RoleEnum 角色枚举值
     */
    @Override
    public RoleEnum getRole() {
        return RoleEnum.ADMIN;
    }

    /**
     * 根据主键ID查询实体对象
     *
     * @param integer 主键ID
     * @return 实体对象
     */
    @Override
    public Admin selectById(Integer integer) {
        return null;
    }

    /**
     * 账户登录
     *
     * @param admin 账户信息
     * @return T 登录成功的账户对象
     */
    @Override
    public Admin login(Admin admin) {
        if (admin == null) throw new CustomerException("参数为空");
        // 1) 查管理员
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (dbAdmin == null) {
            throw new CustomerException("账号不存在");
        }

        // 2) 密码校验（用 md5）
        String inputHash = DigestUtil.md5Hex(admin.getPassword() == null ? "" : admin.getPassword());
        if (!inputHash.equals(dbAdmin.getPassword())) {
            throw new CustomerException("账号或密码错误");
        }

        // 3) 可插入额外校验：是否启用、是否需要二步验证等
        // if (!"ACTIVE".equals(dbAdmin.getStatus())) throw new CustomerException("管理员被锁定");

        return dbAdmin;
    }

    /**
     * 更新账户密码
     *
     * @param admin 包含密码信息的账户对象
     */
    @Override
    public void updatePassword(Admin admin) {
        if (admin == null || admin.getId() == null) throw new CustomerException("参数错误");
        String hash = DigestUtil.md5Hex(admin.getPassword());
        adminMapper.updatePasswordById(admin.getId(), hash);
    }

    /**
     * 注册新账户
     *
     * @param admin 用户对象
     */
    @Override
    public void register(Admin admin) {
        // 管理员注册通常受限 -> 默认不允许
        throw new UnsupportedOperationException("管理员注册不支持");
    }

    /**
     * 重置账户密码
     *
     * @param email 用户邮箱
     */
    @Override
    public void resetPassword(String email) {
        throw new UnsupportedOperationException("功能未实现");
    }

    /**
     * 锁定账户
     *
     * @param integer 账户ID
     */
    @Override
    public void lockAccount(Integer integer) {
        throw new UnsupportedOperationException("功能未实现");
    }

    /**
     * 解锁账户
     *
     * @param integer 账户ID
     */
    @Override
    public void unlockAccount(Integer integer) {
        throw new UnsupportedOperationException("功能未实现");
    }

    /**
     * 查询所有实体对象列表
     *
     * @return 实体对象列表
     */
    @Override
    public List<Admin> selectAll() {
        return List.of();
    }

    /**
     * 根据主键ID更新实体对象
     *
     * @param id 主键ID（Long类型）
     */
    @Override
    public void updateById(Long id) {
        throw new UnsupportedOperationException("功能未实现");
    }

    /**
     * @param admin
     * @return
     */
    @Override
    public boolean save(Admin admin) {
        return false;
    }

    /**
     * @param adminList
     * @param batchSize
     * @return
     */
    @Override
    public boolean saveBatch(Collection<Admin> adminList, int batchSize) {
        return false;
    }

    /**
     * @param adminList
     * @param batchSize
     * @return
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<Admin> adminList, int batchSize) {
        return false;
    }

    /**
     * @param admin
     */
    @Override
    public void insert(Admin admin) {
        throw new UnsupportedOperationException("功能未实现");
    }

    /**
     * @param admin
     * @return
     */
    @Override
    public boolean updateById(Admin admin) {
        return false;
    }

    /**
     * @param adminList
     * @param batchSize
     * @return
     */
    @Override
    public boolean updateBatchById(Collection<Admin> adminList, int batchSize) {
        return false;
    }

    /**
     * @param admin
     * @return
     */
    @Override
    public boolean saveOrUpdate(Admin admin) {
        return false;
    }

    /**
     * @param queryWrapper
     * @param throwEx
     * @return
     */
    @Override
    public Admin getOne(Wrapper<Admin> queryWrapper, boolean throwEx) {
        return null;
    }

    /**
     * @param queryWrapper
     * @return
     */
    @Override
    public Map<String, Object> getMap(Wrapper<Admin> queryWrapper) {
        return Map.of();
    }

    /**
     * @param queryWrapper
     * @param mapper
     * @param <V>
     * @return
     */
    @Override
    public <V> V getObj(Wrapper<Admin> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public BaseMapper<Admin> getBaseMapper() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public Class<Admin> getEntityClass() {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("功能未实现");
    }

    /**
     * 分页查询管理员信息列表
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param admin   查询条件对象
     * @return 分页信息对象
     */
    @Override
    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize, Admin admin) {
        return null;
    }


}
