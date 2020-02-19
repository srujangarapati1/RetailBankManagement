package com.boot.spring.RetailBanking.RetailBanking_TransactionService.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boot.spring.RetailBanking.RetailBanking_TransactionService.Repo.TransactionRepo;
import com.boot.spring.RetailBanking.RetailBanking_TransactionService.bean.DWdetails;
import com.boot.spring.RetailBanking.RetailBanking_TransactionService.bean.Transaction;

@Component
public class TransactionService {

	@Autowired
	private TransactionRepo tr;

	@Autowired
	private DiscoveryClient discoveryClient;

	public boolean check(long accId) {
		ResponseEntity<Boolean> response1;
		// if proxy is there
//		System.setProperty("http.proxyHost", "");
//		System.setProperty("http.proxyPort", "");
		RestTemplate restTemplate = new RestTemplate();

		List<ServiceInstance> instances = discoveryClient.getInstances("retail_banking");
		ServiceInstance serviceInstance = instances.get(0);

		String url = serviceInstance.getUri().toString();
		url = url + "/account/checkAccount/" + accId;

		try {
			response1 = restTemplate.getForEntity(url, Boolean.class);
		} catch (Exception ex) {
			response1 = new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		if (response1.getBody() == null)
			return false;
		return (response1.getBody());
	}

	public double getBalance(long accId) {
		ResponseEntity<Double> response1;
		//if proxy is there
//		System.setProperty("http.proxyHost", "");
//		System.setProperty("http.proxyPort", "");
		RestTemplate restTemplate = new RestTemplate();

		List<ServiceInstance> instances = discoveryClient.getInstances("retail_banking");
		ServiceInstance serviceInstance = instances.get(0);

		String url = serviceInstance.getUri().toString();
		url = url + "/account/getBalance/" + accId;

		try {
			response1 = restTemplate.getForEntity(url, Double.class);
		} catch (Exception ex) {
			response1 = new ResponseEntity<Double>(0.0, HttpStatus.OK);
		}
		if (response1.getBody() == null)
			return 0.0;
		return (response1.getBody());
	}

	public DWdetails deposit(long accId, double amnt) {
		DWdetails details = new DWdetails();
		details.setAccountId(accId);
		details.setAmount(amnt);
		details.setProviousAmount(getBalance(accId));
		boolean b = updateAccBal(accId, amnt, "credited");
		if (b) {
			Calendar calender = Calendar.getInstance();
			Timestamp timesamp = new Timestamp(calender.getTime().getTime());
			Transaction t1 = null;
			Transaction t = new Transaction();
			t.setAccId(accId);
			t.setAmount(amnt);
			t.setStatus("credited");
			t.setTimeStamp(timesamp.toString());
			t1 = tr.save(t);
			if (t1 != null) {
				details.setPresentAmount(getBalance(accId));
				return details;
			}
			return details;
		}
		return details;
	}

	public boolean updateAccBal(long accId, double amnt, String status) {
		ResponseEntity<Boolean> response1;
		// if proxy is there
//		System.setProperty("http.proxyHost", "");
//		System.setProperty("http.proxyPort", "");
		RestTemplate restTemplate = new RestTemplate();

		List<ServiceInstance> instances = discoveryClient.getInstances("retail_banking");
		ServiceInstance serviceInstance = instances.get(0);

		String url = serviceInstance.getUri().toString();
		url = url + "/account/updateAccBal/" + accId + "/" + amnt + "/" + status;

		try {
			response1 = restTemplate.getForEntity(url, Boolean.class);
		} catch (Exception ex) {
			response1 = new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		if (response1.getBody() == null)
			return false;
		return (response1.getBody());
	}

	public DWdetails withdraw(long accId, double amnt) {
		DWdetails details = new DWdetails();
		details.setAccountId(accId);
		boolean b = isPossible(accId, amnt);
		if (b) {
			details.setProviousAmount(getBalance(accId));
			boolean u = updateAccBal(accId, amnt, "debited");
			if (u) {
				details.setAmount(amnt);

				Calendar calender = Calendar.getInstance();
				Timestamp timesamp = new Timestamp(calender.getTime().getTime());
				Transaction t1 = null;
				Transaction t = new Transaction();
				t.setAccId(accId);
				t.setAmount(amnt);
				t.setStatus("debited");
				t.setTimeStamp(timesamp.toString());
				t1 = tr.save(t);
				if (t1 != null) {
					details.setPresentAmount(getBalance(accId));
					return details;
				}
				return details;
			}
		}
		return details;
	}

	public boolean isPossible(long accId, double amnt) {
		ResponseEntity<Boolean> response1;
		//if proxy is there
//		System.setProperty("http.proxyHost", "");
//		System.setProperty("http.proxyPort", "");
		RestTemplate restTemplate = new RestTemplate();

		List<ServiceInstance> instances = discoveryClient.getInstances("retail_banking");
		ServiceInstance serviceInstance = instances.get(0);

		String url = serviceInstance.getUri().toString();
		url = url + "/account/isPossible/" + accId + "/" + amnt;

		try {
			response1 = restTemplate.getForEntity(url, Boolean.class);
		} catch (Exception ex) {
			response1 = new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		if (response1.getBody() == null)
			return false;
		return (response1.getBody());
	}

	public ArrayList<Transaction> viewByAccId(long accId) {
		ArrayList<Transaction> al = tr.findByAccId(accId);
		return al;
	}

	public ArrayList<Transaction> viewByCustId(long custId) {
		ArrayList<Transaction> transactions = new ArrayList<>();
		Long[] accId = getAccId(custId);
		for (long id : accId) {
			transactions.addAll(tr.findByAccId(id));
		}

		return transactions;
	}

	public Long[] getAccId(long custId) {
		ResponseEntity<Long[]> response1;
		//if proxy is there
//		System.setProperty("http.proxyHost", "");
//		System.setProperty("http.proxyPort", "");
		RestTemplate restTemplate = new RestTemplate();

		List<ServiceInstance> instances = discoveryClient.getInstances("retail_banking");
		ServiceInstance serviceInstance = instances.get(0);

		String url = serviceInstance.getUri().toString();
		url = url + "/account/getAccId/" + custId;

		response1 = restTemplate.getForEntity(url, Long[].class);
		return (response1.getBody());

	}

	public boolean deleteTransactions(long accId) {

		// TODO Auto-generated method stub
		ArrayList<Transaction> al = tr.findByAccId(accId);
		for (Transaction t : al) {
			tr.deleteById(t.getTransId());
		}
		return true;
	}

	public ArrayList<Transaction> viewAllTransaction(String from, String to) {
		// TODO Auto-generated method stub
		ArrayList<Transaction> list = tr.viewAllTransaction(from, to);
		return list;
	}

}
