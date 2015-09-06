package com.liuliume.portal.controller.count;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by clement on 9/6/15.
 */
@Controller
@RequestMapping(value={"/count"},method= RequestMethod.GET)
public class CountController {

    @RequestMapping(value = "room_count", method = RequestMethod.GET)
    @ResponseBody
    public void roomCountMoney(String startDate,String endDate,Integer room_id,Integer animals_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date start_Date = sdf.parse(startDate);
            Date end_Date = sdf.parse(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
