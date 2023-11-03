package com.wei.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wei.mapper.EmpMapper;
import com.wei.pojo.Emp;
import com.wei.pojo.PageBean;
import com.wei.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//员工业务实现类
@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin,LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page,pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        //强转为Page类型
        //PageHelper中，查询结果通常被封装在一个Page对象中
        Page<Emp> p = (Page<Emp>) empList;

        //3.封装PageBen对象
        //p.getTotal()返回总记录数，p.getResult()返回当前页的记录列表。
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    //批量删除
    @Override
    public void deletes(List<Integer> ids) {
        empMapper.deletes(ids);
    }

    //添加员工
    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    //根据id查询员工
    @Override
    public Emp select(Integer id) {
        return empMapper.select(id);
    }

    //修改员工
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    //登录
    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUserNameAndPassword(emp);
    }
}
