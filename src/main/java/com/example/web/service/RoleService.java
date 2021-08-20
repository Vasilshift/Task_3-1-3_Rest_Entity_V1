package com.example.web.service;

import com.example.web.model.Role;
import com.example.web.model.User;
import java.util.List;


public interface RoleService {

    Role getRoleByName(String name);

    void add(Role role);

    List<Role> allRoles();

    void setupRoles(User user);
}