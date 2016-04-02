package com.practice.webapp.dao;

import java.util.List;

import com.practice.webapp.entity.teacher.TeacherBasicInfo;
import com.practice.webapp.entity.teacher.TeacherBasicInfoAdmin;

public interface TeacherInfoAdminDAO {
	public List<TeacherBasicInfoAdmin> getProTeacherInfoList();//獲取專任教師基本資料
	public List<TeacherBasicInfoAdmin> getPartTeacherInfoList();//獲取兼任教師基本資料
	public void update(TeacherBasicInfoAdmin updateInfo);//更新教師基本資料
	public void delete(TeacherBasicInfoAdmin deleteInfo);//刪除教師（判定爲離校）
	public TeacherBasicInfoAdmin get(TeacherBasicInfoAdmin info);//獲取特定教師的基本資料
	public void changeView(TeacherBasicInfoAdmin changeInfo);//更改教師信息是否顯示在前端
	public void newTeacherBasicInfo(TeacherBasicInfoAdmin newInfo);//新增教師基本信息
}
