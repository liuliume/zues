package com.liuliume.portal.controller.room;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.entity.Room;
import com.liuliume.portal.model.AddressLevelEnum;
import com.liuliume.portal.service.AddressService;
import com.liuliume.portal.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clement on 8/29/15.
 */
@Controller
@RequestMapping(value={"/room"},method= RequestMethod.GET)
public class RoomController {

    private Logger logger = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @RequestMapping(value="list",method=RequestMethod.GET)
    private ModelAndView list(ModelMap map,@SeedParam Seed<Room> seed){
        logger.info("call AddressController.list");
        try {
            roomService.list(seed);
            seed.setActionPath("room/list");
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), seed.toString()), e);
        }
        ModelAndView mav = new ModelAndView("room/list_room");
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
    private List<Room> listForJson(){
        logger.info("call AddressController.list");
        List<Room> room = null;
        try {
            room = roomService.listAllRooms();
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), "", e));
        }
        return room;
    }

    @RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(ModelMap model,@RequestParam(value="room_id",required=false)Integer room_id) {
        Room room = null;
        try {
            logger.info("call AddressController.index");
            room = roomService.findRoomById(room_id);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),room_id,e);
        }
        ModelAndView mav = new ModelAndView("/room/index_room");
        if(room != null){
            model.put("room", room);
        }
        return mav;
    }

    @RequestMapping(value="createOrUpdate",method=RequestMethod.POST)
    @ResponseBody
    public JData createOrUpdate(Room room,HttpServletRequest request,HttpServletResponse response){
        logger.info("call the createOrUpdate account");
        logger.debug(room.toString());
        JData jData = new JData();
        try {
            roomService.createOrUpdate(room);
            jData.setCode(200);
            jData.setSuccess(true);
            jData.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("create Or Update  Error." + e.getMessage()
                    + " account[" + room + "]", e);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("操作失败");
        }
        return jData;
    }

    @RequestMapping(value="batchDelete",method = RequestMethod.POST)
    @ResponseBody
    public JData batchDelete(@RequestParam(value="roomIds",required=true)String roomIds){
        logger.info("call the batch delete account");
        JData jdata = new JData();
        try {
            roomService.batchDelete(roomIds);
            jdata.setSuccess(true);
            jdata.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("Batch delete[Account] Error." + e.getMessage()
                    + " accountIds[" + roomIds + "]", e);
            jdata.setDetail("Batch delete [Account] failed!");
            jdata.setSuccess(false);
        }
        return jdata;
    }

}
