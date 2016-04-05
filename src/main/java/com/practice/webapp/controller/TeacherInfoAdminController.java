package com.practice.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.webapp.entity.teacher.RankInfo;
import com.practice.webapp.entity.teacher.TeacherAwardInfo;
import com.practice.webapp.entity.teacher.TeacherBasicInfoAdmin;
import com.practice.webapp.entity.teacher.TeacherEduInfo;
import com.practice.webapp.entity.teacher.TeacherExpInfo;
import com.practice.webapp.entity.teacher.TeacherOtherInfo;
import com.practice.webapp.entity.teacher.TeacherPlanInfo;
import com.practice.webapp.entity.teacher.TeacherSpeInfo;
import com.practice.webapp.entity.teacher.TeacherStuPaper;
import com.practice.webapp.entity.teacher.TeacherStuTopic;
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

	@RequestMapping(value = "/updateTeacherInfo", method = RequestMethod.GET)
	public ModelAndView updateTeacherInfoPage(@RequestParam String teaCode) {
		ModelAndView model = new ModelAndView("updateTeacherInfo");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		TeacherBasicInfoAdmin updateInfo = teacherInfoAdminDAO.get(teaCode);
		model.addObject("updateInfo", updateInfo);
		return model;
	}

	@RequestMapping(value = "/updateTeacherBasicInfoForm", method = RequestMethod.POST)
	public ModelAndView updateTeacherInfo(@ModelAttribute TeacherBasicInfoAdmin updateInfo) {
		ModelAndView model = new ModelAndView("redirect:/teacherManage");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.update(updateInfo);
		return model;
	}

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

	@RequestMapping(value = "/rankTeacherList", method = RequestMethod.GET)
	public ModelAndView rankTeacherList(@RequestParam String type) {
		ModelAndView model = new ModelAndView("rankTeacherList");
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		List<TeacherBasicInfoAdmin> info = teacherInfoAdminDAO.teacherShowList(type);
		model.addObject("info", info);
		return model;
	}

	// 無法獲取前端傳來的json，需要問一下瑞昱
	@RequestMapping(value = "/changeTeacherRank", method = RequestMethod.POST)
	public ModelAndView changeTeacherRank(@RequestBody RankInfo rank) {
		ModelAndView model = new ModelAndView("redirect:/teacherManage");

		return model;
	}

	@RequestMapping(value = "/updateTeacherDetail", method = RequestMethod.GET)
	public ModelAndView updateTeacherDetailInfoPage(@RequestParam String teaCode) {
		ModelAndView model = new ModelAndView("updateTeacherDetail");

		List<TeacherEduInfo> teacherEduInfo = new ArrayList<TeacherEduInfo>();
		List<TeacherExpInfo> teacherExpInfo = new ArrayList<TeacherExpInfo>();
		List<TeacherSpeInfo> teacherSpeInfo = new ArrayList<TeacherSpeInfo>();
		List<TeacherAwardInfo> teacherAwardInfo = new ArrayList<TeacherAwardInfo>();
		List<TeacherPlanInfo> teacherPlanInfo = new ArrayList<TeacherPlanInfo>();
		List<TeacherStuPaper> teacherStuPaper = new ArrayList<TeacherStuPaper>();
		List<TeacherStuTopic> teacherStuTopic = new ArrayList<TeacherStuTopic>();
		List<TeacherOtherInfo> teacherOtherInfo = new ArrayList<TeacherOtherInfo>();

		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		TeacherBasicInfoAdmin teacher = teacherInfoAdminDAO.get(teaCode);
		model.addObject("teacher", teacher);
		teacherEduInfo = teacherInfoAdminDAO.getTeacherEduInfo(teaCode);
		model.addObject("teacherEduInfo", teacherEduInfo);
		teacherExpInfo = teacherInfoAdminDAO.getTeacherExpInfo(teaCode);
		model.addObject("teacherExpInfo", teacherExpInfo);
		teacherSpeInfo = teacherInfoAdminDAO.getTeacherSpeInfo(teaCode);
		model.addObject("teacherSpeInfo", teacherSpeInfo);
		teacherAwardInfo = teacherInfoAdminDAO.getTeacherAwardInfo(teaCode);
		model.addObject("teacherAwardInfo", teacherAwardInfo);
		teacherPlanInfo = teacherInfoAdminDAO.getTeacherPlanInfo(teaCode);
		model.addObject("teacherPlanInfo", teacherPlanInfo);
		teacherStuPaper = teacherInfoAdminDAO.getTeacherStuPaper(teaCode);
		model.addObject("teacherStuPaper", teacherStuPaper);
		teacherStuTopic = teacherInfoAdminDAO.getTeacherStuTopic(teaCode);
		model.addObject("teacherStuTopic", teacherStuTopic);
		teacherOtherInfo = teacherInfoAdminDAO.getIssuePaper(teaCode);
		model.addObject("teacherIssuePaper", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getMeetingPaper(teaCode);
		model.addObject("teacherMeetingPaper", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getBooks(teaCode);
		model.addObject("teacherBooks", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getTechReport(teaCode);
		model.addObject("teacherTechReport", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getTecherPaper(teaCode);
		model.addObject("teacherPaper", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getMagazinePaper(teaCode);
		model.addObject("teacherMagazinePaper", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getWaitingPaper(teaCode);
		model.addObject("teacherWaitingPaper", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getScholarPaper(teaCode);
		model.addObject("teacherScholarPaper", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getPracticeReach(teaCode);
		model.addObject("teacherPracticeReach", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getOtherExp(teaCode);
		model.addObject("teacherOtherExp", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getInSchService(teaCode);
		model.addObject("teacherInSchService", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getOutSchService(teaCode);
		model.addObject("teacherOutSchService", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getAsCommitMem(teaCode);
		model.addObject("teacherAsCommitMem", teacherOtherInfo);
		teacherOtherInfo = teacherInfoAdminDAO.getScholarExp(teaCode);
		model.addObject("teacherScholarExp", teacherOtherInfo);

		return model;
	}

	@RequestMapping(value = "/deleteTeaEdu", method = RequestMethod.POST)
	public ModelAndView deleteTeaEdu(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherEduInfo eduInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaEdu(teaInfo, eduInfo);
		return model;
	}

	@RequestMapping(value = "/updateTeaEdu", method = RequestMethod.POST)
	public ModelAndView updateTeaEdu(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherEduInfo eduInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeaEdu(teaInfo, eduInfo);
		return model;
	}
	
	@RequestMapping(value = "/newTeaEdu", method = RequestMethod.POST)
	public ModelAndView newTeaEdu(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherEduInfo eduInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaEdu(teaInfo, eduInfo);
		return model;
	}
}
