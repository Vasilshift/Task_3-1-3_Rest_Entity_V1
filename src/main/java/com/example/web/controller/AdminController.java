package com.example.web.controller;

import com.example.web.model.Role;
import com.example.web.model.User;
//import com.example.web.service.InitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
//    private final InitServiceImpl initServiceImpl;

    @Autowired
    public AdminController(UserService userService, RoleService roleService
                           ) {
        this.userService = userService;
        this.roleService = roleService;

    }

//    @PostConstruct
//    public void firstInitialization() {
//        initServiceImpl.init();
//    }

    @GetMapping()
    public String findAll(Model model){
        List<User> users = userService.allUsers();
        List<Role> roles = roleService.allRoles();
        model.addAttribute("users", users);
        model.addAttribute("allRoles", roles);
        return "user-list";
    }
//    @GetMapping("/admin")
//    public String admin() {
//        return "adminPage";
//    }
//
//    @GetMapping(value = "/user")
//    public String user() {
//        return "userPage";
//    }

}