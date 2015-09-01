package com.liuliume.portal.dao.cond;

import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.QueryCond;

public class CourseQueryCond implements QueryCond{
	
	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
