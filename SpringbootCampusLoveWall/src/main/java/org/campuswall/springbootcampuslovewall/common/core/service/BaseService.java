package org.campuswall.springbootcampuslovewall.common.core.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 抽象服务接口，定义通用数据操作。
 *
 * @param <T>  实体类
 * @param <ID> 主键类型
 */
public interface BaseService<T, ID> extends IService<T> {
    boolean save(T entity);
    void insert(T entity);

    void deleteById(ID id);
    boolean updateById(T entity);
    T selectById(ID id);

    List<T> selectAll();




}