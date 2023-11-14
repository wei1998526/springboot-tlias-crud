package com.wei.controller;

import com.wei.pojo.Dept;
import com.wei.pojo.Result;
import com.wei.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//部门管理控制器
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    //注入部门业务接口
    @Autowired
    private DeptService deptService;

    //查询所有部门数据
    @GetMapping
    public Result list() {
        log.info("查询所有部门");
        //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        //响应结果
        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门：{}", id);
        deptService.delete(id);
        return Result.success();
    }

    //添加部门
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    //查询部门
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id) {
        log.info("根据id查询部门：{}", id);
        Dept dept = deptService.select(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
