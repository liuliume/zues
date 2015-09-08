package com.liuliume.portal.controller.hairdressing;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Course;
import com.liuliume.portal.entity.Hairdressing;
import com.liuliume.portal.service.CourseService;
import com.liuliume.portal.service.HairdressingService;

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
import java.util.Date;
import java.util.List;

/**
 * Created by clement on 8/29/15.
 */
@Controller
@RequestMapping(value={"/hairdressing"},method= RequestMethod.GET)
public class HairdressingController {

    private Logger logger = LoggerFactory.getLogger(HairdressingController.class);

    @Autowired
    private HairdressingService hairdressingService;

    @RequestMapping(value="list",method=RequestMethod.GET)
    private ModelAndView list(ModelMap map,@SeedParam Seed<Hairdressing> seed){
        logger.info("call AddressController.list");
        try {
            hairdressingService.list(seed);
            seed.setActionPath("hairdressing/list");
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), seed.toString()), e);
        }
        ModelAndView mav = new ModelAndView("hairdressing/list_hairdressing");
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

    @RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(ModelMap model,@RequestParam(value="hairdressing_id",required=false)Integer hairdressing_id) {
        Hairdressing hairdressing = null;
        try {
            logger.info("call AddressController.index");
            hairdressing = hairdressingService.findHairdressingById(hairdressing_id);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),hairdressing_id,e);
        }
        ModelAndView mav = new ModelAndView("/hairdressing/index_hairdressing");
        if(hairdressing != null){
            model.put("hairdressing", hairdressing);
        }
        return mav;
    }

    @RequestMapping(value="createOrUpdate",method=RequestMethod.POST)
    @ResponseBody
    public JData createOrUpdate(Hairdressing hairdressing,HttpServletRequest request,HttpServletResponse response){
        logger.info("call the createOrUpdate account");
        logger.debug(hairdressing.toString());
        JData jData = new JData();
        try {
            hairdressingService.createOrUpdate(hairdressing);
            jData.setCode(200);
            jData.setSuccess(true);
            jData.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("create Or Update  Error." + e.getMessage()
                    + " account[" + hairdressing + "]", e);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("操作失败");
        }
        return jData;
    }

    @RequestMapping(value="batchDelete",method = RequestMethod.POST)
    @ResponseBody
    public JData batchDelete(@RequestParam(value="hairdressingIds",required=true)String hairdressingIds){
        logger.info("call the batch delete account");
        JData jdata = new JData();
        try {
            hairdressingService.batchDelete(hairdressingIds);
            jdata.setSuccess(true);
            jdata.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("Batch delete[Account] Error." + e.getMessage()
                    + " accountIds[" + hairdressingIds + "]", e);
            jdata.setDetail("Batch delete [Account] failed!");
            jdata.setSuccess(false);
        }
        return jdata;
    }
    
    @RequestMapping(value="listAllHairDressing",method=RequestMethod.GET)
    @ResponseBody
    public JData listAllHairDressing(){
    	JData jData = new JData();
    	logger.info("call AddressController.listHairDressingType");
        try {
            List<Hairdressing> list = hairdressingService.listAllHairdressings();
            jData.setData(list);
            jData.setCode(200);
            jData.setSuccess(true);
            jData.setDetail("获取美容类型成功");
        } catch (Exception e) {
            logger.error(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",e.getMessage(),e);
            jData.setData(null);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("获取美容类型失败");
        }
        return jData;
    }

}
