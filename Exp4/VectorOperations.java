package Exp4;

public class VectorOperations {
    public static void main(String[] args){
        public Vector add(Vector other) {
            validateMatch(other);
            double[] result = new double[dim];
    for (int i = 0; i < dim; i++) {
        result[i] = this.V[i] + other.V[i];
    }
    return new Vector(result);
}
public Vector subtract(Vector other) {
    validateMatch(other);
    double[] result = new double[dim];
    for (int i = 0; i < dim; i++) {
        result[i] = this.V[i] - other.V[i];
    }
    return new Vector(result);
}
public double dotProduct(Vector other) {
    validateMatch(other);
    double result = 0;
    for (int i = 0; i < dim; i++) {
        result += this.V[i] * other.V[i];
    }
    return result;
}
public void display() {
    System.out.print("(");
    for (int i = 0; i < dim; i++) {
        System.out.print(V[i]);
        if (i < dim - 1) {
            System.out.print(", ");
        }
    }
    System.out.println(")");
}
    }
    

}

