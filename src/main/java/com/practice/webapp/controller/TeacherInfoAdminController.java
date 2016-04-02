package com.practice.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.practice.webapp.entity.teacher.TeacherBasicInfo;
import com.practice.webapp.entity.teacher.TeacherBasicInfoAdmin;
import com.practice.webapp.dao.TeacherInfoAdminDAO;

@Controller
public class TeacherInfoAdminController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");

	@RequestMapping(value = "/teacherManage", method = RequestMethod.GET)
	public ModelAndView getTeacherList() {
		ModelAndView model = new ModelAndView("teacherManage");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		// 專任教師
		List<TeacherBasicInfoAdmin> proTeacherList = new ArrayList<TeacherBasicInfoAdmin>();
		proTeacherList = teacherInfoAdminDAO.getProTeacherInfoList();
		model.addObject("proTeacherList", proTeacherList);
		// 兼任教師
		List<TeacherBasicInfoAdmin> partTeacherList = new ArrayList<TeacherBasicInfoAdmin>();
		partTeacherList = teacherInfoAdminDAO.getPartTeacherInfoList();
		model.addObject("partTeacherList", partTeacherList);
		return model;
	}

	// @RequestMapping(value = "/updateTeacherInfo", method = RequestMethod.GET)
	// public ModelAndView updateTeacherInfoPage(@ModelAttribute
	// TeacherBasicInfo updateInfo) {
	// ModelAndView model = new ModelAndView("updateTeacherInfo");
	// TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO)
	// context.getBean("teacherInfoAdminDAO");
	// updateInfo = teacherInfoAdminDAO.get(updateInfo);
	// model.addObject("updateInfo", updateInfo);
	// return model;
	// }

	// @RequestMapping(value = "/updateTeacherInfo", method =
	// RequestMethod.POST)
	// public ModelAndView updateTeacherInfo(@ModelAttribute TeacherBasicInfo
	// updateInfo) {
	// ModelAndView model = new ModelAndView("redirect:/teacherManage");
	// TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO)
	// context.getBean("teacherInfoAdminDAO");
	// teacherInfoAdminDAO.update(updateInfo);
	// return model;
	// }

	@RequestMapping(value = "/deleteTeacherInfo", method = RequestMethod.POST)
	public ModelAndView deleteProduct(@ModelAttribute TeacherBasicInfoAdmin deleteInfo) {
		ModelAndView model = new ModelAndView("redirect:/teacherManage");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.delete(deleteInfo);
		return model;
	}

	@RequestMapping(value = "/changeView", method = RequestMethod.POST)
	public ModelAndView changeView(@ModelAttribute TeacherBasicInfoAdmin changeInfo) {
		ModelAndView model = new ModelAndView("redirect:/teacherManage");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.changeView(changeInfo);
		return model;
	}
	
	@RequestMapping(value = "/newTeacherBasicInfo", method = RequestMethod.POST)
	public ModelAndView newTeacherBasicInfo(@ModelAttribute TeacherBasicInfoAdmin newInfo) {
		ModelAndView model = new ModelAndView("redirect:/teacherManage");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeacherBasicInfo(newInfo);
		return model;
	}
}
