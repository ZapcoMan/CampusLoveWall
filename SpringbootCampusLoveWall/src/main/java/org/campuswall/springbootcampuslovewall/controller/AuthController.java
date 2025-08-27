package org.campuswall.springbootcampuslovewall.controller;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.campuswall.springbootcampuslovewall.common.exception.CustomerException;
import org.campuswall.springbootcampuslovewall.common.result.R;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostConstruct
    public void init() {
        for (AccountService service : accountServices) {
            serviceMap.put(service.getRole().getCode(), service);
        }
    }

    @PostMapping("/login")
    public R<Account> login(@RequestBody Account account) {
        try {
            AccountService service = serviceMap.get(account.getRole());
            if (service == null) {
                return R.error("不支持的角色类型");
            }
            Account loginAccount = service.login(account);
            return R.success(loginAccount);
        } catch (CustomerException e) {
            return R.error(e.getMessage());
        }
    }
}
