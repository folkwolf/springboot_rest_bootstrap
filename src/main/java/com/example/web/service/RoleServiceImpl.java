package com.example.web.service;

import com.example.web.dao.RoleRepository;
import com.example.web.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleDAO;

    @Override
    public List<Role> getAllRoles() {
        return roleDAO.findAll();
    }

    @Override
    public Role findOne(long id) {
        return roleDAO.getById(id);
    }
}
