package com.liuliume.portal.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liuliume.portal.common.Admins;
import com.liuliume.portal.common.Constants;
import com.liuliume.portal.common.JData;
import com.liuliume.portal.service.LoginService;

@Controller
@RequestMapping(value="/",method={RequestMethod.GET,RequestMethod.POST})
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="loginPage",method={RequestMethod.GET,RequestMethod.POST})
	public String loginPage(){
		return "login";
	}
	
	@RequestMapping(value="login",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JData login(String name,String passwd,HttpServletRequest request){
		JData jData = new JData("登陆成功",true);
		if(!loginService.login(name, passwd)){
			jData.setSuccess(false);
			jData.setDetail("用户名或密码错误");
		}else{
			Admins admins = new Admins();
			admins.setAdmin_name(name);
			admins.setName(name);
			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSION_ADMIN, admins);
		}
		return jData;
	}
	
	@RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="logout",method={RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.SESSION_ADMIN);
		return "login";
	}
}
