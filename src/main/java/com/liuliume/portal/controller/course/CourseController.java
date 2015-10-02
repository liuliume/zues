package com.liuliume.portal.controller.course;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.service.CourseService;
import com.liuliume.portal.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by clement on 8/29/15.
 */
@Controller
@RequestMapping(value={"/course"},method= RequestMethod.GET)
public class CourseController {

    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(value="list",method=RequestMethod.GET)
    private ModelAndView list(ModelMap map,@SeedParam Seed<Course> seed){
        logger.info("call AddressController.list");
        try {
            courseService.list(seed);
            seed.setActionPath("course/list");
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), seed.toString()), e);
        }
        ModelAndView mav = new ModelAndView("course/list_course");
//        Seed<HashMap<String, Object>> resultSeed = new Seed<HashMap<String,Object>>();
//        Method[] methods = Seed.class.getMethods();
//        for (Method method : methods){
//            if("get".equalsIgnoreCase(method.getName().substring(0,3))){
//                for(Method method1 : methods) {
//                    if("set".equalsIgnoreCase(method1.getName().substring(0,3))
//                            && method.getName().substring(3,method.getName().length()-1).equalsIgnoreCase(method1.getName().substring(3,method1.getName().length()-1))
//                            && "result".equalsIgnoreCase(method1.getName().substring(3,method1.getName().length()-1))) {
//                        try {
//                            method1.invoke(resultSeed,method.invoke(seed));
//                        } catch (IllegalAccessException e) {
//                            e.printStackTrace();
//                        } catch (InvocationTargetException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        resultSeed.setResult(list);
        map.put("seed", seed);
        return mav;
    }

    @RequestMapping(value="listForJson",method=RequestMethod.GET)
    @ResponseBody
    private List<Course> listForJson(){
        logger.info("call AddressController.list");
        List<Course> list = null;
        JData jData = new JData();
        try {
            list = courseService.listAllCourse();
            jData.setData(list);
            jData.setCode(200);
            jData.setSuccess(true);
            jData.setDetail("获取课程类型成功");
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), null), e);
            jData.setData(null);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("获取课程类型失败");
        }
        return list;
    }

    @RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(ModelMap model,@RequestParam(value="course_id",required=false)Integer course_id) {
        Course course = null;
        try {
            logger.info("call AddressController.index");
            course = courseService.findCourseById(course_id);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),course_id,e);
        }
        ModelAndView mav = new ModelAndView("/course/index_course");
        if(course != null){
            model.put("course", course);
        }
        return mav;
    }

    @RequestMapping(value="indexForJson",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Course indexForJson(@RequestParam(value="course_id",required=true)Integer course_id) {
        Course course = null;
        try {
            logger.info("call AddressController.index");
            course = courseService.findCourseById(course_id);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),course_id,e);
        }
        return course;
    }

    @RequestMapping(value="createOrUpdate",method=RequestMethod.POST)
    @ResponseBody
    public JData createOrUpdate(Course course,HttpServletRequest request,HttpServletResponse response){
        logger.info("call the createOrUpdate account");
        logger.debug(course.toString());
        JData jData = new JData();
        try {
            courseService.createOrUpdate(course);
            jData.setCode(200);
            jData.setSuccess(true);
            jData.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("create Or Update  Error." + e.getMessage()
                    + " account[" + course + "]", e);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("操作失败");
        }
        return jData;
    }

    @RequestMapping(value="batchDelete",method = RequestMethod.POST)
    @ResponseBody
    public JData batchDelete(@RequestParam(value="courseIds",required=true)String courseIds){
        logger.info("call the batch delete account");
        JData jdata = new JData();
        try {
            courseService.batchDelete(courseIds);
            jdata.setSuccess(true);
            jdata.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("Batch delete[Account] Error." + e.getMessage()
                    + " accountIds[" + courseIds + "]", e);
            jdata.setDetail("Batch delete [Account] failed!");
            jdata.setSuccess(false);
        }
        return jdata;
    }

}
