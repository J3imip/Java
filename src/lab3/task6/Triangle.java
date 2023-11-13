package lab3.task6;

import java.util.Arrays;
import java.util.Comparator;

public class Triangle {
    private final double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getArea() {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public String toString() {
        return "Triangle [a=" + a + ", b=" + b + ", c=" + c + ", Area=" + getArea() + "]";
    }

    public static void main(String[] args) {
        // Creating an array of triangles
        Triangle[] triangles = {
                new Triangle(3, 4, 5),
                new Triangle(7, 24, 25),
                new Triangle(6, 8, 10)
        };

        // Sorting the array based on the area in descending order
        Arrays.sort(triangles, new TriangleComparator().reversed());

        // Displaying the sorted triangles
        System.out.println("Sorted Triangles (by Area in descending order):");
        for (Triangle triangle : triangles) {
            System.out.println(triangle);
        }
    }
}

class TriangleComparator implements Comparator<Triangle> {
    @Override
    public int compare(Triangle t1, Triangle t2) {
        // Compare triangles based on area in ascending order
        return Double.compare(t1.getArea(), t2.getArea());
    }
}

