package org.campuswall.springbootcampuslovewall.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.common.core.controller.BaseController;
import org.campuswall.springbootcampuslovewall.common.core.service.BaseService;
import org.campuswall.springbootcampuslovewall.common.result.R;
import org.campuswall.springbootcampuslovewall.entity.User;
import org.campuswall.springbootcampuslovewall.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public R<List<User>> list() {
        return R.success(userService.list());
    }

    @GetMapping("/{id}")
    public R<User> getById(@PathVariable Long id) {
        return R.success(userService.getById(id));
    }

    @PostMapping
    public R<Void> add(@RequestBody User user) {
        userService.save(user);
        return R.ok();
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateById(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        userService.removeById(id);
        return R.ok();
    }
}
