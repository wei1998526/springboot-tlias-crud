package com.wei.controller;

import com.wei.pojo.Emp;
import com.wei.pojo.Result;
import com.wei.service.EmpService;
import com.wei.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
        //登录成功，下发jwt令牌
        if (e != null) {
            Map<String, Object> Claims = new HashMap<>();
            Claims.put("id", e.getId());
            Claims.put("name", e.getName());
            Claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(Claims);
            return Result.success(jwt);
        }
        //登录失败，返回错误信息
        return Result.error("用户名或密码错误！");
    }
}
