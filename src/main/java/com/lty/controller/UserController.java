package com.lty.controller;

import com.lty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/getAllUserTest")
    public List<Map<String, Object>> t() throws Exception{
        return userService.getAllUser();
    }
    @RequestMapping(value = "/getall")
    public List<Map<String, Object>> getall() throws Exception{
        return userService.getall();
    }
}
