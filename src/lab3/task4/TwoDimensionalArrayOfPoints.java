package lab3.task4;

public class TwoDimensionalArrayOfPoints extends AbstractArrayOfPoints {
    private double[][] points;

    public TwoDimensionalArrayOfPoints(int capacity) {
        points = new double[capacity][2];
    }

    @Override
    public void setPoint(int i, double x, double y) {
        points[i][0] = x;
        points[i][1] = y;
    }

    @Override
    public double getX(int i) {
        return points[i][0];
    }

    @Override
    public double getY(int i) {
        return points[i][1];
    }

    @Override
    public int count() {
        return points.length;
    }

    @Override
    public void addPoint(double x, double y) {
        double[][] newArray = new double[points.length + 1][2];
        System.arraycopy(points, 0, newArray, 0, points.length);
        newArray[points.length][0] = x;
        newArray[points.length][1] = y;
        points = newArray;
    }

    @Override
    public void removeLast() {
        if (points.length > 0) {
            double[][] newArray = new double[points.length - 1][2];
            System.arraycopy(points, 0, newArray, 0, points.length - 1);
            points = newArray;
        }
    }
}
