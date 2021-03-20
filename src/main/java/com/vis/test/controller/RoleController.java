package com.vis.test.controller;

import com.vis.test.dto.RoleDto;
import com.vis.test.dto.TokenDto;
import com.vis.test.dto.UserDto;
import com.vis.test.model.Role;
import com.vis.test.model.User;
import com.vis.test.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("roles")
    @PreAuthorize("hasAuthority('LIST_USERS')")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PutMapping("roles/{id}")
    @PreAuthorize("hasAuthority('EDIT_USERS')")
    public Role modifyRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return roleService.modifyRole(id, roleDto);
    }

    @PostMapping("roles")
    @PreAuthorize("hasAuthority('CREATE_USERS')")
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    @DeleteMapping("roles/{id}")
    @PreAuthorize("hasAuthority('DELETE_USERS')")
    public void deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}
