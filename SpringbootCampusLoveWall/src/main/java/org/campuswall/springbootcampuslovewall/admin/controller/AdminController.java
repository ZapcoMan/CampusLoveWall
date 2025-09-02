package org.campuswall.springbootcampuslovewall.admin.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.common.core.controller.BaseController;
import org.campuswall.springbootcampuslovewall.common.result.R;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 管理员控制器类
 * 处理管理员相关的HTTP请求
 */
@Slf4j
@RestController
@RequestMapping("/api/admins")
public class AdminController extends BaseController<Admin, Long> {

    @Resource
    private AdminService adminService;

    /**
     * 构造函数，注入基础服务
     *
     * @param adminService 管理员服务实例
     */
    @Autowired
    public AdminController(AdminService adminService) {
        super(adminService);
        this.adminService = adminService;
    }

//    /**
//     * 获取管理员列表
//     *
//     * @return 管理员列表的响应结果
//     */
//    @GetMapping
//    public R<List<Admin>> list() {
//        return R.success(adminService.list());
//    }
//
//    /**
//     * 根据ID获取管理员信息
//     *
//     * @param id 管理员ID
//     * @return 管理员信息的响应结果
//     */
//    @GetMapping("/{id}")
//    public R<Admin> getById(@PathVariable Long id) {
//        return R.success(adminService.getById(id));
//    }
//
////    /**
////     * 添加新管理员
////     *
////     * @param admin 管理员实体对象
////     * @return 操作结果的响应
////     */
////    @PostMapping
////    public R<Void> add(@RequestBody Admin admin) {
////        adminService.save(admin);
////        return R.ok();
////    }
//
//    /**
//     * 更新管理员信息
//     *
//     * @param id    管理员ID
//     * @param admin 管理员实体对象
//     * @return 操作结果的响应
//     */
//    @PutMapping("/{id}")
//    public R<Void> update(@PathVariable Long id, @RequestBody Admin admin) {
//        admin.setId(id);
//        adminService.updateById(admin);
//        return  R.ok();
//    }
//
//    /**
//     * 删除管理员
//     *
//     * @param id 管理员ID
//     * @return 操作结果的响应
//     */
//    @DeleteMapping("/{id}")
//    public R<Void> delete(@PathVariable Long id) {
//        adminService.removeById(id);
//        return  R.ok();
//    }
}
