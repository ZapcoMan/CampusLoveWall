package org.campuswall.springbootcampuslovewall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.common.core.service.impl.BaseServiceImpl;
import org.campuswall.springbootcampuslovewall.mapper.UserMapper;
import org.campuswall.springbootcampuslovewall.user.entity.User;
import org.campuswall.springbootcampuslovewall.user.service.UserService;
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

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /** ------- CRUD（与 BaseService 泛型一致：ID=Long） ------- */

    @Override
    public User selectById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException("功能未实现");
    }


    /** ！！！删除这个 Integer 版本，避免与 ID=Long 冲突
     *  public void deleteById(Integer id) { ... }
     */

    @Override
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public List<User> selectAll() {
        // 如果没有对应 mapper 方法，可用分页器/或直接返回空列表
        // 建议在 UserMapper 增加 selectAll()
        return userMapper.selectAll();
    }

    @Override
    public void updateById(Long id) {
        // 你的 BaseServiceImpl 要求有这个模板方法
        // 可以选择在这里不使用；建议在 BaseServiceImpl 里移除这个“按 ID 更新”的抽象；
        // 如果必须保留，就做一个简单适配：读取后再 updateById(User)
        User u = userMapper.selectById(id);
        if (u == null) return;
        userMapper.updateById(u);
    }

    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    /** 覆盖 IService 的 save，落库 */
    @Override
    public boolean save(User user) {
        return userMapper.save(user);
    }

    /** 领域态更新（比 Long id 的那个更常用） */
    @Override
    public boolean updateById(User user) {
        return userMapper.updateById(user) > 0;
    }

    /** 下列批量方法，如无需求/无 mapper，对外抛不支持更安全 */
    @Override
    public boolean saveBatch(Collection<User> entityList, int batchSize) {
        throw new UnsupportedOperationException("未实现批量保存");
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<User> entityList, int batchSize) {
        throw new UnsupportedOperationException("未实现批量保存或更新");
    }

    @Override
    public boolean updateBatchById(Collection<User> entityList, int batchSize) {
        throw new UnsupportedOperationException("未实现批量更新");
    }

    @Override
    public boolean saveOrUpdate(User entity) {
        throw new UnsupportedOperationException("未实现 saveOrUpdate");
    }

    /** MyBatis-Plus 相关（若未用到，可简单返回 null/Map.of/或抛不支持） */
    @Override public User getOne(Wrapper<User> queryWrapper, boolean throwEx) { return null; }
    @Override public Map<String, Object> getMap(Wrapper<User> queryWrapper) { return Map.of(); }
    @Override public <V> V getObj(Wrapper<User> queryWrapper, Function<? super Object, V> mapper) { return null; }

    @Override public BaseMapper<User> getBaseMapper() { return null; }
    @Override public Class<User> getEntityClass() { return User.class; }

    @Override
    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, User entity) {
        throw new UnsupportedOperationException("未实现分页，可使用 PageHelper 在 mapper 层实现");
    }




}
