package com.qa.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Alternative
public class AccountRepoOffline implements IAccountRepository {

    private Map<String, Account> accounts = new HashMap<>();

    @Inject
    private JSONUtil jsonConverter;

    @Override
    public String addAccount(String acc) {
        Account account = jsonConverter.getObjectForJSON(acc, Account.class);
        accounts.put(account.getAccountNumber(), account);
        return "{\"message\": \"account has been successfully added\"}";
    }

    @Override
    public String updateAccount(String acc) {
        Account account = jsonConverter.getObjectForJSON(acc, Account.class);
        accounts.replace(account.getAccountNumber(), account);
        return "{\"message\": \"account has been sucessfully updated\"}";
    }

    @Override
    public String deleteAccount(String accountNumber) {
        accounts.remove(accountNumber);
        return "{\"message\": \"account sucessfully deleted\"}";
    }

    @Override
    public String getAccount(String accountNumber) {
        return jsonConverter.getJSONForObject(accounts.get(accountNumber));
    }

    @Override
    public String getAccounts() {
        Collection<Account> values = accounts.values();
        return jsonConverter.getJSONForObject(values);
    }

}
