package com.example.web.converter;

import com.example.web.model.Role;
import com.example.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringRoleConverter implements Converter<String, Role> {

    @Autowired
    private RoleService roleService;

    @Override
    public Role convert(String source) {
        return roleService.findOne(Long.parseLong(source));
    }

}
