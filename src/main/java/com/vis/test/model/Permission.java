package com.vis.test.model;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    LIST_USERS,
    CREATE_USERS,
    DELETE_USERS,
    EDIT_USERS;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
