package com.vis.test.service;

import com.vis.test.dto.RoleDto;
import com.vis.test.model.Permission;
import com.vis.test.model.Role;
import com.vis.test.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Role modifyRole(Long id, RoleDto roleDto) {
        Role role = roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return persistAndReturnRole(roleDto, role);
    }

    public Role saveRole(RoleDto roleDto) {
        if(roleRepository.existsByName(roleDto.getName())) {
            throw new EntityExistsException("Role with such name already exists");
        }
        return persistAndReturnRole(roleDto, new Role());
    }

    private Role persistAndReturnRole(RoleDto roleDto, Role persistedRole) {
        persistedRole.setName(roleDto.getName());
        persistedRole.setPermissions(roleDto.getPermissions().stream().map(Permission::valueOf).collect(Collectors.toList()));
        return roleRepository.save(persistedRole);
    }
    public void deleteRoleById(Long id) {
        if(!roleRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        roleRepository.deleteById(id);
    }

}
