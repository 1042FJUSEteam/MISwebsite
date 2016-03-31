package com.practice.webapp.entity.teacher;

public class TeacherPlanInfo {
	private String name;//詳細資料類別名稱
	private boolean flag;//用於判定資料庫中是否有資料
	private String teaPlanPer;//計劃時期
	private String teaPanLeader;//研究計劃領導人
	private String teaPlanName;//研究計劃名稱
	private String teaPlanSpon;//研究計劃資助單位
	private String teaPlanMon;//研究計劃金額
	private String teaPlanPos;//在研究計劃中的職位
	
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
	public String getTeaPlanPer() {
		return teaPlanPer;
	}
	public void setTeaPlanPer(String teaPlanPer) {
		this.teaPlanPer = teaPlanPer;
	}
	public String getTeaPanLeader() {
		return teaPanLeader;
	}
	public void setTeaPanLeader(String teaPanLeader) {
		this.teaPanLeader = teaPanLeader;
	}
	public String getTeaPlanName() {
		return teaPlanName;
	}
	public void setTeaPlanName(String teaPlanName) {
		this.teaPlanName = teaPlanName;
	}
	public String getTeaPlanSpon() {
		return teaPlanSpon;
	}
	public void setTeaPlanSpon(String teaPlanSpon) {
		this.teaPlanSpon = teaPlanSpon;
	}
	public String getTeaPlanMon() {
		return teaPlanMon;
	}
	public void setTeaPlanMon(String teaPlanMon) {
		this.teaPlanMon = teaPlanMon;
	}
	public String getTeaPlanPos() {
		return teaPlanPos;
	}
	public void setTeaPlanPos(String teaPlanPos) {
		this.teaPlanPos = teaPlanPos;
	}
}
