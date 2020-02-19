package com.boot.spring.RetailBanking.RetailBanking_AccountService.Repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boot.spring.RetailBanking.RetailBanking_AccountService.Bean.AccountBean;

@Repository
public interface AccountRepo extends CrudRepository<AccountBean, Long> {

	@Query("select a from AccountBean a where a.customerId=?1")
	ArrayList<AccountBean> viewByCustomer(long id);

	@Query("select a.accountType from AccountBean a where a.customerId=?1")
	ArrayList<String> checkAccountType(long id);

	@Query("select a.balance from AccountBean a where a.accountId=?1")
	Double checkAmount(long id);

	@Query("select a.accountId from AccountBean a where a.customerId=?1")
	ArrayList<Long> getAccountId(long id);

}
