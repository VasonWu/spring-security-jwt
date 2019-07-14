package com.vason.security.controller;

import com.vason.security.Service.UserService;
import com.vason.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @RequestMapping("/user")
  public List<User> getUsers() {
    var dd = 1;
    return userService.getUsers();
  }
}
