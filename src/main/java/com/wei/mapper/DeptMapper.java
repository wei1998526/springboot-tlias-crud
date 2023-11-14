package com.wei.mapper;

import com.wei.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询所有部门数据
    @Select("select id, name, create_time, update_time from tlias.dept;")
    List<Dept> list();

    //根据id删除部门
    @Delete("delete from tlias.dept where id = #{id}")
    void deleteById(Integer id);

    //新增部门
    @Insert("insert into tlias.dept (name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);


    //根据id查询部门
    @Select("select id, name, create_time, update_time from tlias.dept where id = #{id}")
    Dept select(Integer id);

    //根据id修改部门
    @Update("update tlias.dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
