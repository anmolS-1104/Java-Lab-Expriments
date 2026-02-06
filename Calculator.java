//EXP 1
import java.util.Scanner;

public class Calculator {
    public double n1, n2;

    // Method to add numbers
    public double add(double a, double b) {
        return a + b;
    }

    public double sub(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed.");
            return 0;
        }
        return a / b;
    }

    // Method to find the modulus value
    public double modulusNums(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not possible");
            return 0;
        }
        return a % b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();
        int choice;

        // The do-while loop ensures the menu displays at least once
        do {
            System.out.println("\n--- Menu Driven Calculator ---");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Handle the exit case first
            if (choice == 5) {
                System.out.println("Exiting...");
                break; 
            }

            // Only ask for numbers if the choice is valid (1-4)
            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter first number: ");
                calc.n1 = scanner.nextDouble();

                System.out.print("Enter second number: ");
                calc.n2 = scanner.nextDouble();

                double result = 0;
                switch (choice) {
                    case 1:
                        result = calc.add(calc.n1, calc.n2);
                        break;
                    case 2:
                        result = calc.sub(calc.n1, calc.n2);
                        break;
                    case 3:
                        result = calc.multiply(calc.n1, calc.n2);
                        break;
                    case 4:
                        result = calc.division(calc.n1, calc.n2);
                        break;
                }
                System.out.println("Result = " + result);
            } else {
                System.out.println("Invalid choice! Please select a valid option.");
            }

        } while (choice != 5);

        scanner.close();
    }
}







































































        
        
        
        
    
		
		
	







    




