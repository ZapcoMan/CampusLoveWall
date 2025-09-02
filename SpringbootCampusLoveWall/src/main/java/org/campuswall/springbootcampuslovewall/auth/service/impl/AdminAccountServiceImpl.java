package org.campuswall.springbootcampuslovewall.auth.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.campuswall.springbootcampuslovewall.admin.entity.Admin;
import org.campuswall.springbootcampuslovewall.common.core.service.impl.BaseServiceImpl;
import org.campuswall.springbootcampuslovewall.common.enums.RoleEnum;
import org.campuswall.springbootcampuslovewall.entity.Account;
import org.campuswall.springbootcampuslovewall.mapper.AdminMapper;
import org.campuswall.springbootcampuslovewall.auth.service.AccountService;
import org.campuswall.springbootcampuslovewall.admin.service.AdminService;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 管理员账户服务实现类
 * 实现了AccountService接口，用于处理管理员账户相关的业务逻辑
 */
@Service
public class AdminAccountServiceImpl extends BaseServiceImpl<Admin, Integer, AdminMapper> implements AccountService<Admin> {


    @Resource
    private AdminMapper adminMapper;


}
