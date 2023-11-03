package com.wei.controller;

import com.wei.pojo.Emp;
import com.wei.pojo.Result;
import com.wei.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("用户登录，用户名：{}，密码：{}", emp.getUsername(), emp.getPassword());
        Emp e = empService.login(emp);
        return e != null ? Result.success() : Result.error("用户名或密码错误！");
    }
}
