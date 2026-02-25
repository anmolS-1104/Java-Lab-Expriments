package Exp4;

public class Vector {
    private double[] V;
    private int dim;

    public Vector(double[] components) throws VectorException {
        if (components.length < 2 || components.length > 3) {
            throw new VectorException("Invalid dimension: " + components.length + ". Only 2D or 3D allowed.");
        }
        this.V = components;
        this.dim = components.length;
    }

    private void validateMatch(Vector other) throws VectorException {
        if (this.dim != other.dim) {
            throw new VectorException("Dimension mismatch: " + this.dim + "D vs " + other.dim + "D.");
        }
    }

}


