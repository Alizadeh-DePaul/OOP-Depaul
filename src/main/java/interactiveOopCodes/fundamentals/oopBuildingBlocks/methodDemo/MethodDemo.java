package interactiveOopCodes.fundamentals.oopBuildingBlocks.methodDemo;

public class MethodDemo {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Method with parameters and return value
        int sum = calc.add(5, 3);
        System.out.println("5 + 3 = " + sum);       // 8

        // Method overloading — same name, different parameters
        double dSum = calc.add(2.5, 3.7);
        System.out.println("2.5 + 3.7 = " + dSum);  // 6.2

        int tripleSum = calc.add(1, 2, 3);
        System.out.println("1 + 2 + 3 = " + tripleSum); // 6
    }
}

class Calculator {
    // Method: two int parameters
    int add(int a, int b) {
        return a + b;
    }

    // Overloaded: two double parameters
    double add(double a, double b) {
        return a + b;
    }

    // Overloaded: three int parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }
}
