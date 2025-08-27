package org.campuswall.springbootcampuslovewall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.campuswall.springbootcampuslovewall.common.core.model.BaseEntity;

/**
 * 管理员信息
 * 继承自Account类，包含管理员的基本信息和认证相关信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_admin")
public class Admin extends BaseEntity {

    private String username;
    private String password;
    private String name;
    private String role; // admin / super_admin
    private Integer status; // 1启用 0禁用
}
