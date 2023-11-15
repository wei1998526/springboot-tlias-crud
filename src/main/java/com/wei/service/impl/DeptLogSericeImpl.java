package com.wei.service.impl;

import com.wei.mapper.DeptLogMapper;
import com.wei.pojo.DeptLog;
import com.wei.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogSericeImpl implements DeptLogService {
    @Autowired
    private DeptLogMapper deptLogMapper;
    @Transactional(propagation = Propagation.REQUIRES_NEW)  //事物传播行为，每次都新建事物
    @Override
    public void insert(DeptLog deptLog) {
        //记录事物操作日志
        deptLogMapper.insert(deptLog);
    }
}
