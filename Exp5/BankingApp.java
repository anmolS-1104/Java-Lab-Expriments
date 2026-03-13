package Exp5;

class Account {
    protected String accountNumber;
    protected String accountHolder;
    protected double balance;

    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Using 'throws' to indicate this method might fail
    public void deposit(double amount) throws BankingException {
        if (amount <= 0) {
            throw new BankingException("Invalid Transaction: Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Successfully deposited: $" + amount);
    }

    // Using 'throws' for withdrawal logic
    public void withdraw(double amount) throws BankingException {
        if (amount > balance) {
            throw new BankingException("Insufficient Funds: Current balance is only $" + balance);
        }
        balance -= amount;
        System.out.println("Successfully withdrew: $" + amount);
    }

    public void displayDetails() {
        System.out.println("\n[Account: " + accountNumber + " | Holder: " + accountHolder + " | Balance: $" + balance + "]");
    }
}

// Derived Class: Savings
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accNum, String name, double bal, double rate) {
        super(accNum, name, bal);
        this.interestRate = rate;
    }

    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Applied " + interestRate + "% interest: +$" + interest);
    }
}

// Main Execution
public class BankingApp {
    public static void main(String[] args) {
        // Create object using Base Class reference
        Account mySavings = new SavingsAccount("SAV-101", "Alex Smith", 1000.0, 3.5);

        mySavings.displayDetails();

        try {
            // Operation 1: Normal Withdrawal
            System.out.println("Action: Withdrawing $200...");
            mySavings.withdraw(200.0);

            // Operation 2: Invalid Deposit (Triggers Exception)
            System.out.println("Action: Depositing -$50...");
            mySavings.deposit(-50.0);

        } catch (BankingException e) {
            // Catching the exception you defined
            System.err.println("BANKING ERROR: " + e.getMessage());
        }

        try {
            // Operation 3: Over-withdrawal (Triggers Exception)
            System.out.println("\nAction: Withdrawing $2000...");
            mySavings.withdraw(2000.0);
        } catch (BankingException e) {
            System.err.println("BANKING ERROR: " + e.getMessage());
        }

        mySavings.displayDetails();
    }
}