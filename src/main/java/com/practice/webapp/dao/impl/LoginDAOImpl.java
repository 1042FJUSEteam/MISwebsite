package com.practice.webapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.practice.webapp.dao.LoginDAO;
import com.practice.webapp.entity.login.Account;

public class LoginDAOImpl implements LoginDAO {

	private DataSource dataSource;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement smt = null;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean checkLogin(Account login) {
		boolean flag = false;
		String sql = "select user, pwd from login where user = ? and pwd = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1,login.getUserName());
			smt.setString(2, login.getPassword());
			rs = smt.executeQuery();
			if(rs.next()){
				flag = true;
			}
			rs.close();
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
		return flag;
	}

}
