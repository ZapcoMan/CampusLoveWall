package org.campuswall.springbootcampuslovewall;

import cn.hutool.crypto.digest.DigestUtil;
import org.campuswall.springbootcampuslovewall.user.entity.User;
import org.campuswall.springbootcampuslovewall.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.Resource;

@SpringBootTest
public class UserCreationTest {

    @Resource
    private UserService userService;

    @Test
    public void createTestUser() {
        // 创建测试用户
        User user = new User();
        user.setUsername("testUser");
        // 对密码进行MD5加密存储，与登录逻辑保持一致
        user.setPassword(DigestUtil.md5Hex("password"));
        user.setNickname("测试用户");
        user.setRole("USER");
        user.setStatus(1); // 启用状态

        // 保存用户到数据库
        userService.save(user);


    }
}
