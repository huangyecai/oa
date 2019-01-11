package com.hyc.oa.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hyc.oa.common.service.CustomUserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 这个地方的注解必须加上@Bean
	 */
	@Bean
	protected UserDetailsService userDetailsService() {
		return new CustomUserService();
	}
	
	/**
	 * 认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		
		auth.userDetailsService(userDetailsService()).passwordEncoder( new BCryptPasswordEncoder());
	}
	
	/**
	 * 拦截
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        .anyRequest().authenticated() 
        .and()
        .formLogin().usernameParameter("username").passwordParameter("password")//开启自动配置的登录功能
	        .loginPage("/login")
	        .defaultSuccessUrl("/loginSuccess").
        and()
		//开启自动配置的注销功能。
        .logout().logoutSuccessUrl("/");
		//注销成功以后来到首页
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面；
        //开启记住我功能
//        http.rememberMe().rememberMeParameter("rememberme");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie
        
        
      //以下这句就可以控制单个用户只能创建一个session，也就只能在服务器登录一次        
        http.sessionManagement().maximumSessions(1).expiredUrl("/login");
        //iframe问题
//        他的值有三个：
//
//        （1）DENY — 表示该页面不允许在 frame 中展示，即便是在相同域名的页面中嵌套也不允许。
//
//        （2）SAMEORIGIN — 表示该页面可以在相同域名页面的 frame 中展示。
//
//        （3）ALLOW-FROM https://example.com/ — 表示该页面可以在指定来源的 frame 中展示。
        http.headers().frameOptions().disable()
        .and()
        .csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/zTree_v3/**");
		web.ignoring().antMatchers("/jquery-easyui-1.6.6/**");
	}

	
}
