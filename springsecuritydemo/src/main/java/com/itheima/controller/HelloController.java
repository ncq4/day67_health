package com.itheima.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    //@PreAuthorize("hasAuthority('add')")定义了只有拥有角色ROLE_ADMIN的用户
    // 才能访问add()方法
    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('add')")
    public String add(){
        System.out.println("add...");
        return "success";
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasRole('ROLE_AMIN')")
    public String delete(){
        System.out.println("delete...");
        return "suceess";
    }
}
