package com.liuliume.portal.controller.account;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.model.GenderEnum;
import com.liuliume.portal.service.AccountService;

@Controller
@RequestMapping(value={"/account"},method=RequestMethod.GET)
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
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
	
	@ModelAttribute("allGender")
	public Map<Integer, GenderEnum> getAllGender(){
		Map<Integer, GenderEnum> genders = new HashMap<Integer, GenderEnum>();
		 for(GenderEnum type : GenderEnum.values()){
			 genders.put(type.getId(), type);
		 }
		 return genders;
	}
}
