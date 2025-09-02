package org.campuswall.springbootcampuslovewall.common.core.service.impl;

import com.github.pagehelper.PageInfo;
import org.campuswall.springbootcampuslovewall.common.core.service.BaseService;

import java.util.List;

/**
 * 自定义通用 Service 实现类，使用传统 MyBatis Mapper 实现。
 *
 * @param <T>  实体类
 * @param <ID> 主键类型
 * @param <M>  Mapper 类型
 */
public abstract class BaseServiceImpl<T, ID, M>  implements BaseService<T, ID> {

    protected final M mapper;

    /**
     * 构造函数，初始化Mapper对象
     *
     * @param mapper 数据访问层Mapper对象
     */
    public BaseServiceImpl(M mapper) {
        this.mapper = mapper;
    }

    /**
     * 根据主键ID查询实体对象
     *
     * @param id 主键ID（Long类型）
     * @return 实体对象
     */
    public abstract T selectById(ID id);


    public abstract void deleteById(Integer id);

    /**
     * 查询所有实体对象列表
     *
     * @return 实体对象列表
     */
    @Override
    public abstract List<T> selectAll();

    /**
     * 根据主键ID更新实体对象
     *
     * @param id 主键ID（Long类型）
     */
    public abstract void updateById(Long id);

    /**
     * 插入实体对象
     *
     * @param entity 实体对象
     */
    @Override
    public abstract void insert(T entity);

    @Override
    /**
     * 根据主键ID更新实体对象
     *
     * @param entity 实体对象
     * @return 是否更新成功
     */
    public abstract boolean updateById(T entity);

    @Override
    /**
     * 根据主键ID删除实体对象
     *
     * @param id 主键ID
     */
    public abstract void deleteById(ID id);

    /**
     * 分页查询管理员信息列表
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param entity    查询条件对象
     * @return 分页信息对象
     */
    public abstract PageInfo<T> selectPage(Integer pageNum, Integer pageSize, T entity);
}