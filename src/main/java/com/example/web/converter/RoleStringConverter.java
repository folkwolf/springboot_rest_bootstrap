package com.example.web.converter;

import com.example.web.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleStringConverter implements Converter<Role, String> {

    @Override
    public String convert(Role role) {
        return role.getId().toString();
    }

}
