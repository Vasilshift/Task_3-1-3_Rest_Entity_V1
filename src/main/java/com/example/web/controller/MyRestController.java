package com.example.web.controller;

import com.example.web.exceptionHandling.NoSuchUserException;
import com.example.web.model.User;
import com.example.web.service.InitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.web.service.RoleService;
import com.example.web.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final InitServiceImpl initServiceImpl;

    @Autowired
    public MyRestController(UserService userService, RoleService roleService,
                            InitServiceImpl initServiceImpl) {
        this.userService = userService;
        this.roleService = roleService;
        this.initServiceImpl = initServiceImpl;
    }

    @PostConstruct
    public void firstInitialization() {
        initServiceImpl.initServiceImpl();
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers(Model model) {
        return new ResponseEntity<>(userService.allUsers(),HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> get(@PathVariable int id) {
        User user = userService.get(id);
        if(user == null){
            throw new NoSuchUserException("There is no user with id "+ id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public User add(@RequestBody User user){
        roleService.setupRoles(user);
        userService.add(user);
        return user;
    }

    @PutMapping("/users")
    public User update(@RequestBody User user){
        roleService.setupRoles(user);
        userService.update(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable("id") int id) {
        //User user = userService.get(id);
        userService.delete(id);
    }
}