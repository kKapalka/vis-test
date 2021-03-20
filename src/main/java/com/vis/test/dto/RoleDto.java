package com.vis.test.dto;

import java.util.List;

public class RoleDto {
    private String name;
    private List<String> permissions;

    public RoleDto() {
    }

    public RoleDto(String name, List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "name='" + name + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
