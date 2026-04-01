public class VarargDemo {

    // Vararg: int... allows ANY number of int arguments
    // Internally treated as an array (int[])
    public int sum(int... numbers) {
        System.out.println("You have passed " + numbers.length + " arguments.");
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // Vararg must be the LAST parameter — only ONE per method
    public String formatScores(String student, int... scores) {
        StringBuilder sb = new StringBuilder(student + "'s scores: ");
        for (int i = 0; i < scores.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(scores[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("*** Vararg (Variable-Length Arguments) Demo ***\n");

        VarargDemo ob = new VarargDemo();

        // Call with different numbers of arguments
        int result;
        result = ob.sum(57, 63);
        System.out.println("Sum of 57 and 63: " + result);

        result = ob.sum(57, 63, 50);
        System.out.println("Sum of 57, 63, and 50: " + result);

        result = ob.sum();
        System.out.println("Sum of nothing: " + result);

        System.out.println();

        // printf() and format() are vararg methods in Java!
        System.out.printf("%s scored %d points%n", "Alice", 95);
        String msg = String.format("%s has GPA %.1f", "Bob", 3.8);
        System.out.println(msg);

        System.out.println();

        // Custom vararg with regular parameter
        System.out.println(ob.formatScores("Alice", 95, 87, 92));
        System.out.println(ob.formatScores("Bob", 88));
    }
}
