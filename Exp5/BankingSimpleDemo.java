package Exp5;
import java.util.ArrayList;

// -------------------- Base Class --------------------
abstract class BankAccount {
    protected String accNo;
    protected double balance;

    public BankAccount(String accNo, double initialBalance) {
        this.accNo = accNo;
        this.balance = initialBalance;
    }

    public void deposit(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Deposit must be positive.");
        balance += amount;
        System.out.println("Deposited $" + amount + " to " + accNo);
    }

    public void withdraw(double amount) throws BankingException{
    // ERROR CHECK: 
    if (amount <= 0) {
        throw new BankingException("Invalid Transaction: Withdrawal amount must be greater than zero.");
    }

    // Logic for balance check continues here...
    if (amount > balance) {
        throw new BankingException("Insufficient funds.");
    }
    
    balance -= amount;
    System.out.println("Withdrew: $" + amount);
}
    public abstract String getAccountType();

    public double getBalance() { return balance; }
    public String getAccNo() { return accNo; }
}

// -------------------- Child Class 1: Savings --------------------
class SavingsAccount extends BankAccount {
    private double minBalance = 500.0;

    public SavingsAccount(String accNo, double bal) { super(accNo, bal); }

    @Override
    public String getAccountType() { return "SAVINGS"; }

    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Withdraw amount must be positive.");
        if (balance - amount < minBalance) 
            throw new BankingException("Savings Error: Must maintain min balance of $" + minBalance);
        
        balance -= amount;
        System.out.println("Success: Withdrew $" + amount + " from Savings " + accNo);
    }
}

// -------------------- Child Class 2: Loan --------------------
class LoanAccount extends BankAccount {
    public LoanAccount(String accNo, double initialDebt) { 
        super(accNo, -initialDebt); // Debt is shown as negative balance
    }

    @Override
    public String getAccountType() { return "LOAN"; }

    @Override
    public void withdraw(double amount) throws BankingException {
        // Taking more loan increases the debt (makes balance more negative)
        balance -= amount;
        System.out.println("Success: Loan Disbursement of $" + amount + " to " + accNo);
    }
}

// -------------------- Customer Class --------------------
class Customer {
    private String name;
    private ArrayList<BankAccount> myAccounts = new ArrayList<>();

    public Customer(String name) { this.name = name; }

    public void addAccount(BankAccount acc) { myAccounts.add(acc); }

    // This method fixes the "cannot find symbol" error
    public BankAccount getAccount(int index) {
        if (index >= 0 && index < myAccounts.size()) {
            return myAccounts.get(index);
        }
        return null;
    }

    public void showConsolidatedReport() {
        System.out.println("\nCUSTOMER: " + name.toUpperCase());
        System.out.println("--------------------------------------------");
        double netBalance = 0;
        for (BankAccount a : myAccounts) {
            System.out.printf("%-10s (%s) | Balance: $%.2f\n", a.getAccountType(), a.getAccNo(), a.getBalance());
            netBalance += a.getBalance();
        }
        System.out.printf("NET CONSOLIDATED BALANCE: $%.2f\n", netBalance);
        System.out.println("--------------------------------------------");
    }
}

// -------------------- Main Class --------------------
public class BankingSimpleDemo {
    public static void main(String[] args) {
        try {
            ArrayList<Customer> customers = new ArrayList<>();

            // 1. Setup Ayaan
            Customer c1 = new Customer("Ayaan");
            c1.addAccount(new SavingsAccount("SAV-101", 5000));
            c1.addAccount(new LoanAccount("LOAN-99", 2000));
            customers.add(c1);

            // 2. Setup Isha
            Customer c2 = new Customer("Isha");
            c2.addAccount(new SavingsAccount("SAV-202", 1500));
            c2.addAccount(new LoanAccount("LOAN-109", 2000));
            customers.add(c2);

            // Display Initial State
            System.out.println("=== BANK INITIAL STATE ===");
            for (Customer c : customers) {
                c.showConsolidatedReport();
            }

            // 3. Perform a real Transaction
            System.out.println("\n>>> TRANSACTION: Ayaan withdraws $1000 from Savings");
             
            
            // This line now works because of the getAccount(int) method
            c1.getAccount(0).withdraw(1000); 
            System.out.println("\n=== UPDATED STATE FOR CUSTOMER ===");
            c1.showConsolidatedReport();
            System.out.println("\n>>> TRANSACTION: Isha withdraws -$1000 from Savings");
            c2.getAccount(0).withdraw(-1000); 
            System.out.println("\n=== UPDATED STATE FOR CUSTOMER ===");
            c2.showConsolidatedReport();


        } catch (BankingException e) {
            System.out.println("\nBANKING ERROR: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nSYSTEM ERROR: " + e.getMessage());
        }
    }
}

































































