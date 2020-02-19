package com.boot.spring.RetailBanking.RetailBanking_TransactionService.Repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.spring.RetailBanking.RetailBanking_TransactionService.bean.Transaction;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, Long> {

	@Query("select t from Transaction t where t.accId = ?1")
	ArrayList<Transaction> findByAccId(long accId);

	@Query("select t from Transaction t where t.timeStamp between ?1 and ?2")
	ArrayList<Transaction> viewAllTransaction(String from, String to);
}
