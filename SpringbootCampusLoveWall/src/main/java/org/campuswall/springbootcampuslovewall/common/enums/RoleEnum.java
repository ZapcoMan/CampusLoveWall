package org.campuswall.springbootcampuslovewall.common.enums;

import lombok.Getter;

// ... existing code ...
/**
 * 角色枚举类
 * 定义系统中的角色类型，包括角色代码和标签
 */
@Getter
public enum RoleEnum {
    ADMIN("ADMIN", "管理员");

    private final String code;
    private final String label;

    RoleEnum(String code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * 根据角色代码获取对应的角色枚举值
     * @param code 角色代码
     * @return 对应的角色枚举值
     * @throws IllegalArgumentException 当提供的角色代码无效时抛出此异常
     */
    public static RoleEnum fromCode(String code) {
        for (RoleEnum value : RoleEnum.values()) {
            if (value.getCode().equalsIgnoreCase(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("无效角色标识: " + code);
    }
}
