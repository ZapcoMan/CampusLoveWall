package org.campuswall.springbootcampuslovewall.common.core.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 自定义基础Service接口，适用于所有实体。
 *
 * @param <T>  实体类
 * @param <ID> 主键类型
 */
public interface BaseService<T, ID> extends IService<T> {

    T selectById(ID id);

    List<T> selectAll();

    void insert(T entity);

    boolean save(T entity);

    boolean updateById(T entity);

    void deleteById(ID id);
}