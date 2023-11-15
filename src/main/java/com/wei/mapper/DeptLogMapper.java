package com.wei.mapper;


import com.wei.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {
    //记录事物操作日志
    @Insert("insert into tlias.dept_log(create_time, description) values (#{createTime}, #{description})")
    void insert(DeptLog deptLog);
}
