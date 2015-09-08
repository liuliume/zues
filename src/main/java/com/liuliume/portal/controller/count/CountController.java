package com.liuliume.portal.controller.count;

import com.liuliume.portal.service.CountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clement on 9/6/15.
 */
@Controller
@RequestMapping(value={"/count"},method= RequestMethod.GET)
public class CountController {

    private Logger logger = LoggerFactory.getLogger(CountController.class);

    @Autowired
    private CountService countService;

    @RequestMapping(value = "room_count", method = RequestMethod.GET)
    @ResponseBody
    public Double roomCountMoney(@RequestParam(value="startDate",required=true)String startDate,
                               @RequestParam(value="endDate",required=true)String endDate,
                               @RequestParam(value="room_id",required=true)Integer room_id,
                               @RequestParam(value="animals_id",required=true)Integer animals_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date start_Date = sdf.parse(startDate);
            Date end_Date = sdf.parse(endDate);
            double money = countService.roomCountMoney(start_Date,end_Date,room_id,animals_id);
            return money;
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), null), e);
        }
        return null;
    }

    @RequestMapping(value = "course_count", method = RequestMethod.GET)
    @ResponseBody
    public Double courseCountMoney(@RequestParam(value="course_id",required=true)Integer course_id,
                                   @RequestParam(value="animals_id",required=true)Integer animals_id) {
        double money = 0.0;
        try{
            money = countService.courseCountMoney(course_id,animals_id);
        } catch (Exception e){
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), null), e);
        }
        return money;
    }
}
