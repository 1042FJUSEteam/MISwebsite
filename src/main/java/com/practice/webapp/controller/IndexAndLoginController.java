package com.practice.webapp.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//這裏寫網站首頁和登入的controller

import com.practice.webapp.dao.LoginDAO;
import com.practice.webapp.entity.login.Account;

@Controller
public class IndexAndLoginController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");

	@RequestMapping(value = "/MISwebsiteIndex", method = RequestMethod.GET)
	public ModelAndView showMISwebsiteIndex() {
		ModelAndView model = new ModelAndView("MISwebsiteIndex");

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showlogin() {
		ModelAndView model = new ModelAndView("login");

		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView checklogin(@ModelAttribute Account account) {
		ModelAndView model = new ModelAndView("redirect:/adminIndex");
		LoginDAO loginDAO = (LoginDAO) context.getBean("LoginDAO");
		boolean flag = loginDAO.checkLogin(account);
		if (flag == true){
			Account account_session = (Account) context.getBean("account");
			account_session.setUserName(account.getUserName());
			account_session.setPassword(account.getPassword());
		}else{
			model = new ModelAndView("login");
			model.addObject("message", "用戶名或密碼錯誤，請重新輸入");
			Account account_session = (Account)context.getBean("account");
			account_session.setUserName("");
			account_session.setPassword("");
		}
		
		return model;
	}

	@RequestMapping(value = "/adminIndex", method = RequestMethod.GET)
	public ModelAndView showadminIndex() {
		ModelAndView model = new ModelAndView("adminIndex");

		return model;
	}
}
