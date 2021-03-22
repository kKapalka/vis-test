package com.vis.test.controller;

import com.vis.test.dto.UserDto;
import com.vis.test.model.User;
import com.vis.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    @PreAuthorize("hasAuthority('LIST_USERS') or authentication.name == 'admin'")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("users/{id}")
    @PreAuthorize("hasAuthority('EDIT_USERS') or authentication.name == 'admin'")
    public User modifyUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.modifyUser(id, userDto);
    }

    @PostMapping("users")
    @PreAuthorize("hasAuthority('CREATE_USERS') or authentication.name == 'admin'")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @DeleteMapping("users/{id}")
    @PreAuthorize("hasAuthority('DELETE_USERS') or authentication.name == 'admin'")
    public void deleteUserById(@PathVariable Long id) {
            userService.deleteUserById(id);
    }
}
