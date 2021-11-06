package TankMovement;

import TankMovement.Elements.Point;

public class Tank {

    private static final double offset = 3f;

    private Point[] points = {new Point(0f + offset, 0f + offset), 
        new Point(0f + offset, 1f + offset), 
        new Point(-1f + offset, -.3f + offset), 
        new Point(1f + offset, -.3f + offset)};

    public Point[] getTankPoints() {
        return points;
    }

    public void move(double speed) {

        double x = points[1].getMatrix()[0][0] - points[0].getMatrix()[0][0];
        double y = points[1].getMatrix()[1][0] - points[0].getMatrix()[1][0];

        double length = 1 / (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));

        x *= length * speed;
        y *= length * speed;

        translate(x, y);
    }

    public void rotate(double alpha) {

        double[][] rotationMatrix = {
            {Math.cos(Math.toRadians(alpha)), -Math.sin(Math.toRadians(alpha)), 0f},
            {Math.sin(Math.toRadians(alpha)), Math.cos(Math.toRadians(alpha)), 0f},
            {0f, 0f, 1f}
        };

        double[][] center = points[0].getMatrix();

        translate(-center[0][0], -center[1][0]);

        for (int i = 0; i < points.length; i++) {

            points[i].setMatrix(matmul(rotationMatrix, points[i].getMatrix()));
        }

        translate(center[0][0], center[1][0]);
    }

    private void translate(double vectorX, double vectorY) {

        double[][] translationMathix = {
            {1f, 0f, vectorX},
            {0f, 1f, vectorY},
            {0f, 0f, 1f}
        };

        for (int i = 0; i < points.length; i++) {

            points[i].setMatrix(matmul(translationMathix, points[i].getMatrix()));
        }
    }

    private double[][] matmul(double[][] a, double[][] b) {

        double[][] result = new double[b.length][b[0].length];

        // o promeiro for é para cada linha da matriz da esquerda
        for (int i = 0; i < a.length; i++) {

            // o segundo for é para cada coluna da matriz da direita
            for (int j = 0; j < b[0].length; j++) {

                //o terceiro for é para multiplicar cada elemento da linha da esquerda pela coluna da direita
                for (int m = 0; m < b.length && m < a[i].length; m++) {

                    result[i][j] += a[i][m] * b[m][j];
                }
            }
        }

        return result;
    }
}
