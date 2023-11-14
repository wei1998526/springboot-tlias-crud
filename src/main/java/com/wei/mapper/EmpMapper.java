package com.wei.mapper;

import com.wei.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 查询员工总记录数
     * @return
     */
//    @Select("select count(*) from tlias.emp;")
//    long count();

    /**
     * 分页查询，获取列表数据
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from tlias.emp limit #{start},#{pageSize};")
//     List<Emp> page(Integer start, Integer pageSize);


    /**
     * 条件分页查询
     *
     * @param name   名字
     * @param gender 性别
     * @param begin  开始日期
     * @param end    结束日期
     * @return
     */
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除
    void deletes(List<Integer> ids);

    //添加员工
    @Insert("insert into tlias.emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)\n" +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    //修改员工
    void update(Emp emp);

    //查询员工
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time from tlias.emp where id = #{id};")
    Emp select(Integer id);

    /**
     * 根据用户名密码查询员工
     * @param emp
     * @return
     */
    @Select("select * from tlias.emp where username = #{username} and password = #{password};")
    Emp getByUserNameAndPassword(Emp emp);

    //根据部门id删除员工
    @Delete("delete from tlias.emp where dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);
}
