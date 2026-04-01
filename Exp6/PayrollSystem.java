package Exp6;

public class PayrollSystem {
    public static void main(String[] args) {
        // Full-Time SWE
        FullTimeEmployee swe = new FullTimeEmployee(101, "John Doe", "ABCDE1234F", "SWE", 60000, 5000);
        swe.displayInfo();
        System.out.println("CTC: $" + swe.calcCTC());

        // Contract Employee
        ContractEmployee contractor = new ContractEmployee(201, "Jane Smith", "FGHIJ5678K", "Consultant", 50, 160);
        contractor.displayInfo();
        System.out.println("CTC: $" + contractor.calcCTC());

        // Manager (Multilevel)
        Manager mgr = new Manager(301, "Robert Brown", "LMNOP9012Q", 80000, 15000, 2000, 3000);
        mgr.displayInfo();
        System.out.println("CTC: $" + mgr.calcCTC());
    }
}
