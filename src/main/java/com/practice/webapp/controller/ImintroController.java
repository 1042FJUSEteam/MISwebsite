package com.practice.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
 
//upload
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
//===

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import org.springframework.ui.Model;

import com.practice.webapp.dao.ImintroDAO;

import com.practice.webapp.entity.introduction.Assitant;
import com.practice.webapp.entity.introduction.AssitantWork;
import com.practice.webapp.entity.introduction.Award;
import com.practice.webapp.entity.introduction.Imintro;
import com.practice.webapp.entity.introduction.NewInfoGu;
import com.practice.webapp.entity.introduction.Fileupload;

@Controller
public class ImintroController {
	
	ApplicationContext context =  new ClassPathXmlApplicationContext("spring-module.xml");
	//private static final Logger logger = LoggerFactory.getLogger(ImintroController.class);
	
	
	@RequestMapping(value = "/Imintro", method = RequestMethod.GET)
	public ModelAndView getImintroList(@ModelAttribute Imintro imintro){
		ModelAndView model = new ModelAndView("Imintro");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
				
		List<Imintro> imintroList = new ArrayList<Imintro>();
		imintroList = imintroDAO.getList(imintro);
		model.addObject("imintroList", imintroList);
		return model;
	}
	
	@RequestMapping(value = "/assitant", method = RequestMethod.GET)
	public ModelAndView getassitantList(){
		ModelAndView model = new ModelAndView("assitant");
		System.out.println("@@1");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		List<Assitant> assitantList = new ArrayList<Assitant>();
		assitantList = imintroDAO.getassitantList();
		model.addObject("assitantList", assitantList);		
		
		return model;
	}	
	
	@RequestMapping(value = "/Imintro2", method = RequestMethod.GET)
	public ModelAndView getImintroList2(@ModelAttribute Imintro imintro){
		ModelAndView model = new ModelAndView("deptintro");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		Imintro imintro1 = new Imintro();
		imintro1 = imintroDAO.gettwo(imintro, "1");
		model.addObject("imintro1", imintro1);
		
		Imintro imintro2 = new Imintro();
		imintro2 = imintroDAO.gettwo(imintro, "2");
		model.addObject("imintro2", imintro2);
		
		Imintro imintro3 = new Imintro();
		imintro3 = imintroDAO.getthree(imintro, "3");
		model.addObject("imintro3", imintro3);
		
		List<Imintro> getfileList = new ArrayList<Imintro>();
		getfileList = imintroDAO.getfile(imintro);
		model.addObject("getfileList", getfileList);

		return model;
	}
	
	@RequestMapping(value = "/back_imintro", method = RequestMethod.GET)
	public ModelAndView getImintroBackList(){
		ModelAndView model = new ModelAndView("fix");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		List<Imintro> getAll = new ArrayList<Imintro>();
		getAll = imintroDAO.getAll();
		model.addObject("getAll", getAll);
		
		List<Imintro> getDeptIntro = new ArrayList<Imintro>();
		getDeptIntro = imintroDAO.getDeptIntro();
		model.addObject("getDeptIntro", getDeptIntro);

		return model;
	}
	
	@RequestMapping(value = "/award", method = RequestMethod.GET)
	public ModelAndView getawardList(){
		ModelAndView model = new ModelAndView("award");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		List<Award> awardList = new ArrayList<Award>();
		awardList = imintroDAO.getawardList();
		model.addObject("awardList", awardList);

		return model;
	}
	
	@RequestMapping(value = "/updatedeptintro", method = RequestMethod.GET)
	public ModelAndView updatedeptintroget(@ModelAttribute Imintro imintro){
		
//		System.out.println("1 "+imintro.getDi_code());
//		//System.out.println("2 "+imintro1.getDic_code());
//		System.out.println("3 "+imintro.getDic_code());
//		System.out.println("4 "+imintro.getDic_name());
		
		ModelAndView model = new ModelAndView("updatedeptintro");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		Imintro updatedeptintro = new Imintro();
		updatedeptintro = imintroDAO.getdiccont(imintro);
		model.addObject("updatedeptintro", updatedeptintro);
		return model;
	}
	
	@RequestMapping(value = "/updatedeptintro", method = RequestMethod.POST)
	public ModelAndView updatedeptintropost(@ModelAttribute Imintro imintro){
		ModelAndView model = new ModelAndView("redirect:/back_imintro");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.update(imintro);
		
		return model;
	}
	
	@RequestMapping(value = "/deletedeptintro", method = RequestMethod.POST)
	public ModelAndView deleteImintro(@ModelAttribute Imintro imintro){
		System.out.println("@@1 " + imintro.getDi_code());
		System.out.println("@@2 " + imintro.getDic_code());
		ModelAndView model = new ModelAndView("redirect:/back_imintro");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.delete(imintro);
		
		return model;
	}
	
	@RequestMapping(value = "/insertdeptintro", method = RequestMethod.GET)
	public ModelAndView insertdeptintroget(@ModelAttribute Imintro imintro){
		ModelAndView model = new ModelAndView("insertdeptintro");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		Imintro insertdeptintro = new Imintro();
		insertdeptintro = imintroDAO.getdiccont(imintro);
		model.addObject("insertdeptintro", insertdeptintro);
		return model;
	}
	
	@RequestMapping(value = "/insertdeptintro", method = RequestMethod.POST)
	public ModelAndView insertdeptintropost(@ModelAttribute Imintro imintro){
		ModelAndView model = new ModelAndView("redirect:/back_imintro");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.insert(imintro);
		
		return model;
	}
	
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public ModelAndView upload() {
    	ModelAndView model = new ModelAndView("fileupload");
        return model;
    }
    
