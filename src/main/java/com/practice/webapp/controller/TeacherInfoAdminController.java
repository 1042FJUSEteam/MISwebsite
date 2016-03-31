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

import com.practice.webapp.dao.TeacherInfoDAO;
import com.practice.webapp.entity.teacher.TeacherBasicInfo;
import com.practice.webapp.dao.TeacherInfoAdminDAO;

@Controller
public class TeacherInfoAdminController {
	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");

	//後臺獲取專任教師信息，還需要修改
	@RequestMapping(value = "/teacherManage", method = RequestMethod.GET)
	public ModelAndView getTeacherList() {
		ModelAndView model = new ModelAndView("teacherManage");
		TeacherInfoDAO teacherInfoDAO = (TeacherInfoDAO) context.getBean("teacherInfoDAO");
		List<TeacherBasicInfo> teacherList = new ArrayList<TeacherBasicInfo>();
		teacherList = teacherInfoDAO.getProTeacherInfoList();
		model.addObject("teacherList", teacherList);

		return model;
	}

	@RequestMapping(value = "/updateTeacherInfo", method = RequestMethod.GET)
	public ModelAndView updateTeacherInfoPage(@ModelAttribute TeacherBasicInfo updateInfo) {
		ModelAndView model = new ModelAndView("updateTeacherInfo");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		updateInfo = teacherInfoAdminDAO.get(updateInfo);
		model.addObject("updateInfo", updateInfo);
		return model;
	}

	@RequestMapping(value = "/updateTeacherInfo", method = RequestMethod.POST)
	public ModelAndView updateTeacherInfo(@ModelAttribute TeacherBasicInfo updateInfo) {
		ModelAndView model = new ModelAndView("redirect:/teacherManage");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.update(updateInfo);
		return model;
	}

	@RequestMapping(value = "/deleteTeacherInfo", method = RequestMethod.POST)
	public ModelAndView deleteProduct(@ModelAttribute TeacherBasicInfo deleteInfo) {
		ModelAndView model = new ModelAndView("redirect:/teacherManage");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.delete(deleteInfo);
		return model;
	}
}
