package com.hyc.oa.modules.home.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyc.oa.common.utils.ConstantUtils;
import com.hyc.oa.common.utils.IpUtils;
import com.hyc.oa.common.utils.TreeNode;
import com.hyc.oa.modules.login.entity.Login;
import com.hyc.oa.modules.login.service.LoginService;
import com.hyc.oa.modules.system.menu.entity.Menu;
import com.hyc.oa.modules.system.menu.service.MenuService;
import com.hyc.oa.modules.user.entity.User;
import com.hyc.oa.modules.user.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/")
	public String test(HttpServletRequest request) {
		return "redirect:login";
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
	
	@RequestMapping("loginSuccess")
	public String loginSuccess(HttpServletRequest request) {
		 Principal principal = request.getUserPrincipal();
		 
		 System.out.println(principal.getName());
		 User user=userService.getUserByMobile(principal.getName());
		 Login login = new Login();
		 login.setUserId(user.getId());
		 login.setLoginIp(IpUtils.getRealIp(request));
		 login.setLoginDate(new Date());
		 login.setUpdateBy(user.getId());
		 login.setUpdateDate(new Date());;
		 loginService.save(login);
		 
		return "redirect:index";
	}
	
	@RequestMapping("login")
	public void login(HttpServletRequest request) {
	}
	
	

}
