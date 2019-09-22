package com.ge.exercise3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class Bank {

    private static final Logger logger = LogManager.getLogger(Bank.class);
    private Map<String, Account> accountMap;

    public Bank() {
        accountMap = new HashMap<>();
    }

    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }

    public void addAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public void depositToAccount(String accountNumber, float amount) {
        getAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(String accountNumber, float amount) {
        getAccount(accountNumber).withdraw(amount);
    }

    public int numAccounts() {
        return accountMap.size();
    }
    
    /*
     * Find sum of balances of Checking account type
     */
    public double GetSumOfCurrentAccounts()
    {
    	Iterator iterator = accountMap.entrySet().iterator();
    	double sum = 0;
    	while(iterator.hasNext())
    	{
    		Map.Entry mapEle =  (Map.Entry) iterator.next();
    		Account account = (Account)mapEle.getValue();
    		if(account.getAccountType() == "Checking")
    		{
    			sum += account.getBalance();
    		}
    	}
    	
    	return sum;
    }
    
    
    /*If total fees collected < Intrest paid, bank is in loss
     * else in profit.
     */
    public String ProfitOrLoss()
    {
    	Iterator iterator = accountMap.entrySet().iterator();
    	String ProfitString = "No Profit or Loss";
    	double intrestPaid = 0;
    	double feesCollected = 0;
    	while(iterator.hasNext())
    	{
    		Map.Entry mapEle =  (Map.Entry) iterator.next();
    		Account account = (Account)mapEle.getValue();
    		feesCollected += account.getMonthlyFee();
    		
    		double balance = account.getBalance();
    		double intrestRate = account.getMonthlyInterestRate();
    		intrestPaid += (balance * intrestRate) - balance;
    	}
    	
    	if(intrestPaid > feesCollected)
    	{
    		ProfitString = "Loss";
    	}
    	
    	else if(intrestPaid < feesCollected)
    	{
    		ProfitString = "Profit";
    	}
    	
    	return ProfitString;
    }
}
