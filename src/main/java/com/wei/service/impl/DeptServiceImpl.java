package com.wei.service.impl;

import com.wei.mapper.DeptMapper;
import com.wei.mapper.EmpMapper;
import com.wei.pojo.Dept;
import com.wei.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

//部门业务实现类
@Service
@Slf4j
public class DeptServiceImpl implements DeptService {
    //注入Mapper接口，查询数据
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    //查询所有部门数据
    @Override
    public List<Dept> list() {
        //调用Mapper接口，拿到数据库数据
        return deptMapper.list();
    }

    //根据id删除部门
    @Transactional  //spring事物管理，保持事物一致性
    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
        //同时删除部门下的所有员工
        empMapper.deleteByDeptId(id);
    }

    //添加部门
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    //根据id查询部门
    @Override
    public Dept select(Integer id) {
        return deptMapper.select(id);


    }

    //根据id修改部门
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
