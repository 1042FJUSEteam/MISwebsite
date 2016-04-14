package com.practice.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
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

	@RequestMapping(value = "/rankTeacherList", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView changeTeacherRank(@RequestBody String rank) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<LinkedHashMap<String, Object>> rankList = null;
		List<RankInfo> rankInfo = new ArrayList<RankInfo>();
		try {
			rankList = objectMapper.readValue(rank, List.class);
			// 獲取json包的大小
			// System.out.println(rankList.size());
			for (int i = 0; i < rankList.size(); i++) {
				Map<String, Object> map = rankList.get(i);
				Set<String> set = map.keySet();
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					String key = it.next();
					System.out.println(key + ":" + map.get(key));
					//怎麼把這些值封裝到list中？
				}
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	// 學歷
	@RequestMapping(value = "/newTeaEdu", method = RequestMethod.POST)
	public ModelAndView newTeaEdu(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherEduInfo eduInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaEdu(teaInfo, eduInfo);
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

	@RequestMapping(value = "/deleteTeaEdu", method = RequestMethod.POST)
	public ModelAndView deleteTeaEdu(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherEduInfo eduInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaEdu(teaInfo, eduInfo);
		return model;
	}

	// 經歷
	@RequestMapping(value = "/newTeaExp", method = RequestMethod.POST)
	public ModelAndView newTeaExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherExpInfo expInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaExp(teaInfo, expInfo);
		return model;
	}

	@RequestMapping(value = "/updateTeaExp", method = RequestMethod.POST)
	public ModelAndView updateTeaExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherExpInfo expInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeaExp(teaInfo, expInfo);
		return model;
	}

	@RequestMapping(value = "/deleteTeaExp", method = RequestMethod.POST)
	public ModelAndView deleteTeaExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherExpInfo expInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaExp(teaInfo, expInfo);
		return model;
	}

	// 專長
	@RequestMapping(value = "/newTeaSpe", method = RequestMethod.POST)
	public ModelAndView newTeaSpe(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherSpeInfo speInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaSpe(teaInfo, speInfo);
		return model;
	}

	@RequestMapping(value = "/updateTeaSpe", method = RequestMethod.POST)
	public ModelAndView updateTeaSpe(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherSpeInfo speInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeaSpe(teaInfo, speInfo);
		return model;
	}

	@RequestMapping(value = "/deleteTeaSpe", method = RequestMethod.POST)
	public ModelAndView deleteTeaSpe(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherSpeInfo speInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaSpe(teaInfo, speInfo);
		return model;
	}

	// 期刊論文
	@RequestMapping(value = "/newIssuePaper", method = RequestMethod.POST)
	public ModelAndView newIssuePaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo IssuePaperInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newIssuePaper(teaInfo, IssuePaperInfo);
		return model;
	}

	@RequestMapping(value = "/updateIssuePaper", method = RequestMethod.POST)
	public ModelAndView updateIssuePaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo IssuePaperInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateIssuePaper(teaInfo, IssuePaperInfo);
		return model;
	}

	@RequestMapping(value = "/deleteIssuePaper", method = RequestMethod.POST)
	public ModelAndView deleteIssuePaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo IssuePaperInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteIssuePaper(teaInfo, IssuePaperInfo);
		return model;
	}

	// 研討會論文
	@RequestMapping(value = "/newMeetingPaper", method = RequestMethod.POST)
	public ModelAndView newMeetingPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo meetingPaperInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newMeetingPaper(teaInfo, meetingPaperInfo);
		return model;
	}

	@RequestMapping(value = "/updateMeetingPaper", method = RequestMethod.POST)
	public ModelAndView updateMeetingPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo MeetingPaperInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateMeetingPaper(teaInfo, MeetingPaperInfo);
		return model;
	}

	@RequestMapping(value = "/deleteMeetingPaper", method = RequestMethod.POST)
	public ModelAndView deleteMeetingPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo MeetingPaperInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteMeetingPaper(teaInfo, MeetingPaperInfo);
		return model;
	}

	// 書籍
	@RequestMapping(value = "/newBooks", method = RequestMethod.POST)
	public ModelAndView newBooks(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo booksInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newBooks(teaInfo, booksInfo);
		return model;
	}

	@RequestMapping(value = "/updateBooks", method = RequestMethod.POST)
	public ModelAndView updateBooks(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo booksInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateBooks(teaInfo, booksInfo);
		return model;
	}

	@RequestMapping(value = "/deleteBooks", method = RequestMethod.POST)
	public ModelAndView deleteBooks(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo booksInfo) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteBooks(teaInfo, booksInfo);
		return model;
	}

	// 技術報告
	@RequestMapping(value = "/newTechReport", method = RequestMethod.POST)
	public ModelAndView newTechReport(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo techReport) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTechReport(teaInfo, techReport);
		return model;
	}

	@RequestMapping(value = "/updateTechReport", method = RequestMethod.POST)
	public ModelAndView updateTechReport(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo techReport) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTechReport(teaInfo, techReport);
		return model;
	}

	@RequestMapping(value = "/deleteTechReport", method = RequestMethod.POST)
	public ModelAndView deleteTechReport(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo techReport) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTechReport(teaInfo, techReport);
		return model;
	}

	// 畢業論文
	@RequestMapping(value = "/newTeacherPaper", method = RequestMethod.POST)
	public ModelAndView newTeacherPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo teacherPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeacherPaper(teaInfo, teacherPaper);
		return model;
	}

	@RequestMapping(value = "/updateTeacherPaper", method = RequestMethod.POST)
	public ModelAndView updateTeacherPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo teacherPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeacherPaper(teaInfo, teacherPaper);
		return model;
	}

	@RequestMapping(value = "/deleteTeacherPaper", method = RequestMethod.POST)
	public ModelAndView deleteTeacherPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo teacherPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeacherpaper(teaInfo, teacherPaper);
		return model;
	}

	// 在資訊相關雜誌上近幾年發表之文章
	@RequestMapping(value = "/newMagazinePaper", method = RequestMethod.POST)
	public ModelAndView newMagazinePaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo magazinePaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newMagazinePaper(teaInfo, magazinePaper);
		return model;
	}

	@RequestMapping(value = "/updateMagazinePaper", method = RequestMethod.POST)
	public ModelAndView updateMagazinePaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo magazinePaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateMagazinePaper(teaInfo, magazinePaper);
		return model;
	}

	@RequestMapping(value = "/deleteMagazinePaper", method = RequestMethod.POST)
	public ModelAndView deleteMagazinePaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo magazinePaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteMagazinePaper(teaInfo, magazinePaper);
		return model;
	}

	// 期刊審查中論文
	@RequestMapping(value = "/newWaitingPaper", method = RequestMethod.POST)
	public ModelAndView newWaitingPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo waitingPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newWaitingPaper(teaInfo, waitingPaper);
		return model;
	}

	@RequestMapping(value = "/updateWaitingPaper", method = RequestMethod.POST)
	public ModelAndView updateWaitingPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo waitingPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateWaitingPaper(teaInfo, waitingPaper);
		return model;
	}

	@RequestMapping(value = "/deleteWaitingPaper", method = RequestMethod.POST)
	public ModelAndView deleteWaitingPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo waitingPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteWaitingPaper(teaInfo, waitingPaper);
		return model;
	}

	// 学术著作
	@RequestMapping(value = "/newScholarPaper", method = RequestMethod.POST)
	public ModelAndView newScholarPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo scholarPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newScholarPaper(teaInfo, scholarPaper);
		return model;
	}

	@RequestMapping(value = "/updateScholarPaper", method = RequestMethod.POST)
	public ModelAndView updateScholarPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo scholarPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateScholarPaper(teaInfo, scholarPaper);
		return model;
	}

	@RequestMapping(value = "/deleteScholarPaper", method = RequestMethod.POST)
	public ModelAndView deleteScholarPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo scholarPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteScholarPaper(teaInfo, scholarPaper);
		return model;
	}

	// 研究奖励
	@RequestMapping(value = "/newTeaAwa", method = RequestMethod.POST)
	public ModelAndView newTeaAwa(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherAwardInfo teaAwa) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaAwa(teaInfo, teaAwa);
		return model;
	}

	@RequestMapping(value = "/updateTeaAwa", method = RequestMethod.POST)
	public ModelAndView updateTeaAwa(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherAwardInfo teaAwa) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeaAwa(teaInfo, teaAwa);
		return model;
	}

	@RequestMapping(value = "/deleteTeaAwa", method = RequestMethod.POST)
	public ModelAndView deleteTeaAwa(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherAwardInfo teaAwa) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaAwa(teaInfo, teaAwa);
		return model;
	}

	// 研究計劃
	@RequestMapping(value = "/newTeaPlan", method = RequestMethod.POST)
	public ModelAndView newTeaPlan(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherPlanInfo teaPlana) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaPlan(teaInfo, teaPlana);
		return model;
	}

	@RequestMapping(value = "/updateTeaPlan", method = RequestMethod.POST)
	public ModelAndView updateTeaPlan(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherPlanInfo teaPlana) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeaPlan(teaInfo, teaPlana);
		return model;
	}

	@RequestMapping(value = "/deleteTeaPlan", method = RequestMethod.POST)
	public ModelAndView deleteTeaPlan(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherPlanInfo teaPlana) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaPlan(teaInfo, teaPlana);
		return model;
	}

	// 實務研究
	@RequestMapping(value = "/newPractical", method = RequestMethod.POST)
	public ModelAndView newPractical(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo practiceReach) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newPracticeReach(teaInfo, practiceReach);
		return model;
	}

	@RequestMapping(value = "/updatePractical", method = RequestMethod.POST)
	public ModelAndView updatePractical(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo practiceReach) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updatePracticeReach(teaInfo, practiceReach);
		return model;
	}

	@RequestMapping(value = "/deletePractical", method = RequestMethod.POST)
	public ModelAndView deletePractical(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo practiceReach) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deletePracticeReach(teaInfo, practiceReach);
		return model;
	}

	// 指導學生專題
	@RequestMapping(value = "/newTeaStuTopic", method = RequestMethod.POST)
	public ModelAndView newTeaStuTopic(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherStuTopic teacherStuTopic) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaStuTopic(teaInfo, teacherStuTopic);
		return model;
	}

	@RequestMapping(value = "/updateTeaStuTopic", method = RequestMethod.POST)
	public ModelAndView updateTeaStuTopic(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherStuTopic teacherStuTopic) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeaStuTopic(teaInfo, teacherStuTopic);
		return model;
	}

	@RequestMapping(value = "/deleteTeaStuTopic", method = RequestMethod.POST)
	public ModelAndView deleteTeaStuTopic(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherStuTopic teacherStuTopic) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaStuTopic(teaInfo, teacherStuTopic);
		return model;
	}

	// 指導研究生論文
	@RequestMapping(value = "/newTeaStuPaper", method = RequestMethod.POST)
	public ModelAndView newTeaStuPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherStuPaper teacherStuPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newTeaStuPaper(teaInfo, teacherStuPaper);
		return model;
	}

	@RequestMapping(value = "/updateTeaStuPaper", method = RequestMethod.POST)
	public ModelAndView updateTeaStuPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherStuPaper teacherStuPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateTeaStuPaper(teaInfo, teacherStuPaper);
		return model;
	}

	@RequestMapping(value = "/deleteTeaStuPaper", method = RequestMethod.POST)
	public ModelAndView deleteTeaStuPaper(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherStuPaper teacherStuPaper) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteTeaStuPaper(teaInfo, teacherStuPaper);
		return model;
	}

	// 其他教學經驗
	@RequestMapping(value = "/newOtherTeaExp", method = RequestMethod.POST)
	public ModelAndView newOtherTeaExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo otherTeaExp) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newOtherExp(teaInfo, otherTeaExp);
		return model;
	}

	@RequestMapping(value = "/updateOtherTeaExp", method = RequestMethod.POST)
	public ModelAndView updateOtherTeaExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo otherTeaExp) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateOtherExp(teaInfo, otherTeaExp);
		return model;
	}

	@RequestMapping(value = "/deleteOtherTeaExp", method = RequestMethod.POST)
	public ModelAndView deleteOtherTeaExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo otherTeaExp) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteOtherExp(teaInfo, otherTeaExp);
		return model;
	}

	// 校內服務
	@RequestMapping(value = "/newInSchService", method = RequestMethod.POST)
	public ModelAndView newInSchService(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo inSchService) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newInSchService(teaInfo, inSchService);
		return model;
	}

	@RequestMapping(value = "/updateInSchService", method = RequestMethod.POST)
	public ModelAndView updateInSchService(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo inSchService) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateInSchService(teaInfo, inSchService);
		return model;
	}

	@RequestMapping(value = "/deleteInSchService", method = RequestMethod.POST)
	public ModelAndView deleteInSchService(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo inSchService) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteInSchService(teaInfo, inSchService);
		return model;
	}

	// 校外服務
	@RequestMapping(value = "/newOutSchService", method = RequestMethod.POST)
	public ModelAndView newOutSchService(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo outSchService) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newOutSchService(teaInfo, outSchService);
		return model;
	}

	@RequestMapping(value = "/updateOutSchService", method = RequestMethod.POST)
	public ModelAndView updateOutSchService(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo outSchService) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateOutSchService(teaInfo, outSchService);
		return model;
	}

	@RequestMapping(value = "/deleteOutSchService", method = RequestMethod.POST)
	public ModelAndView deleteOutSchService(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo outSchService) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteOutSchService(teaInfo, outSchService);
		return model;
	}

	// 擔任委員
	@RequestMapping(value = "/newAsCommitMem", method = RequestMethod.POST)
	public ModelAndView newAsCommitMem(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo asCommitMem) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newAsCommitMem(teaInfo, asCommitMem);
		return model;
	}

	@RequestMapping(value = "/updateAsCommitMem", method = RequestMethod.POST)
	public ModelAndView updateAsCommitMem(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo asCommitMem) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateAsCommitMem(teaInfo, asCommitMem);
		return model;
	}

	@RequestMapping(value = "/deleteAsCommitMem", method = RequestMethod.POST)
	public ModelAndView deleteAsCommitMem(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo asCommitMem) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteAsCommitMem(teaInfo, asCommitMem);
		return model;
	}

	// 學術經驗
	@RequestMapping(value = "/newScholarExp", method = RequestMethod.POST)
	public ModelAndView newScholarExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo scholarExp) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.newScholarExp(teaInfo, scholarExp);
		return model;
	}

	@RequestMapping(value = "/updateScholarExp", method = RequestMethod.POST)
	public ModelAndView updateScholarExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo scholarExp) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.updateScholarExp(teaInfo, scholarExp);
		return model;
	}

	@RequestMapping(value = "/deleteScholarExp", method = RequestMethod.POST)
	public ModelAndView deleteScholarExp(@ModelAttribute TeacherBasicInfoAdmin teaInfo,
			@ModelAttribute TeacherOtherInfo scholarExp) {
		ModelAndView model = new ModelAndView("redirect:/updateTeacherDetail?teaCode=" + teaInfo.getTeaCode());
		TeacherInfoAdminDAO teacherInfoAdminDAO = (TeacherInfoAdminDAO) context.getBean("teacherInfoAdminDAO");
		teacherInfoAdminDAO.deleteScholarExp(teaInfo, scholarExp);
		return model;
	}
}
