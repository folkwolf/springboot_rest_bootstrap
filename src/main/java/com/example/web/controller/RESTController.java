package com.example.web.controller;

import com.example.web.model.Role;
import com.example.web.model.User;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/rest")
public class RESTController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/allRoles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/add")
    public void addNewUser(@RequestBody User newUser) {
        if(Objects.nonNull(newUser) && newUser.getAge() != 0 && newUser.getName() != null && newUser.getSurname() != null) {
            userService.saveUser(newUser);
        }
    }

    @PatchMapping("/{id}")
    public void editUser(@PathVariable("id") int id, @RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/me")
    public User getMyInfo(Authentication authentication) {
        return (User)authentication.getPrincipal();
    }
}
