package com.practice.webapp.dao;

import com.practice.webapp.entity.login.Account;

public interface LoginDAO {
	public boolean checkLogin(Account login);
}
