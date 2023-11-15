package com.wei.service;

import com.wei.pojo.Dept;

import java.util.List;

//部门业务规则(业务接口)
public interface DeptService {
    /**
     * 查询所有部门数据
     * @return 存储Dept对象的集合
     */
    List<Dept> list();

    /**
     * 根据id删除部门
     */
    void delete(Integer id) throws Exception;

    /**
     * 添加部门
     * @param 部门名称
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param id
     */
    Dept select(Integer id);

    /**
     * 修改部门
     * @param dept
     */
    void update(Dept dept);
}
