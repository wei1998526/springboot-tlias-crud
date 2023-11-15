package com.wei.service;

import com.wei.pojo.DeptLog;

public interface DeptLogService {
    //记录事物操作日志
    void insert(DeptLog deptLog);
}
