package lab3.task4;

public class OneDimensionalArrayOfPoints extends AbstractArrayOfPoints {
    private double[] points;

    public OneDimensionalArrayOfPoints(int capacity) {
        points = new double[capacity * 2];
    }

    @Override
    public void setPoint(int i, double x, double y) {
        int index = i * 2;
        points[index] = x;
        points[index + 1] = y;
    }

    @Override
    public double getX(int i) {
        return points[i * 2];
    }

    @Override
    public double getY(int i) {
        return points[i * 2 + 1];
    }

    @Override
    public int count() {
        return points.length / 2;
    }

    @Override
    public void addPoint(double x, double y) {
        double[] newArray = new double[points.length + 2];
        System.arraycopy(points, 0, newArray, 0, points.length);
        newArray[points.length] = x;
        newArray[points.length + 1] = y;
        points = newArray;
    }

    @Override
    public void removeLast() {
        if (points.length > 1) {
            double[] newArray = new double[points.length - 2];
            System.arraycopy(points, 0, newArray, 0, points.length - 2);
            points = newArray;
        }
    }
}
