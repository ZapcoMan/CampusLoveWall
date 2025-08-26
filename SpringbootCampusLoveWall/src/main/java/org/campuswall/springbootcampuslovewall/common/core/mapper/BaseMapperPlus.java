package org.campuswall.springbootcampuslovewall.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 自定义 BaseMapper 接口
 * @param <T> 实体类
 * @param <ID> 主键类型
 */
public interface BaseMapperPlus<T, ID> extends BaseMapper<T> {

    T selectById(ID id);

    List<T> selectAll();

    int insert(T entity);

    int updateById(T entity);

    int deleteById(T id);
}

