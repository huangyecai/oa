package com.hyc.oa.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyc.oa.modules.login.entity.Login;
import com.hyc.oa.modules.login.service.LoginService;
import com.hyc.oa.modules.user.entity.UserRole;
import com.hyc.oa.modules.user.service.UserRoleService;
import com.hyc.oa.modules.user.service.UserService;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserRoleService userRoleService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username==null || username.equals("")) {
			System.out.println("用户名为空"+username);
			return null;
		}
		com.hyc.oa.modules.user.entity.User user=userService.getUserByMobile(username);
		 List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
		 Login login = loginService.get(user.getId());
		 if (login == null ) {
			return  null;
		}else if ( login.getAuthFlag() == 0) {
			System.out.println("账号未激活"+username);
			return null;
		}
		List<UserRole> roles = userRoleService.getByUserId(user.getId());
		for (UserRole role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole().getCode()));
			System.out.println("拥有的角色:"+role.getRole().getName());
		}
		return  new User(user.getMobile(),login.getPassword(), authorities);
	}

}
