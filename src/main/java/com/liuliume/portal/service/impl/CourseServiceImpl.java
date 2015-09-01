package com.liuliume.portal.service.impl;

import com.liuliume.common.pagination.Seed;
import com.liuliume.portal.common.MBox;
import com.liuliume.portal.dao.CourseDao;
import com.liuliume.portal.dao.RoomDao;
import com.liuliume.portal.dao.cond.CourseQueryCond;
import com.liuliume.portal.dao.cond.RoomQueryCond;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.mybatis.Parameter;
import com.liuliume.portal.service.CourseService;
import com.liuliume.portal.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

	private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Autowired
	private CourseDao courseDao;

	@Override
	public List<Course> list(Seed<Course> seed) throws Exception{
		logger.info("Address Service list bengins.");
		List<Course> result = new ArrayList<Course>();
		
		Parameter parameter = MBox.convertParameter(seed);
		
		CourseQueryCond cond = new CourseQueryCond();
        Course course = new Course();

		if(seed.getFilter().containsKey("name")){
			String name = seed.getFilter().get("name");
            if(!StringUtils.isEmpty(name))
                course.setCourseName(name);
		}
		//add other query condition here
		
		cond.setCourse(course);
		parameter.setCond(cond);
		
		int count = courseDao.count(parameter);
		seed.setTotalSize(count);
		if(count>0){
			result = courseDao.list(parameter);
			seed.setResult(result);
		}
		return result;
	}

    @Override
    public Course findCourseById(Integer course_id) throws Exception {
        Course course = null;
        if(course_id!=null)
            course = courseDao.findCourseById(course_id);
        return course;
    }

    @Override
    public void createOrUpdate(Course course) throws Exception {
        if(course==null)
            throw new IllegalArgumentException("course为空");
        if(null == course.getId()){//add
            courseDao.createCourse(course);
        }else {
            courseDao.updateCourse(course);
        }
    }

    @Override
    @Transactional
    public void batchDelete(String course_ids) throws Exception {
        String[] aids = course_ids.split(",");
        for (String aid : aids) {
            Course course= new Course();
            course.setId(new Integer(aid));
            courseDao.delete(course);
        }
    }

}
