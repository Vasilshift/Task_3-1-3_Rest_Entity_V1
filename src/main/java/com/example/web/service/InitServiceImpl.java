package com.example.web.service;

import com.example.web.model.Role;
import com.example.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitServiceImpl implements InitService {

    private final UserService userService;
    private final RoleService roleService;
    private BCryptPasswordEncoder bcryptpasswordEncoder;

    @Autowired
    public InitServiceImpl(UserService userService, RoleService roleService, BCryptPasswordEncoder bcryptpasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bcryptpasswordEncoder = bcryptpasswordEncoder;
    }

    @Override
    public void initServiceImpl() {

		Role roleAdmin = new Role();
		roleAdmin.setId(1);
		roleAdmin.setName("ROLE_ADMIN");
		roleService.add(roleAdmin);

		Role roleUser = new Role();
		roleUser.setId(2);
		roleUser.setName("ROLE_USER");
		roleService.add(roleUser);

		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(bcryptpasswordEncoder.encode("admin"));
		user1.setLastname("adminov");
		user1.setAge(34);
		user1.setEmail("admin@mail.ru");
		user1.setRoles(Collections.singleton(roleService.getRoleByName("ROLE_ADMIN")));
		userService.add(user1);

		User user2 = new User();
		user2.setUsername("user");
		user2.setPassword(bcryptpasswordEncoder.encode("user"));
		user2.setLastname("userov");
		user2.setAge(34);
		user2.setEmail("user@mail.ru");
		user2.setRoles(Collections.singleton(roleService.getRoleByName("ROLE_USER")));
		userService.add(user2);

    }
}