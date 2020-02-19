package com.boot.spring.RetailBanking.RetailBanking_TransactionService.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.spring.RetailBanking.RetailBanking_TransactionService.bean.DWdetails;
import com.boot.spring.RetailBanking.RetailBanking_TransactionService.bean.Transaction;
import com.boot.spring.RetailBanking.RetailBanking_TransactionService.service.TransactionService;

@RestController
@CrossOrigin
public class TransactionController {

	@Autowired
	private TransactionService ts;

	@RequestMapping(value = "/deposit/{accId}/{amnt}", method = RequestMethod.POST)
	public ResponseEntity<DWdetails> deposit(@PathVariable("accId") long accId, @PathVariable("amnt") double amnt) {
		ResponseEntity<DWdetails> re = null;
		boolean b = ts.check(accId);
		if (b) {
			DWdetails result = ts.deposit(accId, amnt);
			re = new ResponseEntity<DWdetails>(result, HttpStatus.OK);
		}

		return re;
	}

	@RequestMapping(value = "/Withdraw/{accId}/{amnt}", method = RequestMethod.POST)
	public ResponseEntity<DWdetails> Withdraw(@PathVariable("accId") long accId, @PathVariable("amnt") double amnt) {
		ResponseEntity<DWdetails> re = null;
		boolean b = ts.check(accId);
		if (b) {
			DWdetails result = ts.withdraw(accId, amnt);
			re = new ResponseEntity<DWdetails>(result, HttpStatus.OK);
		}

		return re;
	}

	@RequestMapping(value = "/viewByAccId/{accId}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Transaction>> viewByAccId(@PathVariable("accId") long accId) {
		ResponseEntity<ArrayList<Transaction>> re = null;
		ArrayList<Transaction> al = null;
		boolean b = ts.check(accId);
		if (b) {
			al = ts.viewByAccId(accId);
			if (al.size() > 0)
				re = new ResponseEntity<ArrayList<Transaction>>(al, HttpStatus.OK);
			else
				re = new ResponseEntity<ArrayList<Transaction>>(al, HttpStatus.OK);

		} else {
			re = new ResponseEntity<ArrayList<Transaction>>(al, HttpStatus.OK);
		}
		return re;
	}

	@RequestMapping(value = "/viewByCustId/{custId}", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Transaction>> viewByCustIdId(@PathVariable("custId") long custId) {
		ResponseEntity<ArrayList<Transaction>> re = null;
		ArrayList<Transaction> al = ts.viewByCustId(custId);
		if (al.size() > 0)
			re = new ResponseEntity<ArrayList<Transaction>>(al, HttpStatus.OK);
		else
			re = new ResponseEntity<ArrayList<Transaction>>(al, HttpStatus.OK);
		return re;
	}

	@RequestMapping(value = "/deleteTransactions/{id}")
	public ResponseEntity<Boolean> deleteTransactions(@PathVariable("id") long accId) {
		ResponseEntity<Boolean> re = null;
		boolean result = ts.deleteTransactions(accId);
		re = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		return re;
	}

	@RequestMapping(value = "/viewAllTransaction/{from}/{to}")
	public ResponseEntity<ArrayList<Transaction>> viewAllTransaction(@PathVariable("from") String from,
			@PathVariable("to") String to) {
		ResponseEntity<ArrayList<Transaction>> list = null;
		ArrayList<Transaction> Tlist = new ArrayList<>();
		Tlist = ts.viewAllTransaction(from, to);
		list = new ResponseEntity<ArrayList<Transaction>>(Tlist, HttpStatus.OK);
		return list;
	}
}
