package com.boot.spring.RetailBanking.RetailBanking_AccountService.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.spring.RetailBanking.RetailBanking_AccountService.Bean.AccountBean;
import com.boot.spring.RetailBanking.RetailBanking_AccountService.Service.AccountService;

@RestController
@CrossOrigin
public class AccountController {

	@Autowired
	private AccountService as;

	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public ResponseEntity<Long> createAccount(@RequestBody AccountBean ab) {
		ResponseEntity<Long> re = null;
		Long id = as.createAccount(ab);
		if (id != 0) {
			re = new ResponseEntity<Long>(id, HttpStatus.OK);
		} else {
			re = new ResponseEntity<Long>(id, HttpStatus.OK);
		}
		return re;

	}

	@RequestMapping(value = "/deleteAccount/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> deleteAccount(@PathVariable("id") long id) {
		ResponseEntity<Boolean> re = null;
		boolean result = as.deleteAccount(id);
		if (result) {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/viewByAccountId/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccountBean> viewByAccountId(@PathVariable("id") long id) {
		ResponseEntity<AccountBean> re = null;
		AccountBean result = as.viewByAccountId(id);
		if (result != null) {
			re = new ResponseEntity<AccountBean>(result, HttpStatus.OK);
		} else {
			re = new ResponseEntity<AccountBean>(result, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/viewByCustomerId/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<AccountBean>> viewByCustomerId(@PathVariable("id") long id) {
		ResponseEntity<ArrayList<AccountBean>> re = null;
		ArrayList<AccountBean> result = new ArrayList<AccountBean>();
		result = as.viewByCustomerId(id);
		if (result != null) {
			re = new ResponseEntity<ArrayList<AccountBean>>(result, HttpStatus.OK);
		} else {
			re = new ResponseEntity<ArrayList<AccountBean>>(result, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/viewAllAccount", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<AccountBean>> viewAllAccount() {
		ResponseEntity<ArrayList<AccountBean>> re = null;
		ArrayList<AccountBean> accountList = new ArrayList<AccountBean>();
		accountList = as.viewAllAccount();
		if (accountList != null) {
			re = new ResponseEntity<ArrayList<AccountBean>>(accountList, HttpStatus.OK);
		} else {
			re = new ResponseEntity<ArrayList<AccountBean>>(accountList, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/checkAccount/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkAccount(@PathVariable("id") long id) {
		ResponseEntity<Boolean> re = null;
		boolean result = as.checkAccount(id);
		if (result) {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/checkByCustomerID/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkByCustomerID(@PathVariable("id") long id) {
		ResponseEntity<Boolean> re = null;
		boolean result = as.checkByCustomerID(id);
		if (result) {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/isPossible/{id}/{amt}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> isPossible(@PathVariable("id") long id, @PathVariable("amt") double amt) {
		ResponseEntity<Boolean> re = null;
		boolean result = as.isPossible(id, amt);
		if (result) {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/updateAccBal/{id}/{amt}/{status}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> updateAccBal(@PathVariable("id") long id, @PathVariable("amt") double amt,
			@PathVariable("status") String status) {
		ResponseEntity<Boolean> re = null;
		boolean result = as.updateAccBal(id, amt, status);
		if (result) {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} else {
			re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/getAccId/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Long>> getAccId(@PathVariable("id") long id) {
		ResponseEntity<ArrayList<Long>> re = null;
		ArrayList<Long> result = as.getAccId(id);
		re = new ResponseEntity<ArrayList<Long>>(result, HttpStatus.OK);
		return re;
	}

	@RequestMapping(value = "/getBalance/{id}")
	public ResponseEntity<Double> getBalance(@PathVariable("id") long accId) {
		ResponseEntity<Double> re = null;
		AccountBean account = as.viewByAccountId(accId);
		re = new ResponseEntity<Double>(account.getBalance(), HttpStatus.OK);
		return re;
	}
}
