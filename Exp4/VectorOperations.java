package Exp4;

public class VectorOperations {

    // Requirement: Method to check dimensions
    public static void validateMatch(Vector v1, Vector v2) throws VectorException {
        if (v1.dim != v2.dim) {
            throw new VectorException("Dimensions do not match: " + v1.dim + "D vs " + v2.dim + "D");
        }
    }

    // Requirement: add() returns Vector
    public static Vector add(Vector v1, Vector v2) throws VectorException {
        validateMatch(v1, v2);
        double[] res = new double[v1.dim];
        for (int i = 0; i < v1.dim; i++) {
            res[i] = v1.V[i] + v2.V[i];
        }
        return new Vector(res);
    }

    // Requirement: subtract() returns Vector
    public static Vector subtract(Vector v1, Vector v2) throws VectorException {
        validateMatch(v1, v2);
        double[] res = new double[v1.dim];
        for (int i = 0; i < v1.dim; i++) {
            res[i] = v1.V[i] - v2.V[i];
        }
        return new Vector(res);
    }

    // Requirement: dotProduct() returns a single value
    public static double dotProduct(Vector v1, Vector v2) throws VectorException {
        validateMatch(v1, v2);
        double res = 0;
        for (int i = 0; i < v1.dim; i++) {
            res += v1.V[i] * v2.V[i];
        }
        return res;
    }

    public static void main(String[] args) {
        try {
            Vector v1 = new Vector(new double[]{10, 20, 30});
            Vector v2 = new Vector(new double[]{5, 5, 5});

            System.out.println("v1: "); v1.display();
            System.out.println("v2: "); v2.display();

            // Store in 'v' as requested
            Vector v = add(v1, v2);
            System.out.println("Result Addition (v): "); v.display();

            v = subtract(v1, v2);
            System.out.print("Result Subtraction (v): "); v.display();

            double dot = dotProduct(v1, v2);
            System.out.println("Result Dot Product: " + dot);

        } catch (VectorException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}