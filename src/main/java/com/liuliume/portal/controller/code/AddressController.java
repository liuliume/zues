package com.liuliume.portal.controller.code;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.util.MD5Util;
import com.liuliume.common.util.ServletUtil;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.model.AddressLevelEnum;
import com.liuliume.portal.model.GenderEnum;
import com.liuliume.portal.service.AccountService;
import com.liuliume.portal.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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
@RequestMapping(value={"/code/address"},method= RequestMethod.GET)
public class AddressController {

    private Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value="list",method=RequestMethod.GET)
    private ModelAndView list(ModelMap map,@SeedParam Seed<HashMap<String,Object>> seed){
        logger.info("call AddressController.list");
        try {
            addressService.list(seed);
            seed.setActionPath("code/address/list");
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
    public ModelAndView index(ModelMap model,@RequestParam(value="address_id",required=false)Integer address_id) {
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

    @RequestMapping(value="indexForJson",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Address indexForJson(ModelMap model,@RequestParam(value="address_id",required=false)int address_id) {
        Address firstAddress = null;
        try {
            logger.info("call AddressController.index");
            firstAddress = addressService.findAddressById(address_id);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),address_id,e);
        }
        System.out.println(firstAddress);
        return firstAddress;
    }

    @RequestMapping(value="index_parent",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<Address> index_parent(ModelMap model,@RequestParam(value="parent_id",required=false)int parent_id) {
        List<Address> firstAddress = null;
        try {
            logger.info("call AddressController.index");
            firstAddress = addressService.findAddressByParentId(parent_id);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),parent_id,e);
        }
        System.out.println(firstAddress);
        return firstAddress;
    }


    @RequestMapping(value="createOrUpdate",method=RequestMethod.POST)
    @ResponseBody
    public JData createOrUpdate(Address address,HttpServletRequest request,HttpServletResponse response){
        logger.info("call the createOrUpdate account");
        logger.debug(address.toString());
        JData jData = new JData();
        try {
            addressService.createOrUpdate(address);
            jData.setCode(200);
            jData.setSuccess(true);
            jData.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("create Or Update  Error." + e.getMessage()
                    + " account[" + address + "]", e);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("操作失败");
        }
        return jData;
    }

    @RequestMapping(value="batchDelete",method = RequestMethod.POST)
    @ResponseBody
    public JData batchDelete(@RequestParam(value="addressIds",required=true)String addressIds){
        logger.info("call the batch delete account");
        JData jdata = new JData();
        try {
            addressService.batchDelete(addressIds);
            jdata.setSuccess(true);
            jdata.setDetail("操作成功");
        } catch (Exception e) {
            logger.error("Batch delete[Account] Error." + e.getMessage()
                    + " accountIds[" + addressIds + "]", e);
            jdata.setDetail("Batch delete [Account] failed!");
            jdata.setSuccess(false);
        }
        return jdata;
    }


    @RequestMapping(value="listAllProvince",method=RequestMethod.GET)
    @ResponseBody
    public JData listAllProvince() {
    	JData jData = new JData("操作成功",true);
    	List<Address> firstAddress = null;
        try {
            logger.info("call AddressController.listAllProvince");
            firstAddress = addressService.findAddressByLevel(AddressLevelEnum.First.getLevel());
            jData.setCode(200);
            jData.setSuccess(true);
            jData.setData(firstAddress);
        } catch (Exception e) {
            logger.error("AddressController.listAllProvince Error! reason:{},",
                    e.getMessage(),e);
            jData.setCode(500);
            jData.setSuccess(false);
            jData.setDetail("操作失败");
        }
        return jData;
	}

    @RequestMapping(value="listAllSubAddress",method=RequestMethod.GET)
    @ResponseBody
    public JData listAllSubAddress(@RequestParam(value="parent_id",required=true)int parent_id) {
    	List<Address> firstAddress = null;
    	JData jData = new JData("操作成功",true);
        try {
            logger.info("call AddressController.listAllSubAddress");
            firstAddress = addressService.findAddressByParentId(parent_id);
            jData.setData(firstAddress);
        } catch (Exception e) {
            logger.error("Error! reason:{}, Paramter:account_id:{}.",
                    e.getMessage(),parent_id,e);
            jData.setSuccess(false);
            jData.setDetail("操作失败");
        }
        System.out.println(firstAddress);
        return jData;
	}

    @RequestMapping(value="listUserAddress",method=RequestMethod.GET)
    @ResponseBody
    public JData listUserAddress(HttpServletRequest request) {
        JData jData = new JData();
        String mobile = ServletUtil.getCookie(request,"mobile");
        String sgid = ServletUtil.getCookie(request,"sgid");
        if(StringUtils.isNotEmpty(mobile) && StringUtils.isNotEmpty(sgid) && MD5Util.MD5WithSalt(mobile).equals(sgid)){
            try {
                Account account = accountService.findAccountByMobile(mobile);
                jData.setData(account);
                jData.setSuccess(true);
            } catch (Exception e) {
                logger.error("Error! reason:{}, Paramter:account_id:{}.",
                        e.getMessage(),mobile,e);
                jData.setSuccess(false);
                jData.setDetail("操作失败");
            }
        } else {
            jData.setSuccess(false);
            jData.setDetail("用户身份验证失败");
            jData.setData(null);
        }
        return jData;
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
