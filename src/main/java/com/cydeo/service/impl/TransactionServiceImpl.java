package com.cydeo.service.impl;

import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnershipException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    public TransactionServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date createDate, String message) {

        // is sender or receiver is null
        // is sender and receiver the same account
        // does sender have sufficient balance
        // are both accounts checking OR they belong to the same user
        validateAccount(sender, receiver);
        checkAccountOwnership(sender,receiver);

        //make transfer
        return null;
    }

    private void checkAccountOwnership(Account sender, Account receiver) {
        // check if accounts are checking OR belong to the same owner. Otherwice throw AccountOwnershipException
        if((sender.getAccountType().equals(AccountType.SAVINGS) || receiver.getAccountType().equals(AccountType.SAVINGS))
        && !sender.getUserId().equals(receiver.getUserId())){
            throw new AccountOwnershipException("Both accounts have to be checking or belong to the same user");
        }
    }

    private void validateAccount(Account sender, Account receiver) {
        // is sender or receiver is null
        if(sender==null||receiver==null){
            throw new BadRequestException("Neither sender or receiver accounts can be null");
        }
        // are accounts the same
        if(sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Can not transact from-into the same account");
        }
        // do accounts exist in repository
        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(UUID id) {
        AccountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return null;
    }
}
