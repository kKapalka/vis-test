package com.vis.test.controller;

import com.vis.test.dto.RoleDto;
import com.vis.test.model.Role;
import com.vis.test.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@PreAuthorize("authentication.name == 'admin'")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @PutMapping("roles/{id}")
    public Role modifyRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return roleService.modifyRole(id, roleDto);
    }

    @PostMapping("roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRole(@RequestBody RoleDto roleDto) {
        return roleService.saveRole(roleDto);
    }

    @DeleteMapping("roles/{id}")
    public void deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}
