package org.campuswall.springbootcampuslovewall;

import cn.hutool.crypto.digest.DigestUtil;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.admin.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminCreationTest {
    @Resource
    private AdminService adminService;

    @Test
    public void createTestUser() {
        // 创建测试用户
        Admin admin = new Admin();
        admin.setUsername("testUser");
        // 对密码进行MD5加密存储，与登录逻辑保持一致
        admin.setPassword(DigestUtil.md5Hex("password"));
        admin.setRole("ADMIN");
        admin.setStatus(1); // 启用状态

        // 保存用户到数据库
        adminService.save(admin);


    }
}
