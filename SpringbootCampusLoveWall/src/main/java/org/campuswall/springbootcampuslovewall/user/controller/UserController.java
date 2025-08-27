package org.campuswall.springbootcampuslovewall.user.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.common.core.controller.BaseController;
import org.campuswall.springbootcampuslovewall.common.core.service.BaseService;
import org.campuswall.springbootcampuslovewall.common.result.R;
import org.campuswall.springbootcampuslovewall.user.entity.User;
import org.campuswall.springbootcampuslovewall.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器类
 * 处理用户相关的HTTP请求
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<User,Long> {
    @Resource
    private UserService userService;

    /**
     * 构造函数，注入基础服务
     *
     * @param baseService 基础服务实例
     */
    protected UserController(BaseService<User, Long> baseService) {
        super(baseService);
    }

    /**
     * 获取用户列表
     *
     * @return 用户列表的响应结果
     */
    @GetMapping
    public R<List<User>> list() {
        return R.success(userService.list());
    }

    /**
     * 根据ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息的响应结果
     */
    @GetMapping("/{id}")
    public R<User> getById(@PathVariable Long id) {
        return R.success(userService.getById(id));
    }

    /**
     * 添加新用户
     *
     * @param user 用户实体对象
     * @return 操作结果的响应
     */
    @PostMapping
    public R<Void> add(@RequestBody User user) {
        userService.save(user);
        return R.ok();
    }

    /**
     * 更新用户信息
     *
     * @param id   用户ID
     * @param user 用户实体对象
     * @return 操作结果的响应
     */
    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return R.ok();
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 操作结果的响应
     */
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        userService.removeById(id);
        return R.ok();
    }
}
