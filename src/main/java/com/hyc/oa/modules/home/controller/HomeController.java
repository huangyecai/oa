package com.hyc.oa.modules.home.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.common.utils.TreeNode;
import com.hyc.oa.modules.system.menu.entity.Menu;
import com.hyc.oa.modules.system.menu.service.MenuService;

@Controller
public class HomeController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/")
	public String test(HttpServletRequest request) {
		return "redirect:index";
	}

	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		Menu menu = new Menu();
		menu.setStatus(ConstantUtils.ALIVE);
		List< Menu > list = menuService.list(menu);
		List< TreeNode > menuTree = menuService.makeTree(list, null);
		request.setAttribute("list1", menuTree);
		return "index";
	}

}
