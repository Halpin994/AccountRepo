package com.qa.service;

import org.junit.Assert;
import org.junit.Test;

import com.qa.domain.Account;

public class ServiceTest {
    @Test
    public void countAccountsWithGivenFirstNameTest() throws Exception {
        String firstName = "Johnny";
        Service service = new Service();

        Assert.assertEquals(0, service.countAccounts(firstName));

        Account account1 = new Account("John", "Jacob", "12345");
        Account account2 = new Account("Johnny", "Bravo", "12346");
        Account account3 = new Account("Johnny", "Vamos", "12347");
        service.addAccount(account1);
        service.addAccount(account2);
        service.addAccount(account3);

        Assert.assertEquals(2, service.countAccounts(firstName));
    }
}