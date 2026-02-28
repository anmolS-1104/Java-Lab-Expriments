package Exp4;

public class Vector {

    private double[] components;
    private int dimension;

    // Constructor (only 2D or 3D allowed)
    public Vector(double[] comp) throws VectorException {

        if (comp.length != 2 && comp.length != 3) {
            throw new VectorException("Only 2D or 3D vectors are allowed.");
        }

        dimension = comp.length;
        components = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            components[i] = comp[i];
        }
    }

    public int getDimension() {
        return dimension;
    }

    public double[] getComponents() {
        return components;
    }

    // Display method
    public void display() {
        System.out.print("(");
        for (int i = 0; i < dimension; i++) {
            System.out.print(components[i]);
            if (i < dimension - 1)
                System.out.print(", ");
        }
        System.out.println(")");
    }
}

