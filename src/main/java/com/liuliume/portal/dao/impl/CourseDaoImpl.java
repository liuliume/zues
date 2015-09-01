package com.liuliume.portal.dao.impl;

import com.liuliume.portal.dao.CourseDao;
import com.liuliume.portal.dao.RoomDao;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.mybatis.mapper.CourseMapper;
import com.liuliume.portal.mybatis.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao{

	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public int count(Parameter parameter) {
		return courseMapper.count(parameter);
	}

	@Override
	public List<Course> list(Parameter parameter) {
		return courseMapper.list(parameter);
	}

    @Override
    public Course findCourseById(Integer room_id) {
        return courseMapper.selectByPrimaryKey(room_id);
    }


    @Override
    public void createCourse(Course course) {
        courseMapper.createEntity(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateEntity(course);
    }

    @Override
    public void delete(Course course) {
        courseMapper.deleteEntityPhysically(course);
    }


}
