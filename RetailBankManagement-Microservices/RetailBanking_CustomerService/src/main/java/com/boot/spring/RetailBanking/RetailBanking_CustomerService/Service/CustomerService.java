package com.boot.spring.RetailBanking.RetailBanking_CustomerService.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boot.spring.RetailBanking.RetailBanking_CustomerService.Bean.Customer;
import com.boot.spring.RetailBanking.RetailBanking_CustomerService.Repository.CustomerRepository;

@Component
public class CustomerService {

	@Autowired
	private CustomerRepository reposit;

	@Autowired
	private DiscoveryClient dc;

	// Add Customer

	public long addCustomer(Customer c) {
		long result = 0;
		Customer c1 = null;
		c1 = reposit.save(c);
		if (c1 != null) {
			result = c1.getCustomerId();
		}
		return result;
	}

	// Check Customer By SSN

	public boolean checkBySSN(long id) {
		boolean result = false;
		long acId = 0;
		acId = reposit.checkBySSN(id);
		if (acId != 0) {
			result = true;
		}
		return result;
	}

	// View Customer By SSN ID

	public Customer viewBySSN(long id) {
		Customer c = null;
		c = reposit.viewBySSN(id);
		return c;
	}

	// View Customers By Customer Name

	public ArrayList<Customer> viewByName(String Name) {
		ArrayList<Customer> cList = null;
		cList = reposit.viewByName(Name);
		return cList;
	}

	// View Customers By Mobile Number

	public ArrayList<Customer> viewByMobile(long Mobile) {
		ArrayList<Customer> cList = null;
		cList = reposit.viewByMobile(Mobile);
		return cList;
	}

	// View Customers By Gmail

	public ArrayList<Customer> viewByGmail(String Gmail) {
		ArrayList<Customer> cList = null;
		cList = reposit.viewByGmail(Gmail);
		return cList;
	}

	// View Customers By City

	public ArrayList<Customer> viewByCity(String City) {
		ArrayList<Customer> cList = null;
		cList = reposit.viewByCity(City);
		return cList;
	}

	// View All Customers

	public ArrayList<Customer> viewAll() {
		ArrayList<Customer> cList = null;
		cList = (ArrayList<Customer>) reposit.findAll();
		return cList;
	}

	// Update Customer

	public boolean updateCustomer(Customer c) {
		boolean result = false;
		Customer s = new Customer();
		s = reposit.save(c);
		if (s != null) {
			result = true;
		}
		return result;
	}

	// Check Customer By Customer ID

	public boolean checkCustomer(long id) {
		boolean result = false;
		result = reposit.existsById(id);
		return result;
	}

	public boolean checkAccount(long id) {
		ResponseEntity<Boolean> response1 = null;
		// if proxy is there
//		System.setProperty("http.proxyHost", "");
//		System.setProperty("http.proxyPort", "");
		RestTemplate restTemplate = new RestTemplate();
		// String url1="http://localhost:7011/checkProductEntry/"+id;

		List<ServiceInstance> instances = dc.getInstances("retail_banking");
		ServiceInstance si = instances.get(0);

		String url1 = si.getUri().toString();
		url1 = url1 + "/account/checkByCustomerID/" + id;
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
	// Delete Customer

	public boolean deleteCustomer(long ssnId) {
		boolean accountResult = false;
		Customer c = viewBySSN(ssnId);
		if (c != null) {
			long cusId = c.getCustomerId();
			accountResult = checkAccount(cusId);
			if (!accountResult) {
				reposit.deleteById(cusId);
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}
}
