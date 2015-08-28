package com.liuliume.portal.controller.account;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.entity.Account;
import com.liuliume.portal.service.AccountService;

@Controller
@RequestMapping(value={"/account"},method=RequestMethod.GET)
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	private ModelAndView list(ModelMap map,@SeedParam Seed<Account> seed){
		logger.info("call AccountController.list");
		try {
			accountService.list(seed);
		} catch (Exception e) {
			logger.error(MessageFormat.format(
					"Get Account list error! reason:{0}, Paramter:seed:{1}.",
					e.getMessage(), seed.toString()), e);
		}
		ModelAndView mav = new ModelAndView("account/list");
		map.put("seed", seed);
		return mav;
	}
}
