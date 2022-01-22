package com.example.web.service;

import com.example.web.dao.UserRepository;
import com.example.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUser(long id) {
        return userDAO.getById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public void updateUser(User user) {
        if(user.getPassword()!=null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(getUser(user.getId()).getPassword());
        }
        userDAO.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userDAO.deleteById(id);
    }
}
