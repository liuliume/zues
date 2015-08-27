package com.liuliume.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liuliume.portal.model.Menu;
import com.liuliume.portal.service.MenuService;

@Controller
@RequestMapping("/includes")
public class IncludesController extends BaseController{

private static final String MODEL_ATTR_MENUS = "menus";
	
	@Autowired 
	private MenuService menuService;
		
	@SuppressWarnings("unchecked")
	@RequestMapping("newmenu")
	public String newmenu(Model model){
		List<Menu> menus = menuService.getMenusAll();
		model.addAttribute(MODEL_ATTR_MENUS,menus);
		return "includes/newmenu";
	}
	
	public String util(){
		return "includes/util.jsp";
	}
}
