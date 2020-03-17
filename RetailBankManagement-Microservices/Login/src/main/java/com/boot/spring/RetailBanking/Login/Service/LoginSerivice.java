package com.boot.spring.RetailBanking.Login.Service;

import java.lang.reflect.Field;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.spring.RetailBanking.Login.Bean.Login;
import com.boot.spring.RetailBanking.Login.Repository.LoginRepo;
@Service
public class LoginSerivice {
	@Autowired
	private LoginRepo loginRepo;
	
	public Login getAccess(Login login) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
	Optional<Login> loginBean = loginRepo.findById(login.getLoginId());
	String beanPassword ;
	String password ;
	Field field = (loginBean.get()).getClass().getDeclaredField("password");
	
	if(loginBean!=null) {
		field.setAccessible(true);
		System.out.println(field.get(loginBean.get()).toString());
		beanPassword = (String)field.get(loginBean.get());
		password = (String)field.get(login);
		field.setAccessible(false);
		if(beanPassword.equals(password))
			return loginBean.get();
		}
	return null;
	}
	
	public LoginRepo getRepo() {
		return loginRepo;
	}

}
