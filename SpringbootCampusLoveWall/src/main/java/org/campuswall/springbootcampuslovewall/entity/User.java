package org.campuswall.springbootcampuslovewall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.campuswall.springbootcampuslovewall.common.core.model.BaseEntity;
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class User extends BaseEntity {
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private Integer gender; // 0未知 1男 2女
    private String email;
    private String phone;
    private String role; // admin / user
    private Integer status; // 1启用 0禁用
}
