/**
 * Inner Class Access Patterns
 *
 * Two ways to instantiate an inner class:
 * 1. Via an outer class method (most common)
 * 2. Directly using outer.new Inner() syntax
 *
 * Inner classes maintain a hidden reference to their outer instance.
 */
public class InnerClassAccess {

    private String teamName = "Alpha";
    private int memberCount = 0;

    // Inner class
    class Member {
        private String name;

        public Member(String name) {
            this.name = name;
            memberCount++;  // accesses outer instance field!
        }

        public void introduce() {
            // Inner class can access outer class's private fields
            System.out.println("I'm " + name + " from team " + teamName
                    + " (member #" + memberCount + ")");
        }
    }

    // Way 1: Factory method in outer class creates the inner instance
    public Member addMember(String name) {
        return new Member(name);  // 'this' is implicitly the outer
    }

    public static void main(String[] args) {
        InnerClassAccess team = new InnerClassAccess();

        // Way 1: Via an outer class method
        System.out.println("=== Via outer class method ===");
        Member m1 = team.addMember("Alice");
        m1.introduce();

        // Way 2: Directly using outer.new Inner() syntax
        System.out.println("\n=== Directly with outer.new ===");
        Member m2 = team.new Member("Bob");
        m2.introduce();

        Member m3 = team.new Member("Carol");
        m3.introduce();

        System.out.println("\nTotal members: " + team.memberCount);
    }
}
/*
  Output:
  === Via outer class method ===
  I'm Alice from team Alpha (member #1)

  === Directly with outer.new ===
  I'm Bob from team Alpha (member #2)
  I'm Carol from team Alpha (member #3)

  Total members: 3
*/
