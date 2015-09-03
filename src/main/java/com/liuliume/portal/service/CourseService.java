package com.liuliume.portal.service;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Room;

import java.util.List;

public interface CourseService {
	
	/**
	 * 查询Address表
	 * @param seed
	 * @throws Exception
	 */
	public List<Course> list(Seed<Course> seed) throws Exception;

    public Course findCourseById(Integer course_id) throws Exception;

    public void createOrUpdate(Course course) throws Exception;

    public void batchDelete(String course_ids) throws Exception;
    
    public List<Course> listAllCourse();
}
