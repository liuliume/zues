package com.liuliume.portal.controller.code;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.model.AddressLevelEnum;
import com.liuliume.portal.model.GenderEnum;
import com.liuliume.portal.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clement on 8/29/15.
 */
@Controller
@RequestMapping(value={"/code/address"},method= RequestMethod.GET)
public class AddressController {

    private Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @RequestMapping(value="list",method=RequestMethod.GET)
    private ModelAndView list(ModelMap map,@SeedParam Seed<HashMap<String,Object>> seed){
        logger.info("call AddressController.list");
        List<HashMap<String,Object>> list = null;
        try {
            addressService.list(seed);
        } catch (Exception e) {
            logger.error(MessageFormat.format(
                    "Get Account list error! reason:{0}, Paramter:seed:{1}.",
                    e.getMessage(), seed.toString()), e);
        }
        ModelAndView mav = new ModelAndView("code/list_address");
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
    public ModelAndView index(ModelMap model,@RequestParam(value="address_id",required=false)String address_id) {
        Address address = null;
        List<Address> firstAddress = null;
        try {
            logger.info("call AddressController.index");
            address = addressService.findAddressById(address_id);
            firstAddress = addressService.findAddressByLevel(AddressLevelEnum.First.getLevel());
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),address_id,e);
        }
        ModelAndView mav = new ModelAndView("/code/index_address");
        if(address != null){
            model.put("address", address);
        }
        if(firstAddress != null && firstAddress.size() > 0) {
            model.put("firstAddress",firstAddress);
        }
        return mav;
    }

    @RequestMapping(value="index_parent",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Address> index_parent(ModelMap model,@RequestParam(value="level",required=false)String level) {
        List<Address> firstAddress = null;
        try {
            logger.info("call AddressController.index");
            firstAddress = addressService.findAddressByLevel(level);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),level,e);
        }
        return firstAddress;
    }



    @ModelAttribute("allAddressLevel")
    public Map<String, AddressLevelEnum> getAllAddressLevel(){
        Map<String, AddressLevelEnum> addressLevel = new HashMap<String, AddressLevelEnum>();
        for(AddressLevelEnum type : AddressLevelEnum.values()){
            addressLevel.put(type.getLevel(), type);
        }
        return addressLevel;
    }

}
