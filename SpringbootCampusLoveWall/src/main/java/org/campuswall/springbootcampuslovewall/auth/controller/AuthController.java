package org.campuswall.springbootcampuslovewall.auth.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.auth.service.AccountService;
import org.campuswall.springbootcampuslovewall.auth.strategy.AccountConvertStrategy;
import org.campuswall.springbootcampuslovewall.common.exception.CustomerException;
import org.campuswall.springbootcampuslovewall.common.result.R;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private List<AccountService> accountServices;

    private final Map<String, AccountService> serviceMap = new HashMap<>();
    @Resource
    private Map<String, AccountConvertStrategy> strategyMap; // Spring 会自动注入
    @PostConstruct
    public void init() {
        for (AccountService<?, ?> service : accountServices) {
            if (service.getRole() != null) {
                serviceMap.put(service.getRole().getCode(), service);
                log.info("初始化角色：{}", service.getRole().getCode());
            } else {
                log.warn("发现AccountService实现类返回null角色: {}", service.getClass().getName());
            }
        }
    }

    /**
     * 用户登录接口
     * 根据用户角色使用相应的账户服务进行登录验证，并返回登录结果
     *
     * @param account 包含用户名、密码和角色信息的账户对象
     * @return R<Account> 统一响应结果，包含登录成功的账户信息或错误信息
     */
    @PostMapping("/login")
    public R<Account> login(@RequestBody Account account) {
        try {
            if (account == null || account.getRole() == null) {
                return R.error("参数错误");
            }

            // 获取策略（DTO <-> Domain 的转换器）
            AccountConvertStrategy<?> strategy = strategyMap.get(account.getRole());
            if (strategy == null) {
                return R.error("未找到转换策略");
            }

            // 获取服务
            AccountService<?, Integer> service = serviceMap.get(account.getRole());
            if (service == null) {
                return R.error("不支持的角色类型");
            }

            // 1. Account DTO 转领域对象
            Object domain = strategy.convertToDomain(account);
            if (domain == null) {
                return R.error("转换领域对象失败");
            }

            // 2. 领域对象登录
            Object loginDomain = ((AccountService<Object, Integer>) service).login(domain);
            if (loginDomain == null) {
                return R.error("登录失败");
            }

            // 3. 领域对象 -> Account DTO
            Account loginAccount = ((AccountConvertStrategy<Object>) strategy).convertToAccount(loginDomain);

            return R.success(loginAccount);

        } catch (CustomerException e) {
            return R.error(e.getMessage());
        } catch (Exception e) {
            log.error("登录异常", e);
            return R.error("系统异常");
        }
    }

}
