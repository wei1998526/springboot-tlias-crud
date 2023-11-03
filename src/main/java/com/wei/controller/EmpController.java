package com.wei.controller;

import com.wei.pojo.Emp;
import com.wei.pojo.PageBean;
import com.wei.pojo.Result;
import com.wei.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//员工管理控制器
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    //注入员工业务接口
    @Autowired
    private EmpService empService;

    /**
     * 条件分页查询
     *
     * @param page     页码
     * @param pageSize 每页展示的信息
     * @param name     名字
     * @param gender   性别
     * @param begin    开始日期
     * @param end      结束日期
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    //批量删除
    @DeleteMapping("/{ids}")
    public Result deletes(@PathVariable List<Integer> ids) {
        log.info("批量删除，参数{}", ids);
        empService.deletes(ids);
        return Result.success();
    }

    //添加员工
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        log.info("添加员工{}", emp);
        empService.insert(emp);
        return Result.success();
    }

    //根据ID查询员工
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id) {
        log.info("根据id查询员工：{}", id);
        Emp emp = empService.select(id);
        return Result.success(emp);
    }

    //修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
