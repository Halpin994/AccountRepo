package com.qa.repository;

import com.qa.domain.Account;

public interface IAccountRepository {

	String addAccount(String account);

    String updateAccount(String account);

    String deleteAccount(String accountNumber);

    String getAccount(String accountNumber);

    String getAccounts();
	
}
