package Exp5;


import java.util.ArrayList;

// -------------------- Base Class --------------------
class BankAccount {
    private final String accountNo;
    private final String holderName;
    protected double balance;

    public BankAccount(String accountNo, String holderName, double openingBalance) throws BankingException {
        if (openingBalance < 0) throw new BankingException(openingBalance+" Opening balance cannot be negative.");
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public void deposit(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Deposit must be positive.");
        balance += amount;
        System.out.println("Deposited: $" + amount + " to " + accountNo);
    }

    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Amount must be positive.");
        if (amount > balance) {
            throw new BankingException("FAILED: Insufficient funds. Balance: $" + balance + ", Requested: $" + amount);
        }
        System.out.println("Withdrawing: $" + amount + " from Generic Account [" + accountNo + "]");
        balance -= amount;
        System.out.println("Success! New Balance: $" + balance);
    }

    public String getAccountType() { return "GENERIC"; }
    public String getAccountNo() { return accountNo; }
    
    public String summary() {
        return String.format("[%s] AccNo=%s, Holder=%s, Balance=%.2f", getAccountType(), accountNo, holderName, balance);
    }
}

// -------------------- Child: Savings --------------------
class SavingsAccount extends BankAccount {
    private final double minBalance;

    public SavingsAccount(String accountNo, String holderName, double openingBalance, double minBalance) throws BankingException {
        super(accountNo, holderName, openingBalance);
        this.minBalance = minBalance;
    }

    @Override
    public String getAccountType() { return "SAVINGS"; }

    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Amount must be positive.");
        if ((balance - amount) < minBalance) {
            throw new BankingException(amount+ " Cannnot be withdrawn due to minBalance:" + minBalance);
        }
        System.out.println("Withdrawing: $" + amount + " from Savings [" + getAccountNo() + "]");
        balance -= amount;
        System.out.println("Success! New Savings Balance: $" + balance);
    }
}

// -------------------- Child: Current (No Overdraft) --------------------
class CurrentAccount extends BankAccount {

    public CurrentAccount(String accountNo, String holderName, double openingBalance) throws BankingException {
        super(accountNo, holderName, openingBalance);
    }

    @Override
    public String getAccountType() { return "CURRENT"; }

    @Override
    public void withdraw(double amount) throws BankingException {
        if (amount <= 0) throw new BankingException("Amount must be positive.");
        if (amount > balance) {
            throw new BankingException("FAILED: Current Account has no overdraft. Insufficient funds.");
        }
        System.out.println("Withdrawing: $" + amount + " from Current Account [" + getAccountNo() + "]");
        balance -= amount;
        System.out.println("Success! New Current Balance: $" + balance);
    }
}

// -------------------- Main Driver --------------------
public class BankingSimpleDemo {
    public static void main(String[] args) {
        try {
            // Setup accounts
            BankAccount sa = new SavingsAccount("SA-101", "Ayaan", 5000,1000);
            BankAccount ca = new CurrentAccount("CA-201", "Isha", 2000);

            ArrayList<BankAccount> list = new ArrayList<>();
            list.add(sa);
            list.add(ca);

            System.out.println("=== INITIAL BALANCES ===");
            for (BankAccount b : list) System.out.println(b.summary());

            System.out.println("\n=== PROCESSING WITHDRAWALS ===");
            
            // 1. Valid Savings Withdrawal
            sa.withdraw(2000); 

            // 2. Valid Current Withdrawal
            ca.withdraw(1500);

            System.out.println("\n=== AFTER TRANSACTIONS ===");
            for (BankAccount b : list) System.out.println(b.summary());

            // 3. Triggering Exception (Attempt to withdraw more than balance)
            
            

        } catch (BankingException e) {
            System.out.println("\nBLOCKER: " + e.getMessage());
        }
    }
}
