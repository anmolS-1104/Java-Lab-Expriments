package Exp6;

import java.util.Date;


abstract class Employee {
    String name;
    String panNo;
    Date joiningDate;
    String designation;
    double empID;
    public Employee(String name,String panNo,String designation,double empID){
        this.name = name;
        this.panNo = panNo;
        this.joiningDate = new Date();
        this.designation = designation;
        this.empID = empID;
    }
    abstract double calcCTC();
    void displayInfo() {
        System.out.println("\nID: " + empID + " | Name: " + name + " | Role: " + designation);
    }
}
class FullTimeEmployee extends Employee{
    double baseSalary;
    double extraPay;
    FullTimeEmployee(int empID, String name, String panNo, String designation, double baseSalary, double extraPay) {
        super(name,panNo,designation,empID);
        this.baseSalary = baseSalary;
        this.extraPay = extraPay;
    }

    @Override
    double calcCTC() {
        // Shared logic for SWE and HR roles
        return baseSalary + extraPay;
    }
}
class ContractEmployee extends Employee {
    double hourlyRate;
    int noOfHrs;

    ContractEmployee(int empID, String name, String panNo, String designation, double hourlyRate, int noOfHrs) {
        super(name,panNo,designation,empID);
        this.hourlyRate = hourlyRate;
        this.noOfHrs = noOfHrs;
    }

    @Override
    double calcCTC() {
        return noOfHrs * hourlyRate;
    }
}
class Manager extends FullTimeEmployee{
    double ta; // Travel Allowance
    double eduAllowance;

    Manager(int empId, String name, String panNo, double baseSalary, double perfBonus, double ta, double eduAllowance) {
        // Designation is fixed as Manager
        super(empId, name, panNo, "Manager", baseSalary, perfBonus);
        this.ta = ta;
        this.eduAllowance = eduAllowance;
    }

    @Override
    double calcCTC() {
        // baseSalary + perfBonus (from parent) + Manager specific allowances
        return super.calcCTC() + ta + eduAllowance;
    }
  }
















