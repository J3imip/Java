package lab3.task3;

// Function interface
interface Function {
    double evaluate(double x);
}

// Class using abstract and derived classes
class MinFinderUsingClasses {
    public static double findMinimum(Function f, double start, double end, double step) {
        double min = Double.MAX_VALUE;
        for (double x = start; x <= end; x += step) {
            double y = f.evaluate(x);
            if (y < min) {
                min = y;
            }
        }
        return min;
    }
}

// Class using interface and implementing classes
class MinFinderUsingInterface {
    public static double findMinimum(Function f, double start, double end, double step) {
        double min = Double.MAX_VALUE;
        for (double x = start; x <= end; x += step) {
            double y = f.evaluate(x);
            if (y < min) {
                min = y;
            }
        }
        return min;
    }
}

// Class using interface and anonymous classes
class MinFinderUsingAnonymousClasses {
    public static double findMinimum(Function f, double start, double end, double step) {
        double min = Double.MAX_VALUE;
        for (double x = start; x <= end; x += step) {
            double y = f.evaluate(x);
            if (y < min) {
                min = y;
            }
        }
        return min;
    }
}

// Class using lambda expressions
class MinFinderUsingLambda {
    public static double findMinimum(Function f, double start, double end, double step) {
        double min = Double.MAX_VALUE;
        for (double x = start; x <= end; x += step) {
            double y = f.evaluate(x);
            if (y < min) {
                min = y;
            }
        }
        return min;
    }
}

// Class using method references
class MinFinderUsingMethodReferences {
    public static double findMinimum(Function f, double start, double end, double step) {
        double min = Double.MAX_VALUE;
        for (double x = start; x <= end; x += step) {
            double y = f.evaluate(x);
            if (y < min) {
                min = y;
            }
        }
        return min;
    }
}

public class Main {
    public static void main(String[] args) {
        // Example functions: x^2 and sin(x)
        Function quadraticFunction = x -> x * x;
        Function sineFunction = Math::sin;

        System.out.println("\tx^2:");
        // Testing each approach with the quadratic function
        System.out.println("Minimum using Abstract and Derived Classes: " +
                MinFinderUsingClasses.findMinimum(quadraticFunction, -5, 5, 0.1));

        System.out.println("Minimum using Interface and Implementing Classes: " +
                MinFinderUsingInterface.findMinimum(quadraticFunction, -5, 5, 0.1));

        System.out.println("Minimum using Interface and Anonymous Classes: " +
                MinFinderUsingAnonymousClasses.findMinimum(quadraticFunction, -5, 5, 0.1));

        System.out.println("Minimum using Lambda Expressions: " +
                MinFinderUsingLambda.findMinimum(quadraticFunction, -5, 5, 0.1));

        System.out.println("Minimum using Method References: " +
                MinFinderUsingMethodReferences.findMinimum(quadraticFunction, -5, 5, 0.1));

        // Testing each approach with the sin(x) function
        System.out.println("\tsin(x):");
        System.out.println("Minimum using Abstract and Derived Classes: " +
                MinFinderUsingClasses.findMinimum(sineFunction, -2 * Math.PI, 2 * Math.PI, 0.1));

        System.out.println("Minimum using Interface and Implementing Classes: " +
                MinFinderUsingInterface.findMinimum(sineFunction, -2 * Math.PI, 2 * Math.PI, 0.1));

        System.out.println("Minimum using Interface and Anonymous Classes: " +
                MinFinderUsingAnonymousClasses.findMinimum(sineFunction, -2 * Math.PI, 2 * Math.PI, 0.1));

        System.out.println("Minimum using Lambda Expressions: " +
                MinFinderUsingLambda.findMinimum(sineFunction, -2 * Math.PI, 2 * Math.PI, 0.1));

        System.out.println("Minimum using Method References: " +
                MinFinderUsingMethodReferences.findMinimum(sineFunction, -2 * Math.PI, 2 * Math.PI, 0.1));
    }
}

