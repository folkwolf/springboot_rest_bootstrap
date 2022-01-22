package com.example.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);
//        RoleService roleService = context.getBean(RoleService.class);
//        UserService userService = context.getBean(UserServiceImpl.class);
//        User user1 = new User("Эрнест", "Ибрагимов", 23, "admin", "admin");
//        User user2 = new User("Максим", "Максимов", 25, "user", "user");
//        user1.addRole(roleService.getAllRoles().get(0));
//        user2.addRole(roleService.getAllRoles().get(1));
//        userService.saveUser(user1);
//        userService.saveUser(user2);
    }

}
