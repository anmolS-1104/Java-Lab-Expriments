package Exp6;
import java.time.LocalDate; // Modern Date API

// Root Class
public abstract class Employee {
    protected String name, panNo, type;
    protected double empID;
    protected LocalDate joiningDate; // Changed from Date to LocalDate

    public Employee(String name, String panNo, String type, double empID, LocalDate joiningDate) {
        this.name = name;
        this.panNo = panNo;
        this.type = type;
        this.empID = empID;
        this.joiningDate = joiningDate;
    }

    public abstract double calcCTC();

    public void displayBaseInfo() {
        System.out.println("Employee ID   : " + (int)empID);
        System.out.println("Employee Name : " + name);
        System.out.println("Employee Type : " + type);
        System.out.println("PAN Number    : " + panNo);
        System.out.println("Joining Date  : " + joiningDate);
    }
}

// 1. Single Inheritance
class FullTimeEmployee extends Employee {
    double baseSalary, perfBonus;

    FullTimeEmployee(double id, String name, String pan, LocalDate doj, double base, double bonus) {
        super(name, pan, "Permanent", id, doj);
        this.baseSalary = base;
        this.perfBonus = bonus;
    }

    @Override
    public double calcCTC() { return baseSalary + perfBonus; }

    public void displayPayroll() {
        displayBaseInfo();
        System.out.println("Basic Salary  : " + (int)baseSalary);
        System.out.println("Gross Salary  : " + (int)calcCTC() + "\n");
    }
}

// 2. Multilevel Inheritance
class Manager extends FullTimeEmployee {
    double allowance;

    Manager(double id, String name, String pan, LocalDate doj, double base, double bonus, double allowance) {
        super(id, name, pan, doj, base, bonus);
        this.type = "Manager (Permanent)";
        this.allowance = allowance;
    }

    @Override
    public double calcCTC() { return super.calcCTC() + allowance; }
}

// 3. Hierarchical Inheritance
class ContractEmployee extends Employee {
    double hourlyRate;
    int hours;

    ContractEmployee(double id, String name, String pan, LocalDate doj, double rate, int hrs) {
        super(name, pan, "Contract", id, doj);
        this.hourlyRate = rate;
        this.hours = hrs;
    }

    @Override
    public double calcCTC() { return hourlyRate * hours; }

    public void displayPayroll() {
        displayBaseInfo();
        System.out.println("Monthly Salary: " + (int)calcCTC() + "\n");
    }
}