package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import com.qa.domain.Account;

public class Service {
    private Map<String, Account> accounts;

    public Service() {
        this.accounts = new HashMap<>();
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public Account addAccount(Account account) {
        return accounts.put(account.getAccountNumber(), account);
    }

    public Account deleteAccount(String accountNumber) {
        return accounts.remove(accountNumber);
    }
    
    public Account retrieveAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
    
    public int countAccounts(final String firstName) {
        return (int) accounts.values().stream().filter(s -> s.getFirstName().equals(firstName)).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Service service = (Service) o;

        return accounts != null ? accounts.equals(service.accounts) : service.accounts == null;
    }

    @Override
    public int hashCode() {
        return accounts != null ? accounts.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Service{" +
                "accounts=" + accounts +
                '}';
    }
}
