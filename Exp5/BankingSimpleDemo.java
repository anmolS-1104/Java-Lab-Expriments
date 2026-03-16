// Save as BankingSimpleDemo.java in folder Exp5
package Exp5;
import java.util.ArrayList;

class BankingException extends Exception {
    public BankingException(String msg) { super(msg); }
}

class BankAccount {
    private final String accountNo;
    private final String holderName;
    protected double balance;

    public BankAccount(String accountNo, String holderName, double openingBalance) throws BankingException {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Withdraw amount must be positive.");
        if (amount > balance) throw new BankingException("Insufficient balance.");
        
        System.out.println("Processing Withdrawal: $" + amount + " from Generic Account...");
        balance -= amount;
        System.out.println("Success! New Balance: $" + balance);
    }

    public String summary() {
        return String.format("[%s] AccNo=%s, Holder=%s, Balance=%.2f", getAccountType(), accountNo, holderName, balance);
    }
    public String getAccountType() { return "GENERIC"; }
    public String getAccountNo() { return accountNo; }
}

class SavingsAccount extends BankAccount {
    private final double minBalance;

    public SavingsAccount(String accountNo, String holderName, double openingBalance, double minBalance, double rate) throws BankingException {
        super(accountNo, holderName, openingBalance);
        this.minBalance = minBalance;
    }

    @Override
    public String getAccountType() { return "SAVINGS"; }

    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Withdraw amount must be positive.");
        if ((balance - amount) < minBalance) {
            throw new BankingException("Denied: Withdrawal of $" + amount + " would drop balance below min limit of $" + minBalance);
        }
        System.out.println("Processing Withdrawal: $" + amount + " from Savings Account...");
        balance -= amount;
        System.out.println("Success! New Savings Balance: $" + balance);
    }
}

class CurrentAccount extends BankAccount {
    private final double overdraftLimit;

    public CurrentAccount(String accountNo, String holderName, double bal, double limit) throws BankingException {
        super(accountNo, holderName, bal);
        this.overdraftLimit = limit;
    }

    @Override
    public String getAccountType() { return "CURRENT"; }

    @Override
    public void withdraw(double amount) throws BankingException {
        if ((balance - amount) < -overdraftLimit) {
            throw new BankingException("Current Error: Withdrawal of $" + amount + " exceeds overdraft limit.");
        }
        System.out.println("Processing Withdrawal: $" + amount + " from Current Account...");
        balance -= amount;
        System.out.println("Success! Current Balance is now: $" + balance);
    }
}

public class BankingSimpleDemo {
    public static void main(String[] args) {
        try {
            BankAccount acc1 = new SavingsAccount("SA-101", "Ayaan", 5000, 1000, 4.0);
            BankAccount acc2 = new CurrentAccount("CA-201", "Isha", 2000, 3000);

            System.out.println("---- Initial State ----");
            System.out.println(acc1.summary());
            System.out.println(acc2.summary());

            System.out.println("\n---- Performing Transactions ----");
            acc1.withdraw(2000); 
            acc2.withdraw(4000); 

            System.out.println("\n---- Attempting Illegal Withdrawal ----");
            acc1.withdraw(3500); // This will trigger the exception

        } catch (BankingException ex) {
            System.out.println("\n!!! TRANSACTION BLOCKED !!!");
            System.out.println("Reason: " + ex.getMessage());
        }
    }
}























































































































