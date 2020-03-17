package com.boot.spring.RetailBanking.Login.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.spring.RetailBanking.Login.Bean.Login;
import com.boot.spring.RetailBanking.Login.Service.LoginSerivice;

@RestController
public class LoginController {
	
@Autowired
private LoginSerivice loginService;
	@GetMapping(value = "/weolcome")
	public String welcome() {
		return "Welcome to Docker";
	}
 @PostMapping(value = "/login")
 public ResponseEntity<Login> login(@RequestBody Login login){
	 try {
		 Login loginBean = loginService.getAccess(login);
		if(loginBean!=null)
			return new ResponseEntity<Login>(loginBean,HttpStatus.OK);
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new ResponseEntity<Login>(login,HttpStatus.UNAUTHORIZED);
	 
 }
}
