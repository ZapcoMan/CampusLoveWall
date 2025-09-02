package org.campuswall.springbootcampuslovewall;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testUserLoginSuccess() throws Exception {
        // 准备测试数据
        Account account = new Account();
        account.setUsername("testUser");
        account.setPassword("password");
        account.setRole("USER");

        // 执行登录请求
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(20000));
    }

    @Test
    void testAdminLoginSuccess() throws Exception {
        // 准备测试数据
        Account account = new Account();
        account.setUsername("testAdmin");
        account.setPassword("password");
        account.setRole("ADMIN");

        // 执行登录请求
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(20000));
    }

    @Test
    void testLoginWithUnsupportedRole() throws Exception {
        // 准备测试数据
        Account account = new Account();
        account.setUsername("testUser");
        account.setPassword("password");
        account.setRole("GUEST");

        // 执行登录请求
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(500))
                .andExpect(jsonPath("$.message").value("不支持的角色类型"));
    }



}
