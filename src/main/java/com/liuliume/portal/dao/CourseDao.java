package com.liuliume.portal.dao;

import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;

import java.util.List;

public interface CourseDao {
	
	public int count(Parameter parameter);
	
	public List<Course> list(Parameter parameter);

    public Course findCourseById(Integer course_id);

    public void createCourse(Course course);

    public void updateCourse(Course course);

    public void delete(Course course);
    
    public List<Course> listAllCourse();

}
