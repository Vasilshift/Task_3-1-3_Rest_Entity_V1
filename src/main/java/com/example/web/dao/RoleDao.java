package com.example.web.dao;

import com.example.web.model.Role;
import java.util.List;

public interface RoleDao {
    Role getRoleByName(String name);

//    Role getDefaultRole();

    void add(Role role);

    List<Role> allRoles();

}