package Exp4;
import java.util.Scanner;

public class VectorOperations {

    // Method to check dimensions
    public static void checkDimension(Vector v1, Vector v2) throws VectorException {
        if (v1.getDimension() != v2.getDimension()) {
            throw new VectorException("Vector dimensions must be same.");
        }
    }

    // Add method
    public static Vector add(Vector v1, Vector v2) throws VectorException {
        checkDimension(v1, v2);

        int dim = v1.getDimension();
        double[] result = new double[dim];

        for (int i = 0; i < dim; i++) {
            result[i] = v1.getComponents()[i] + v2.getComponents()[i];
        }

        return new Vector(result);
    }

    // Subtract method
    public static Vector subtract(Vector v1, Vector v2) throws VectorException {
        checkDimension(v1, v2);

        int dim = v1.getDimension();
        double[] result = new double[dim];

        for (int i = 0; i < dim; i++) {
            result[i] = v1.getComponents()[i] - v2.getComponents()[i];
        }

        return new Vector(result);
    }

    // Dot Product method
    public static double dotProduct(Vector v1, Vector v2) throws VectorException {
        checkDimension(v1, v2);

        double result = 0;
        int dim = v1.getDimension();

        for (int i = 0; i < dim; i++) {
            result += v1.getComponents()[i] * v2.getComponents()[i];
        }

        return result;
    }

    // Strict input method
    public static Vector readVector(Scanner sc) throws VectorException {

        System.out.print("Enter dimension (2 or 3): ");
        int dim = sc.nextInt();
        sc.nextLine(); // clear buffer

        System.out.println("Enter components separated by space:");
        String input = sc.nextLine();

        String[] parts = input.trim().split("\\s+");

        if (parts.length != dim) {
            throw new VectorException("Number of components must match dimension!");
        }

        double[] comp = new double[dim];

        try {
            for (int i = 0; i < dim; i++) {
                comp[i] = Double.parseDouble(parts[i]);
            }
        } catch (NumberFormatException e) {
            throw new VectorException("Invalid number format!");
        }

        return new Vector(comp);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.println("Vector 1:");
            Vector v1 = readVector(sc);

            System.out.println("\nVector 2:");
            Vector v2 = readVector(sc);

            Vector sum = add(v1, v2);
            Vector diff = subtract(v1, v2);
            double dot = dotProduct(v1, v2);

            System.out.println("\nAddition Result:");
            sum.display();

            System.out.println("Subtraction Result:");
            diff.display();

            System.out.println("Dot Product: " + dot);

        } catch (VectorException e) {
            System.out.println("Vector Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error!");
        }

        sc.close();
    }
}