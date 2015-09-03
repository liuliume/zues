package com.liuliume.portal.mybatis.mapper;

import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.mybatis.MyBatisBaseMapper;
import com.liuliume.portal.mybatis.Parameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper extends MyBatisBaseMapper<Course> {

    int count(@Param("param")Parameter parameter);

    public List<Course> list(@Param("param")Parameter parameter);

    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    
    public List<Course> listAllCourse();
}