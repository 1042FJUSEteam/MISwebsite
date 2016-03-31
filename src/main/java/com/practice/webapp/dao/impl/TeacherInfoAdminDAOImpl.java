package com.practice.webapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.practice.webapp.dao.TeacherInfoAdminDAO;
import com.practice.webapp.entity.teacher.TeacherBasicInfo;

public class TeacherInfoAdminDAOImpl implements TeacherInfoAdminDAO {

	private DataSource dataSource;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement smt = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void update(TeacherBasicInfo updateInfo) {
		String sql1 = "Update members SET M_NAME = ?, M_PHONE = ?, M_EMAIL = ? " + "WHERE M_LDAP =?";
		String sql2 = "Update teacher SET TEA_EN_NAME = ?, Location = ? " + "WHERE TEA_LDAP =?";
		String sql3 = "Update members_class SET M_POST_CODE = ? " + "WHERE M_LDAP =?";

		try {
			PreparedStatement smt1 = null;
			PreparedStatement smt2 = null;
			PreparedStatement smt3 = null;
			conn = dataSource.getConnection();
			smt1 = conn.prepareStatement(sql1);
			smt1.setString(1, updateInfo.getTeaName());
			smt1.setString(2, updateInfo.getTeaTel());
			smt1.setString(3, updateInfo.getTeaEmail());
			smt1.setString(4, updateInfo.getTeaLDAP());
			smt1.executeUpdate();
			smt1.close();

			smt2 = conn.prepareStatement(sql2);
			smt2.setString(1, updateInfo.getTeaENName());
			smt2.setString(2, updateInfo.getTeaLoc());
			smt2.setString(3, updateInfo.getTeaLDAP());
			smt2.executeUpdate();
			smt2.close();

			smt3 = conn.prepareStatement(sql3);
			smt3.setString(1, updateInfo.getTeaPos());
			smt3.setString(2, updateInfo.getTeaLDAP());
			smt3.executeUpdate();
			smt3.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public void delete(TeacherBasicInfo deleteInfo) {

		String sql = "Update teacher, members_class SET members_class.M_POST_CODE = 'DZ' "
				+ "WHERE members_class.M_LDAP = teacher.TEA_LDAP and teacher.TEA_CODE = ?";

		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, deleteInfo.getTeaCode());
			smt.executeUpdate();
			smt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	@Override
	public TeacherBasicInfo get(TeacherBasicInfo info) {
		
		String sql = "select teacher.TEA_PHOTO, teacher.TEA_CODE, teacher.TEA_LDAP, members.M_NAME, teacher.TEA_EN_NAME, class_post.POST_NAME, members.M_PHONE, members.M_EMAIL, teacher.Location "				
				+ "from teacher, members, class_post, members_class "
				+ "where teacher.TEA_CODE = ? and teacher.TEA_LDAP = members.M_LDAP and members.M_LDAP = members_class.M_LDAP and members_class.M_POST_CODE = class_post.POST_CODE";


		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, info.getTeaCode());
			rs = smt.executeQuery();
			if(rs.next()){
				info.setTeaPic(rs.getString("TEA_PHOTO"));
				info.setTeaCode(rs.getString("TEA_CODE"));
				info.setTeaLDAP(rs.getString("TEA_LDAP"));
				info.setTeaName(rs.getString("M_NAME"));
				info.setTeaENName(rs.getString("TEA_EN_NAME"));
				info.setTeaPos(rs.getString("POST_NAME"));
				info.setTeaTel(rs.getString("M_PHONE"));
				info.setTeaEmail(rs.getString("M_EMAIL"));
				info.setTeaLoc(rs.getString("Location"));
			}
			rs.close();
			smt.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return info;
	}
}
