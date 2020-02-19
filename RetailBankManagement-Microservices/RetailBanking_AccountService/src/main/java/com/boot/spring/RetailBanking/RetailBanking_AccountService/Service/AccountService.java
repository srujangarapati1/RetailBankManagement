package com.boot.spring.RetailBanking.RetailBanking_AccountService.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boot.spring.RetailBanking.RetailBanking_AccountService.Bean.AccountBean;
import com.boot.spring.RetailBanking.RetailBanking_AccountService.Repo.AccountRepo;

@Component
public class AccountService {

	@Autowired
	private AccountRepo ar;

	@Autowired
	private DiscoveryClient dc;

	// check if the customer is valid or not
	public boolean checkCustomer(long id) {
		ResponseEntity<Boolean> response1 = null;
		// if proxy is there
//		System.setProperty("http.proxyHost", ""); 
//		System.setProperty("http.proxyPort", "");
		RestTemplate restTemplate = new RestTemplate();
		// String url1="http://localhost:7011/checkProductEntry/"+id;

		List<ServiceInstance> instances = dc.getInstances("retail_banking");
		ServiceInstance si = instances.get(0);

		String url1 = si.getUri().toString();
		url1 = url1 + "/customer/checkCustomer/" + id;
		try {
			response1 = restTemplate.getForEntity(url1, Boolean.class);
		} catch (Exception e) {
			response1 = new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		if (response1.getBody() == null)
			return false;
		else
			return response1.getBody();
	}

	// creates a new account
	public Long createAccount(AccountBean ab) {

		AccountBean account = null;
		boolean b = checkCustomer(ab.getCustomerId());
		long id = 0;
		long custid = ab.getCustomerId();
		if (b) {
			ArrayList<String> list = new ArrayList<>();
			list = ar.checkAccountType(custid);
			if (list.size() == 2) {
				return id;
			} else if (list.size() == 1) {
				String type = list.get(0);
				if (ab.getAccountType().equals(type)) {
					return id;
				} else {
					account = ar.save(ab);
					id = account.getAccountId();
					return id;
				}
			} else {
				account = ar.save(ab);
				id = account.getAccountId();
				return id;
			}
		} else {
			id = -1;
			return id;
		}
	}

	// delete the particular account
	public boolean deleteAccount(long id) {

		if (ar.findById(id).isPresent()) {
			if (ar.findById(id).get().getBalance() == 0) {
				ResponseEntity<Boolean> response1 = null;
				// if proxy is there
//				System.setProperty("http.proxyHost", "");
//				System.setProperty("http.proxyPort", "");
				RestTemplate restTemplate = new RestTemplate();
				// String url1="http://localhost:7011/checkProductEntry/"+id;

				List<ServiceInstance> instances = dc.getInstances("retail_banking");
				ServiceInstance si = instances.get(0);

				String url1 = si.getUri().toString();
				url1 = url1 + "/transaction/deleteTransactions/" + id;
				try {
					response1 = restTemplate.getForEntity(url1, Boolean.class);
				} catch (Exception e) {
					response1 = new ResponseEntity<Boolean>(false, HttpStatus.OK);
				}
				ar.deleteById(id);
				return true;
			}
		}

		return false;
	}

	// display the account details by account ID
	public AccountBean viewByAccountId(long id) {

		if (ar.findById(id).isPresent()) {
			AccountBean ab = ar.findById(id).get();
			return ab;
		}
		return null;
	}

	// checks if the account exists or not
	public boolean checkAccount(long id) {

		if (ar.findById(id).isPresent()) {
			return true;
		}
		return false;
	}

	// view all account details
	public ArrayList<AccountBean> viewAllAccount() {

		ArrayList<AccountBean> accountList = new ArrayList<AccountBean>();
		accountList = (ArrayList<AccountBean>) ar.findAll();
		if (accountList.size() > 0) {
			return accountList;
		}
		return null;
	}

	// view account details by customer id
	public ArrayList<AccountBean> viewByCustomerId(long id) {

		ArrayList<AccountBean> cusAccList = new ArrayList<AccountBean>();
		cusAccList = ar.viewByCustomer(id);
		if (cusAccList.size() > 0) {
			return cusAccList;
		}
		return null;
	}

	public boolean isPossible(long id, double amt) {

		double balance = ar.checkAmount(id);
		if (balance >= amt) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkByCustomerID(long id) {
		ArrayList<String> res = null;
		res = ar.checkAccountType(id);
		if (res.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean updateAccBal(long id, double amt, String status) {

		AccountBean ab = viewByAccountId(id);
		if (ab != null) {
			if (status.equals("credited")) {
				ab.setBalance(ab.getBalance() + amt);
				ar.save(ab);
				return true;
			} else if (status.equals("debited")) {
				ab.setBalance(ab.getBalance() - amt);
				ar.save(ab);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public ArrayList<Long> getAccId(long id) {
		ArrayList<Long> accountId = new ArrayList<>();
		accountId = ar.getAccountId(id);
		return accountId;
	}

}
