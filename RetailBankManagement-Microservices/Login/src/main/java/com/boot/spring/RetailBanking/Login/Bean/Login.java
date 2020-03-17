package com.boot.spring.RetailBanking.Login.Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="Login_Table")s
public class Login {
@Id
@Column(name = "loginId")
private String loginId;

@Column(name = "password")
private String password;

@Column(name = "role")
private String role;

public Login() {
	super();
}

public Login(String loginId, String password, String role) {
	super();
	this.loginId = loginId;
	this.password = password;
	this.role = role;
}

public String getLoginId() {
	return loginId;
}

public void setLoginId(String loginId) {
	this.loginId = loginId;
}


public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getPassword() {
	return password;
}

}
