package com.boot.spring.RetailBanking.RetailBanking_CustomerService.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.boot.spring.RetailBanking.RetailBanking_CustomerService.Bean.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Query("select c.customerId from Customer c where c.ssnId =?1")
	long checkBySSN(long sid);

	@Query("select c from Customer c where c.ssnId =?1")
	Customer viewBySSN(long id);

	@Query("select c from Customer c where c.customerName = ?1")
	ArrayList<Customer> viewByName(String Name);

	@Query("select c from Customer c where c.mobile = ?1")
	ArrayList<Customer> viewByMobile(long ph);

	@Query("select c from Customer c where c.gmail = ?1")
	ArrayList<Customer> viewByGmail(String gmail);

	@Query("select c from Customer c where c.city = ?1")
	ArrayList<Customer> viewByCity(String City);

}
