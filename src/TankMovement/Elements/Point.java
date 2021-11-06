package TankMovement.Elements;

public class Point {
    
    private double[][] coordinates;

    public Point (double x, double y) {

        coordinates = new double[][] {{x}, {y}, {1f}};
    }

    public void setMatrix(double[][] matrix) {
        coordinates = matrix;
    }

    public double[][] getMatrix() {
        return coordinates;
    }
}
