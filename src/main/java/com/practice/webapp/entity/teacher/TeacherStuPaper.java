package com.practice.webapp.entity.teacher;

public class TeacherStuPaper {
	private String name;//�ԲӸ�����O�W��
	private boolean flag;//�Ω�P�w��Ʈw���O�_�����
	private String teaStuYear;//�~��
	private String teaStuName;//�ǥͦW�r
	private String TeaStuPaperName;//�פ���D
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getTeaStuYear() {
		return teaStuYear;
	}
	public void setTeaStuYear(String teaStuYear) {
		this.teaStuYear = teaStuYear;
	}
	public String getTeaStuName() {
		return teaStuName;
	}
	public void setTeaStuName(String teaStuName) {
		this.teaStuName = teaStuName;
	}
	public String getTeaStuPaperName() {
		return TeaStuPaperName;
	}
	public void setTeaStuPaperName(String teaStuPaperName) {
		TeaStuPaperName = teaStuPaperName;
	}
}