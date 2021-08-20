package com.example.web.controller;

import com.example.web.service.InitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.PostConstruct;

@Controller
@RequestMapping("")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final InitServiceImpl initServiceImpl;

    @Autowired
    public AdminController(UserService userService, RoleService roleService,
                           InitServiceImpl initServiceImpl) {
        this.userService = userService;
        this.roleService = roleService;
        this.initServiceImpl = initServiceImpl;
    }

    @PostConstruct
    public void firstInitialization() {
        initServiceImpl.initServiceImpl();
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "userPage";
    }
}