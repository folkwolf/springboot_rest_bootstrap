package com.example.web.service;

import com.example.web.model.User;

import java.util.List;

public interface UserService {
     User getUser(long id);

     List<User> getAllUsers();

     void saveUser(User user);

     void updateUser(User user);

     void deleteUser(long id);
}
