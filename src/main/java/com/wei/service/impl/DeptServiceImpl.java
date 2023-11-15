package com.wei.service.impl;

import com.wei.mapper.DeptMapper;
import com.wei.mapper.EmpMapper;
import com.wei.pojo.Dept;
import com.wei.pojo.DeptLog;
import com.wei.service.DeptLogService;
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

    @Autowired
    private DeptLogService deptLogService;

    //查询所有部门数据
    @Override
    public List<Dept> list() {
        //调用Mapper接口，拿到数据库数据
        return deptMapper.list();
    }

    //根据id删除部门
    @Transactional(rollbackFor = Exception.class)  //spring事物管理，保持事物一致性(默认只捕获运行时异常)
    @Override
    public void delete(Integer id) throws Exception{
        try {
            deptMapper.deleteById(id);
            //模拟异常
            if (true) {
                throw new Exception("出现异常了~~~");
            }
            //同时删除部门下的所有员工
            empMapper.deleteByDeptId(id);
        } finally {
            //不论是否有异常，最终都要执行的代码：记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是" + id + "号部门");
            //调用其他业务类中的方法
            deptLogService.insert(deptLog);
        }
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
