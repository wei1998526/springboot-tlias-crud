package com.wei.service;

import com.wei.pojo.Emp;
import com.wei.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//员工业务规则
public interface EmpService {
    /**
     * 分页查询
     *
     * @param page     页码
     * @param pageSize 每页展示的记录数
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除
    void deletes(List<Integer> ids);

    //添加员工
    void insert(Emp emp);

    //修改员工
    void update(Emp emp);

    //根据id查询员工
    Emp select(Integer id);

    //登录
    Emp login(Emp emp);
}
