package org.example.springbootcampuslovewall.common.core.controller;



import io.swagger.v3.oas.annotations.Operation;
import org.example.springbootcampuslovewall.common.core.service.BaseService;
import org.example.springbootcampuslovewall.common.result.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础控制器类
 * 提供通用的RESTful CRUD操作，所有具体的控制器可以继承此类以获得基本的增删改查功能
 * @param <T> 实体类型
 * @param <ID> 主键类型
 */
public abstract class BaseController<T, ID> {

    protected final BaseService<T, ID> baseService;

    /**
     * 构造函数，注入基础服务
     * @param baseService 基础服务实例
     */
    protected BaseController(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    /**
     * 根据ID查询实体
     * @param id 实体ID
     * @return 包含查询结果的响应对象
     */
    @Operation(summary = "根据ID查询")
    @GetMapping("/{id}")
    public R<T> getById(@PathVariable ID id) {
        return R.success(baseService.selectById(id));
    }

    /**
     * 查询所有实体
     * @return 包含所有实体列表的响应对象
     */
    @Operation(summary = "查询全部")
    @GetMapping
    public R<List<T>> getAll() {
        return R.success(baseService.selectAll());
    }

    /**
     * 创建新实体
     * @param entity 要创建的实体对象
     * @return 操作结果响应对象
     */
    @Operation(summary = "新增")
    @PostMapping
    public R<Void> create(@RequestBody T entity) {
        baseService.insert(entity);
        return R.ok();
    }

    /**
     * 更新实体
     * @param entity 要更新的实体对象
     * @return 操作结果响应对象
     */
    @Operation(summary = "更新")
    @PutMapping
    public R<Void> update(@RequestBody T entity) {
        baseService.updateById(entity);
        return R.ok();
    }

    /**
     * 根据ID删除实体
     * @param id 要删除的实体ID
     * @return 操作结果响应对象
     */
    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable ID id) {
        baseService.deleteById(id);
        return R.ok();
    }
}
