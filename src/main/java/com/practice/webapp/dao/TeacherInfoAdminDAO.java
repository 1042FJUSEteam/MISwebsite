package com.practice.webapp.dao;

import com.practice.webapp.entity.teacher.TeacherBasicInfo;

public interface TeacherInfoAdminDAO {
	public void update(TeacherBasicInfo updateInfo);
	public void delete(TeacherBasicInfo deleteInfo);
	public TeacherBasicInfo get(TeacherBasicInfo info);
}
