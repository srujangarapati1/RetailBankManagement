package com.boot.spring.RetailBanking.RetailBanking_CustomerService.Controller;

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

import com.boot.spring.RetailBanking.RetailBanking_CustomerService.Bean.Customer;
import com.boot.spring.RetailBanking.RetailBanking_CustomerService.Service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService service;

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public ResponseEntity<Long> addCustomer(@RequestBody Customer c) {
		ResponseEntity<Long> rs = null;
		long b = 0;
		b = service.addCustomer(c);
		if (b != 0) {
			rs = new ResponseEntity<Long>(b, HttpStatus.OK);
		}
		return rs;

	}

	@RequestMapping(value = "/checkCustomerBySSN/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCustomerBySSN(@PathVariable("id") long id) {
		ResponseEntity<Boolean> rs = null;
		boolean b = false;
		b = service.checkBySSN(id);
		if (b) {
			rs = new ResponseEntity<Boolean>(b, HttpStatus.OK);
		} else {
			rs = new ResponseEntity<Boolean>(b, HttpStatus.OK);
		}
		return rs;
	}

	@RequestMapping(value = "/checkCustomer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCustomer(@PathVariable("id") long id) {

		ResponseEntity<Boolean> rs = null;
		boolean b = false;
		b = service.checkCustomer(id);
		if (b) {
			rs = new ResponseEntity<Boolean>(b, HttpStatus.OK);
		} else {
			rs = new ResponseEntity<Boolean>(b, HttpStatus.OK);
		}
		return rs;

	}

	@RequestMapping(value = "/findCustomer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> findCustomerBySSN(@PathVariable("id") long id) {
		ResponseEntity<Customer> re = null;
		Customer c = null;
		c = service.viewBySSN(id);
		if (c != null) {
			re = new ResponseEntity<Customer>(c, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> findCustomerByName(@PathVariable("name") String Name) {
		ResponseEntity<ArrayList<Customer>> rs = null;
		ArrayList<Customer> cList = null;
		cList = service.viewByName(Name);
		if (cList.size() != 0) {
			rs = new ResponseEntity<ArrayList<Customer>>(cList, HttpStatus.OK);
		}
		return rs;
	}

	@RequestMapping(value = "/findByCity/{city}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> findCustomerByCity(@PathVariable("city") String City) {
		ResponseEntity<ArrayList<Customer>> rs = null;
		ArrayList<Customer> cList = null;
		cList = service.viewByCity(City);
		if (cList.size() != 0) {
			rs = new ResponseEntity<ArrayList<Customer>>(cList, HttpStatus.OK);
		}
		return rs;
	}

	@RequestMapping(value = "/findByNumber/{mobile}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> findCustomerByMobile(@PathVariable("mobile") long No) {
		ResponseEntity<ArrayList<Customer>> rs = null;
		ArrayList<Customer> cList = null;
		cList = service.viewByMobile(No);
		if (cList.size() != 0) {
			rs = new ResponseEntity<ArrayList<Customer>>(cList, HttpStatus.OK);
		}
		return rs;
	}

	@RequestMapping(value = "/findByMail/{mail}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> findCustomerByMail(@PathVariable("mail") String Email) {
		ResponseEntity<ArrayList<Customer>> rs = null;
		ArrayList<Customer> cList = null;
		cList = service.viewByGmail(Email);
		if (cList.size() != 0) {
			rs = new ResponseEntity<ArrayList<Customer>>(cList, HttpStatus.OK);
		}
		return rs;
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Customer>> findAllCustomers() {
		ResponseEntity<ArrayList<Customer>> rs = null;
		ArrayList<Customer> cList = null;
		cList = service.viewAll();
		if (cList.size() != 0) {
			rs = new ResponseEntity<ArrayList<Customer>>(cList, HttpStatus.OK);
		}
		return rs;
	}

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public ResponseEntity<Boolean> updateCustomer(@RequestBody Customer c) {
		ResponseEntity<Boolean> rs = null;
		boolean b = false;
		b = service.updateCustomer(c);
		if (b) {
			rs = new ResponseEntity<Boolean>(b, HttpStatus.OK);
		}
		return rs;
	}

	@RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.GET)
	public ResponseEntity<Boolean> deleteCustomer(@PathVariable("id") long id) {
		ResponseEntity<Boolean> rs = null;
		boolean deleteResult = false;
		deleteResult = service.deleteCustomer(id);
		if (deleteResult) {
			rs = new ResponseEntity<Boolean>(deleteResult, HttpStatus.OK);
		} else {
			rs = new ResponseEntity<Boolean>(deleteResult, HttpStatus.OK);
		}
		return rs;

	}

}
