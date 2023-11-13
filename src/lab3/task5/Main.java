package lab3.task5;

import java.util.Arrays;

record Circle(double radius) implements Comparable<Circle> {
    @Override
    public int compareTo(Circle other) {
        // Compare circles based on their radius
        return Double.compare(this.radius, other.radius);
    }

    @Override
    public String toString() {
        return "Circle [radius=" + radius + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating an array of Circle objects
        Circle[] circles = {
                new Circle(3.5),
                new Circle(2.0),
                new Circle(5.2),
                new Circle(1.8)
        };

        // Sorting the array using Arrays.sort()
        Arrays.sort(circles);

        // Displaying the sorted circles
        System.out.println("Sorted Circles:");
        for (Circle circle : circles) {
            System.out.println(circle);
        }
    }
}

