package com.factory;

import com.dao.BankDaoImplementation;
import com.dao.Cashierdao;
import com.dao.CashierdaoInterface;
import com.dao.DAO;
import com.dao.Executivedao;
import com.dao.ExecutivedaoInterface;
import com.service.Service;
import com.service.ServiceImplementation;

public class BankFactory {
public static Service getServiceInstance(){
	return new ServiceImplementation();
}
public static DAO getDaoInstance(){
	return new BankDaoImplementation();
}
public static CashierdaoInterface CashierdaoInstance(){
	return new Cashierdao();
}
public static ExecutivedaoInterface ExecutiveInstance(){
	return new Executivedao();
}
}
