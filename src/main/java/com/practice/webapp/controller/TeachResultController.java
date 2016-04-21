package com.practice.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.webapp.dao.*;
import com.practice.webapp.entity.teachresult.*;

@Controller
public class TeachResultController {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");

	@RequestMapping(value = "/Sym", method = RequestMethod.GET)
	public ModelAndView getArticleList() {
		ModelAndView model = new ModelAndView("Sym");
		Teach_ResultDAO symDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Sym> symList = new ArrayList<Sym>();
		symList = symDAO.getSymList();
		model.addObject("symList", symList);
		return model;
	}

	@RequestMapping(value = "/Graduation", method = RequestMethod.GET)
	public ModelAndView getGraduationList() {
		ModelAndView model = new ModelAndView("Gradution_yeah");
		Teach_ResultDAO Teach_ResultDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Graduation> graduationList = new ArrayList<Graduation>();
		graduationList = Teach_ResultDAO.getGraduationList();
		model.addObject("graduationList", graduationList);
		return model;
	}

	@RequestMapping(value = "/Tea_stu_paper", method = RequestMethod.GET)
	public ModelAndView getTea_stu_paperList() {
		ModelAndView model = new ModelAndView("Tea_stu_paper");
		Teach_ResultDAO Teach_ResultDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Tea_stu_paper> tea_stu_paperList = new ArrayList<Tea_stu_paper>();
		tea_stu_paperList = Teach_ResultDAO.getTea_stu_paperList();
		model.addObject("tea_stu_paperList", tea_stu_paperList);
		return model;
	}

	@RequestMapping(value = "/award-teachResult", method = RequestMethod.GET)
	public ModelAndView getAwardList() {
		ModelAndView model = new ModelAndView("award-teachResult");
		Teach_ResultDAO Teach_ResultDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Award> awardList = new ArrayList<Award>();
		awardList = Teach_ResultDAO.getAwardList();
		model.addObject("awardList", awardList);
		return model;
	}

	@RequestMapping(value = "/admin/insertSym", method = RequestMethod.GET)
	public ModelAndView insertArticlePage() {
		ModelAndView model = new ModelAndView("SymAdd");
		Teach_ResultDAO symDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");

		return model;
	}

	@RequestMapping(value = "/admin/insertSym", method = RequestMethod.POST)
	public ModelAndView insertSym(@ModelAttribute Sym sym) {
		ModelAndView model = new ModelAndView("redirect:/admin/SymAdmin");
		Teach_ResultDAO symDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		symDAO.insertSym(sym);

		return model;
	}

	@RequestMapping(value = "/admin/SymAdmin", method = RequestMethod.GET)
	public ModelAndView SymAdmin() {
		ModelAndView model = new ModelAndView("SymAdmin");

		Teach_ResultDAO symDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Sym> symList = new ArrayList<Sym>();
		symList = symDAO.getSymList();
		model.addObject("symList", symList);

		return model;
	}

	@RequestMapping(value = "/admin/deleteSym", method = RequestMethod.GET)
	public ModelAndView deleteSym(@ModelAttribute Sym sym) {
		ModelAndView model = new ModelAndView("redirect:/admin/SymAdmin");
		Teach_ResultDAO symDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		symDAO.deleteSym(sym);

		return model;
	}

	@RequestMapping(value = "/admin/insertGraduation", method = RequestMethod.GET)
	public ModelAndView insertGraduationPage() {
		ModelAndView model = new ModelAndView("GraduationAdd");
		Teach_ResultDAO graduationDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");

		return model;
	}

	@RequestMapping(value = "/admin/insertGraduation", method = RequestMethod.POST)
	public ModelAndView insertGraduation(@ModelAttribute Graduation graduation) {
		ModelAndView model = new ModelAndView("redirect:/admin/GraduationAdmin");
		Teach_ResultDAO graduationDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		graduationDAO.insertGraduation(graduation);

		return model;
	}

	@RequestMapping(value = "/admin/GraduationAdmin", method = RequestMethod.GET)
	public ModelAndView GraduationAdmin() {
		ModelAndView model = new ModelAndView("GraduationAdmin");
		Teach_ResultDAO Teach_ResultDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Graduation> graduationList = new ArrayList<Graduation>();
		graduationList = Teach_ResultDAO.getGraduationList();
		model.addObject("graduationList", graduationList);
		return model;
	}

	@RequestMapping(value = "/admin/deleteGraduation", method = RequestMethod.GET)
	public ModelAndView deleteGraduation(@ModelAttribute Graduation graduation) {
		ModelAndView model = new ModelAndView("redirect:/admin/GraduationAdmin");
		Teach_ResultDAO graduationDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		graduationDAO.deleteGraduation(graduation);

		return model;
	}

	@RequestMapping(value = "/admin/insertTea_stu_paper", method = RequestMethod.GET)
	public ModelAndView insertTea_stu_paperPage() {
		ModelAndView model = new ModelAndView("Tea_stu_paperAdd");

		return model;
	}

	@RequestMapping(value = "/admin/insertTea_stu_paper", method = RequestMethod.POST)
	public ModelAndView insertTea_stu_paper(@ModelAttribute Tea_stu_paper tea_stu_paper) {
		ModelAndView model = new ModelAndView("redirect:/admin/Tea_stu_paperAdmin");
		Teach_ResultDAO tea_stu_paperDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		tea_stu_paperDAO.insertTea_stu_paper(tea_stu_paper);

		return model;
	}

