package com.jxufe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello.do")
    public Map sayHello(String username, String pwd) {

        System.out.println(username);
        System.out.println(pwd);

        Map<String, Object> map = new HashMap<>();
        map.put("success", "访问成功");

        return map;
    }

}