    @RequestMapping(value = "/savefiles", method = RequestMethod.POST)
    public ModelAndView savefiles(@ModelAttribute Fileupload fileupload) throws IllegalStateException, IOException {
    	ModelAndView model = new ModelAndView("fileuploadsuccess");
    	
        String saveDirectory = "d:/crunchify/";
 
        List<MultipartFile> crunchifyFiles = fileupload.getFiles();
 
        List<String> fileNames = new ArrayList<String>();
 
        if (null != crunchifyFiles && crunchifyFiles.size() > 0) {
            for (MultipartFile multipartFile : crunchifyFiles) {
 
                String fileName = multipartFile.getOriginalFilename();
                if (!"".equalsIgnoreCase(fileName)) {
                    // Handle file content - multipartFile.getInputStream()
                    multipartFile.transferTo(new File(saveDirectory + fileName));
                    fileNames.add(fileName);
                }
            }
        }
        
        model.addObject("fileNames", fileNames);
        
        return model;
    }
    
    //award
	@RequestMapping(value = "/back_award", method = RequestMethod.GET)
	public ModelAndView getbackaward(){
		ModelAndView model = new ModelAndView("back_award");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		List<Award> getAward = new ArrayList<Award>();
		getAward = imintroDAO.getawardList();
		model.addObject("getAward", getAward);		

		return model;
	}
	
	@RequestMapping(value = "/updateaward", method = RequestMethod.GET)
	public ModelAndView updateaward(@ModelAttribute Award award){
		
		ModelAndView model = new ModelAndView("updateaward");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		Award updateaward = new Award();
		updateaward = imintroDAO.getaward(award);
		model.addObject("updateaward", updateaward);
		return model;
	}
	
	@RequestMapping(value = "/updateaward", method = RequestMethod.POST)
	public ModelAndView updateawardpost(@ModelAttribute Award award){
		ModelAndView model = new ModelAndView("redirect:/back_award");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.updateaward(award);
		
		return model;
	}
	
	//ass
	@RequestMapping(value = "/back_updateass", method = RequestMethod.GET)
	public ModelAndView getbackass(){
		ModelAndView model = new ModelAndView("back_updateass");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		List<Assitant> getAss = new ArrayList<Assitant>();
		getAss = imintroDAO.getassitantList();
		model.addObject("getAss", getAss);		

		return model;
	}
	
	@RequestMapping(value = "/updateass", method = RequestMethod.GET)
	public ModelAndView updateass(@ModelAttribute Assitant assitant){
		
		ModelAndView model = new ModelAndView("updateass");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		Assitant updateass = new Assitant();
		updateass = imintroDAO.getass(assitant);
		model.addObject("updateass", updateass);
		return model;
	}
	
	@RequestMapping(value = "/updateass", method = RequestMethod.POST)
	public ModelAndView updateasspost(@ModelAttribute Assitant assitant){
		System.out.println(assitant.getM_ldap());
		ModelAndView model = new ModelAndView("redirect:/back_updateass");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.updateass(assitant);
		
		return model;
	}
	
	@RequestMapping(value = "/updateworkcontent", method = RequestMethod.GET)
	public ModelAndView updateworkcontent(@ModelAttribute Assitant assitant){
		
		ModelAndView model = new ModelAndView("updateworkcontent");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		List<AssitantWork> updateworkcontent = new ArrayList<AssitantWork>();
		updateworkcontent = imintroDAO.getassitantWorkList(assitant);
		model.addObject("updateworkcontent", updateworkcontent);
		return model;
	}
	
	@RequestMapping(value = "/updateworkcontent", method = RequestMethod.POST)
	public ModelAndView updateworkcontentpost(@ModelAttribute AssitantWork assitantWork){
		ModelAndView model = new ModelAndView("redirect:/back_updateass");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.updateworkcontent(assitantWork);
		
		return model;
	}	
	
	@RequestMapping(value = "/insertworkcontent", method = RequestMethod.GET)
	public ModelAndView insertworkcontentget(@ModelAttribute Assitant assitant){
//		System.out.println(assitant.getTea_code());
		ModelAndView model = new ModelAndView("insertworkcontent");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		
		AssitantWork insertworkcontent = new AssitantWork();
		insertworkcontent = imintroDAO.getassitantWork(assitant);
		model.addObject("insertworkcontent", insertworkcontent);

		return model;
	}
	

	@RequestMapping(value = "/insertworkcontent", method = RequestMethod.POST)
	public ModelAndView insertworkcontentpost(@ModelAttribute AssitantWork assitantWork){
//		System.out.println(assitantWork.getTeachercode());
		ModelAndView model = new ModelAndView("redirect:/back_updateass");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.insertworkcontent(assitantWork);
		
		return model;
	}
	
	@RequestMapping(value = "/insertass", method = RequestMethod.GET)
	public ModelAndView insertassget(){
		ModelAndView model = new ModelAndView("insertasistant");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");

		return model;
	}
	
	//teacher_code �A_I
	@RequestMapping(value = "/insertass", method = RequestMethod.POST)
	public ModelAndView insertasspost(@ModelAttribute NewInfoGu assitant){
		ModelAndView model = new ModelAndView("redirect:/back_updateass");
		ImintroDAO imintroDAO = (ImintroDAO)context.getBean("imintroDAO");
		imintroDAO.insertass(assitant);
		
		return model;
	}

}