	@RequestMapping(value = "/admin/Tea_stu_paperAdmin", method = RequestMethod.GET)
	public ModelAndView Tea_stu_paperAdmin() {
		ModelAndView model = new ModelAndView("Tea_stu_paperAdmin");
		Teach_ResultDAO Teach_ResultDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Tea_stu_paper> tea_stu_paperList = new ArrayList<Tea_stu_paper>();
		tea_stu_paperList = Teach_ResultDAO.getTea_stu_paperList();
		model.addObject("tea_stu_paperList", tea_stu_paperList);
		return model;
	}

	@RequestMapping(value = "/admin/deleteTea_stu_paper", method = RequestMethod.GET)
	public ModelAndView deleteTea_stu_paper(@ModelAttribute Tea_stu_paper tea_stu_paper) {
		ModelAndView model = new ModelAndView("redirect:/admin/Tea_stu_paperAdmin");
		Teach_ResultDAO tea_stu_paperDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		tea_stu_paperDAO.deleteTea_stu_paper(tea_stu_paper);

		return model;
	}

	@RequestMapping(value = "/admin/insertAward", method = RequestMethod.GET)
	public ModelAndView insertAwardPage() {
		ModelAndView model = new ModelAndView("AwardAdd");
		Teach_ResultDAO awardDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");

		return model;
	}

	@RequestMapping(value = "/admin/insertAward", method = RequestMethod.POST)
	public ModelAndView insertAward(@ModelAttribute Award award) {
		ModelAndView model = new ModelAndView("redirect:/admin/AwardAdmin");
		Teach_ResultDAO awardDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		awardDAO.insertAward(award);

		return model;
	}

	@RequestMapping(value = "/admin/AwardAdmin", method = RequestMethod.GET)
	public ModelAndView AwardAdmin() {
		ModelAndView model = new ModelAndView("AwardAdmin");
		Teach_ResultDAO Teach_ResultDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		List<Award> awardList = new ArrayList<Award>();
		awardList = Teach_ResultDAO.getAwardList();
		model.addObject("awardList", awardList);
		return model;
	}

	@RequestMapping(value = "/admin/deleteAward", method = RequestMethod.GET)
	public ModelAndView deleteAward(@ModelAttribute Award award) {
		ModelAndView model = new ModelAndView("redirect:/admin/AwardAdmin");
		Teach_ResultDAO awardDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		awardDAO.deleteAward(award);

		return model;
	}

	@RequestMapping(value = "/admin/updateSym", method = RequestMethod.GET)
	public ModelAndView updateArticlePage(@RequestParam int sym_code) {
		ModelAndView model = new ModelAndView("SymUpdate");
		Teach_ResultDAO symDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		Sym symList = symDAO.getSym(sym_code);
		model.addObject("symList", symList);

		return model;
	}

	@RequestMapping(value = "/admin/updateSym", method = RequestMethod.POST)
	public ModelAndView updateArticle(@ModelAttribute Sym sym) {
		ModelAndView model = new ModelAndView("redirect:/admin/SymAdmin");
		Teach_ResultDAO symDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		symDAO.updateSym(sym);
		return model;
	}

	@RequestMapping(value = "/admin/updateGraduation", method = RequestMethod.GET)
	public ModelAndView updateGraduation(@RequestParam String gra_code) {
		ModelAndView model = new ModelAndView("GraduationUpdate");
		Teach_ResultDAO graduationDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		Graduation graduationList = graduationDAO.getGraduation(gra_code);
		model.addObject("graduationList", graduationList);

		return model;
	}

	@RequestMapping(value = "/admin/updateGraduation", method = RequestMethod.POST)
	public ModelAndView updateGraduation(@ModelAttribute Graduation graduation) {
		ModelAndView model = new ModelAndView("redirect:/admin/GraduationAdmin");
		Teach_ResultDAO graduationDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		graduationDAO.updateGraduation(graduation);
		return model;
	}

	@RequestMapping(value = "/admin/updateTea_stu_paper", method = RequestMethod.GET)
	public ModelAndView updateGraduation(@RequestParam int paperid) {
		ModelAndView model = new ModelAndView("Tea_stu_paperUpdate");
		Teach_ResultDAO tea_stu_paperDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		Tea_stu_paper tea_stu_paperList = tea_stu_paperDAO.getTea_stu_paper(paperid);
		model.addObject("tea_stu_paperList", tea_stu_paperList);

		return model;
	}

	@RequestMapping(value = "/admin/updateTea_stu_paper", method = RequestMethod.POST)
	public ModelAndView updateTea_stu_paper(@ModelAttribute Tea_stu_paper tea_stu_paper) {
		ModelAndView model = new ModelAndView("redirect:/admin/Tea_stu_paperAdmin");
		Teach_ResultDAO tea_stu_paperDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		tea_stu_paperDAO.updateTea_stu_paper(tea_stu_paper);
		return model;
	}

	@RequestMapping(value = "/admin/updateAward", method = RequestMethod.GET)
	public ModelAndView updateAward(@RequestParam int articleid) {
		ModelAndView model = new ModelAndView("AwardUpdate");
		Teach_ResultDAO awardDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		Award awardList = awardDAO.getAward(articleid);
		model.addObject("awardList", awardList);

		return model;
	}

	@RequestMapping(value = "/admin/updateAward", method = RequestMethod.POST)
	public ModelAndView updateAward(@ModelAttribute Award award) {
		ModelAndView model = new ModelAndView("redirect:/admin/AwardAdmin");
		Teach_ResultDAO awardDAO = (Teach_ResultDAO) context.getBean("TeachResultDAO");
		awardDAO.updateAward(award);
		return model;
	}

}
