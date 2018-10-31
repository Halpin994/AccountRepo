package com.qa.app;

import com.qa.domain.Account;
import com.qa.service.Service;

public class App {

    public static void main(String[] args) {

        Account account = new Account("Johnny", "Bravo", "123456");
        Account account2 = new Account("John", "Bravo", "789");

        Service service = new Service();
        service.addAccount(account);
        //print maps
        System.out.println(service.getAccounts());
        
        service.addAccount(account2);
        
        //print specific accs by id
        System.out.println(service.retrieveAccount(account.getAccountNumber()));
        System.out.println(service.retrieveAccount("789"));
        
        service.deleteAccount(account.getAccountNumber());
        System.out.println(service.getAccounts());
    }
}
