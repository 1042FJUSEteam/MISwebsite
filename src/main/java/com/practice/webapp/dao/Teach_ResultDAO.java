package com.practice.webapp.dao;

import java.util.List;

import com.practice.webapp.entity.teachresult.*;

public interface Teach_ResultDAO {
	public List<Sym> getSymList();
	public List<Graduation> getGraduationList();
	public List<Tea_stu_paper> getTea_stu_paperList();
	public List<Award> getAwardList();
	
	public void insertSym(Sym sym);
	public void insertGraduation(Graduation graduation);
	public void insertTea_stu_paper(Tea_stu_paper tea_stu_paper);
	public void insertAward(Award award);
	
	public void deleteSym(Sym sym);
	public void deleteGraduation(Graduation graduation);
	public void deleteTea_stu_paper(Tea_stu_paper tea_stu_paper);
	public void deleteAward(Award award);
	
	public void updateSym(Sym sym);
	public void updateGraduation(Graduation graduation);
	public void updateTea_stu_paper(Tea_stu_paper tea_stu_paper);
	public void updateAward(Award award);
	
	public Sym get(Sym sym);
	public Graduation get(Graduation graduation);
	public Tea_stu_paper get(Tea_stu_paper tea_stu_paper);
	public Award get(Award award);
	public Sym getSym(int sym_code);
	public Graduation getGraduation(String gra_code);
	public Tea_stu_paper getTea_stu_paper(int paperid);
	public Award getAward(int articleid);
	
}
