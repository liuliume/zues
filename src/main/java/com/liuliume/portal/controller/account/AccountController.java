package com.liuliume.portal.controller.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liuliume.common.pagination.Seed;
import com.liuliume.common.web.spring.mvc.annotation.SeedParam;
import com.liuliume.portal.entity.Account;

@Controller
@RequestMapping(value={"/account"},method=RequestMethod.GET)
public class AccountController {

	private Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	private ModelAndView list(ModelMap map,@SeedParam Seed<Account> seed){
		logger.info("call AccountController.list");
		try {
			
		} catch (Exception e) {
		}
		ModelAndView mav = new ModelAndView("account/list");
		map.put("seed", seed);
		return mav;
	}
}
