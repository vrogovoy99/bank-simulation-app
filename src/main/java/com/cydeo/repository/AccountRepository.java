package com.cydeo.repository;

import com.cydeo.model.Account;

import java.util.List;

public class AccountRepository {
public Account save(Account account){
    System.out.println("Account saved");
    return account;
}

public List<Account> findAll(){
    return null;
}

}
