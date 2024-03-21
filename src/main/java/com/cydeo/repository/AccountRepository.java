package com.cydeo.repository;

import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class AccountRepository {
    public static List<Account> accountList = new ArrayList<>();

    public static Account findById(UUID id) {
        //find and return account if it is in database otherwise throw an error.
        return accountList.stream()
                .filter(account -> account.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RecordNotFoundException("Account does not exist in the database."));
    }

    public Account save(Account account){
        accountList.add(account);
        return account;
    }

    public List<Account> findAll(){
        return accountList;
    }

}
