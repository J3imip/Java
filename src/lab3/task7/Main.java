package lab3.task7;

interface Integrable {
    double f(double x);

    default double integral(double a, double b, double eps) {
        if (Math.abs(b - a) < eps) {
            return f((a + b) / 2) * (b - a);
        }

        double mid = (a + b) / 2;
        double leftIntegral = integral(a, mid, eps);
        double rightIntegral = integral(mid, b, eps);

        return leftIntegral + rightIntegral;
    }
}

class TrapezoidalIntegral implements Integrable {
    @Override
    public double f(double x) {
        return Math.sin(x);
    }

    @Override
    public double integral(double a, double b, double eps) {
        double h = Math.sqrt(eps / 2.0); // adaptive step size
        double sum = 0.5 * (f(a) + f(b)); // area
        double x = a + h;

        while (x < b) {
            double fx = f(x);
            double next = x + h;
            double fxNext = f(next);

            if (Math.abs(fxNext - fx) <= eps) {
                sum += fx + fxNext;
                x = next + h;
            } else {
                h /= 2; // reduce step size
            }
        }

        return sum * h;
    }
}

public class Main {
    public static void main(String[] args) {
        Integrable TrapezoidalSin = new TrapezoidalIntegral();

        // Function: Math.sin(x)
        System.out.println("\tTrapezoidal Integral for sin(x):");
        calculateAndPrintIntegral(TrapezoidalSin, "sin(x)", 0.0, 1.0, 1e-1);
        calculateAndPrintIntegral(TrapezoidalSin, "sin(x)", 0.0, 1.0, 1e-2);
        calculateAndPrintIntegral(TrapezoidalSin, "sin(x)", 0.0, 1.0, 1e-3);

        Integrable RectangleSin = Math::sin;
        System.out.println("\tRectangle Integral for sin(x):");
        calculateAndPrintIntegral(RectangleSin, "sin(x)", 0.0, 1.0, 1e-1);
        calculateAndPrintIntegral(RectangleSin, "sin(x)", 0.0, 1.0, 1e-2);
        calculateAndPrintIntegral(RectangleSin, "sin(x)", 0.0, 1.0, 1e-3);

        // Function: Math.cos(x)
        Integrable TrapezoidalCos = new TrapezoidalIntegral() {
            @Override
            public double f(double x) {
                return Math.cos(x);
            }
        };
        System.out.println("\n\tTrapezoidal Integral for cos(x):");
        calculateAndPrintIntegral(TrapezoidalCos, "cos(x)", 0.0, 1.0, 1e-1);
        calculateAndPrintIntegral(TrapezoidalCos, "cos(x)", 0.0, 1.0, 1e-2);
        calculateAndPrintIntegral(TrapezoidalCos, "cos(x)", 0.0, 1.0, 1e-3);

        Integrable f2 = Math::cos;
        System.out.println("\tRectangle Integral for cos(x):");
        calculateAndPrintIntegral(f2, "cos(x)", 0.0, 1.0, 1e-1);
        calculateAndPrintIntegral(f2, "cos(x)", 0.0, 1.0, 1e-2);
        calculateAndPrintIntegral(f2, "cos(x)", 0.0, 1.0, 1e-3);
    }

    private static void calculateAndPrintIntegral(Integrable function, String functionName, double a, double b, double eps) {
        double integral = function.integral(a, b, eps);
        System.out.printf("Integral of %s [%f; %f] with accuracy %s:  \t%.9f\n", functionName, a, b, eps, integral);
    }
}
