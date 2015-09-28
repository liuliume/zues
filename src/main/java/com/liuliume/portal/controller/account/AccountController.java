package com.liuliume.portal.controller.account;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.util.MD5Util;
import com.liuliume.common.util.ServletUtil;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.entity.Address;
import com.liuliume.portal.model.GenderEnum;
import com.liuliume.portal.service.AccountService;
import com.liuliume.portal.service.AddressService;

@Controller
@RequestMapping(value={"/account"},method=RequestMethod.GET)
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public ModelAndView list(ModelMap map,@SeedParam Seed<Account> seed){
		logger.info("call AccountController.list");
		try {
			accountService.list(seed);
			seed.setActionPath("account/list");
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"Get Account list error! reason:{0}, Paramter:seed:{1}.",
					e.getMessage(), seed.toString()), e);
		}
		ModelAndView mav = new ModelAndView("account/list");
		map.put("seed", seed);
		return mav;
	}
	
	@RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView index(ModelMap model,@RequestParam(value="account_id",required=false)Integer account_id){
		
		Account account = null;
		try {
			logger.info("call AccountCOntroller.index");
			account = accountService.findAccountById(account_id);
		} catch (Exception e) {
			logger.error("Error! reason:{}, Paramter:account_id:{}.",
					e.getMessage(),account_id,e);
		}
		ModelAndView mav = new ModelAndView("/account/index");
		if(account != null){
			model.put("account", account);
		}
		return mav;
	}
	
	@RequestMapping(value="batchDelete",method = RequestMethod.POST)
	@ResponseBody
	public JData batchDelete(@RequestParam(value="accountIds",required=true)String accountIds){
		logger.info("call the batch delete account");
		JData jdata = new JData();
		try {
			accountService.batchDelete(accountIds);
			jdata.setSuccess(true);
			jdata.setDetail("操作成功");
		} catch (Exception e) {
			logger.error("Batch delete[Account] Error." + e.getMessage()
					+ " accountIds[" + accountIds + "]", e);
			jdata.setDetail("Batch delete [Account] failed!");
			jdata.setSuccess(false);		
		}
		return jdata;
	}
	
	@RequestMapping(value="createOrUpdate",method=RequestMethod.POST)
	@ResponseBody
	public JData createOrUpdate(Account account,HttpServletRequest request,HttpServletResponse response){
		
		logger.info("call the createOrUpdate account");
		logger.debug(account.toString());
		JData jData = new JData();
		try {
			String req_ip = request.getRemoteAddr();
			account.setReg_ip(req_ip);
			accountService.createOrUpdate(account);
			jData.setCode(200);
			jData.setSuccess(true);
			jData.setDetail("操作成功");
		} catch (Exception e) {
			logger.error("create Or Update  Error." + e.getMessage()
					+ " account[" + account + "]", e);
			jData.setCode(500);
			jData.setSuccess(false);
			jData.setDetail("操作失败");
		}
		return jData;
	}
	
	/**
	 * 前端根据用户ID请求用户信息(包括地址)
	 * @param account_id
	 * @return
	 */
	@RequestMapping(value="listAccountByUserId",method=RequestMethod.GET)
	public JData listAccountByUserId(@RequestParam(value="account_id",required=true)Integer account_id ) {
		JData jData = new JData("操作成功",true);
		Account account = null;
		try {
			logger.info("call AccountCOntroller.index");
			account = accountService.findAccountById(account_id);
			jData.setCode(200);
			jData.setData(account);
		} catch (Exception e) {
			logger.error("Error! reason:{}, Paramter:account_id:{}.",
					e.getMessage(),account_id,e);
			jData.setCode(500);
			jData.setData(null);
			jData.setSuccess(false);
			jData.setDetail("操作失败");
		}
		return jData;
	}
	
	/**
	 * 前端根据用户ID请求用户信息(包括地址)
	 * @param
	 * @return
	 */
	@RequestMapping(value="listAccount",method=RequestMethod.GET)
	@ResponseBody
	public JData listAccount(HttpServletRequest request) {
		JData jData = new JData("操作成功",true);
		String mobile = ServletUtil.getCookie(request, "mobile");
		String sgid = ServletUtil.getCookie(request, "sgid");
		String sid = MD5Util.MD5WithSalt(mobile);
		if (StringUtils.isBlank(mobile) || !sid.equals(sgid)) {
			jData.setCode(320);
			jData.setSuccess(false);
			jData.setDetail("用户身份验证失败,请登陆！");
			return jData;
		}
		
		Account account = null;
		try {
			logger.info("call AccountCOntroller.listAccountByMobile");
			account = accountService.findAccountByMobile(mobile);
			jData.setCode(200);
			jData.setData(account);
		} catch (Exception e) {
			logger.error("Error! reason:{}, Paramter:mobile:{}.",
					e.getMessage(),mobile,e);
			jData.setCode(500);
			jData.setData(null);
			jData.setSuccess(false);
			jData.setDetail("操作失败");
		}
		return jData;
	}
	
	@RequestMapping(value="updateUserName",method=RequestMethod.POST)
	@ResponseBody
	public JData updateUserName(String name,HttpServletRequest request){
		JData jData = new JData("操作成功",true);
		String mobile = ServletUtil.getCookie(request, "mobile");
		String sgid = ServletUtil.getCookie(request, "sgid");
		String sid = MD5Util.MD5WithSalt(mobile);
		if (StringUtils.isBlank(mobile) || !sid.equals(sgid)) {
			jData.setCode(320);
			jData.setSuccess(false);
			jData.setDetail("用户身份验证失败,请登陆！");
			return jData;
		} else {
            try {
                accountService.updateNameByMobile(name,mobile);
            } catch (Exception e) {
                jData.setSuccess(false);
                jData.setCode(500);
                jData.setDetail("更改用户名失败！");
            }
        }
		return jData;
	}

    @RequestMapping(value="updateUserAddress",method=RequestMethod.POST)
    @ResponseBody
    public JData updateUserAddress(Integer province_id,Integer city_id,Integer area_id,String address,HttpServletRequest request){
        JData jData = new JData("操作成功",true);
        String mobile = ServletUtil.getCookie(request, "mobile");
        String sgid = ServletUtil.getCookie(request, "sgid");
        String sid = MD5Util.MD5WithSalt(mobile);
        if (StringUtils.isBlank(mobile) || !sid.equals(sgid)) {
            jData.setCode(320);
            jData.setSuccess(false);
            jData.setDetail("用户身份验证失败,请登陆！");
            return jData;
        } else {
            try {
                accountService.updateAddressByMobile(province_id,city_id,area_id,address,mobile);
            } catch (Exception e) {
                jData.setSuccess(false);
                jData.setCode(500);
                jData.setDetail("更改用户地址失败！");
            }
        }
        return jData;
    }
	
	@ModelAttribute("allGender")
	public Map<Integer, GenderEnum> getAllGender(){
		Map<Integer, GenderEnum> genders = new HashMap<Integer, GenderEnum>();
		 for(GenderEnum type : GenderEnum.values()){
			 genders.put(type.getId(), type);
		 }
		 return genders;
	}
	@ModelAttribute("allProvince")
	public List<Address> genAllProvince() throws Exception{
		List<Address> list = addressService.findAddressByLevel(Constants.LEVEL_PROVINCE.toString());
		Address all = new Address();
		all.setId(-1);
		all.setName("All");
		list.add(all);
		return list;
	}
}
