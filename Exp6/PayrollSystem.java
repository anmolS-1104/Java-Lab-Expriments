package Exp6;
import java.time.LocalDate;


public class PayrollSystem {
    public static void main(String[] args) {
        // Create Permanent Employee
        FullTimeEmployee emp1 = new FullTimeEmployee(301, "Amit Kulkarni", "ABC1234P", LocalDate.of(2022, 11, 5), 30000, 12000);
        
        // Create Contract Employee
        ContractEmployee emp2 = new ContractEmployee(302, "Sneha Patil", "XYZ5678Q", LocalDate.now(), 156.25, 160);
        
        // Create Manager
        Manager mgr = new Manager(303, "Robert Brown", "LMN9012R", LocalDate.of(2022, 4, 5), 50000, 15000, 5000);

        // Run displays
        emp1.displayPayroll();
        emp2.displayPayroll();
        mgr.displayPayroll();
    }
}