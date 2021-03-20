package com.vis.test.dto;

public class UserDto extends LoginDto{
    private Long roleId;

    public UserDto() {
        super();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
