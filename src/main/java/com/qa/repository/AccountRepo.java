package com.qa.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountRepo implements IAccountRepository{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	public String getAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}
	
	@Override
	@Transactional(REQUIRED)
	public String addAccount(String jsonAccount) {
		Account anAccount = util.getObjectForJSON(jsonAccount, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteAccount(String accountNumber) {
		Account accountInDB = findAccount(accountNumber);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	@Transactional(REQUIRED)
	public String updateAccount(String jsonAccount) {
		Account account = util.getObjectForJSON(jsonAccount, Account.class);	
		Account accountInDB = findAccount(account.getAccountNumber());
		deleteAccount(account.getAccountNumber());
		addAccount(jsonAccount);
		
		return "{\"message\": \"account has been sucessfully updated\"}";
	}
	
	@Transactional(SUPPORTS)
	public String getAccount(String accountNumber) {
		Account anAccount =  manager.find(Account.class, accountNumber);
		return util.getJSONForObject(anAccount);
	}

	private Account findAccount(String accountNumber) {
		return manager.find(Account.class, accountNumber);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
 