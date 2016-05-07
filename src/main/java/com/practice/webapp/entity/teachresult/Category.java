package com.practice.webapp.entity.teachresult;

//取自division_class資料表
public class Category {
	private String DCcode;// 班級類別編號
	private String DCclass;// 班級名稱

	public String getDCcode() {
		return DCcode;
	}

	public void setDCcode(String dCcode) {
		DCcode = dCcode;
	}

	public String getDCclass() {
		return DCclass;
	}

	public void setDCclass(String dCclass) {
		DCclass = dCclass;
	}
}
