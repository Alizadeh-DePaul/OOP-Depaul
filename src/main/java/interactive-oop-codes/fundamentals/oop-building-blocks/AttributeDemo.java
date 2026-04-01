public class AttributeDemo {
    public static void main(String[] args) {
        // Create two Dog objects
        Dog rex = new Dog("Rex", "German Shepherd", 5);
        Dog luna = new Dog("Luna", "Golden Retriever", 3);

        // Each object has its OWN copy of attributes
        System.out.println(rex.getInfo());
        // Rex | German Shepherd | Age: 5

        System.out.println(luna.getInfo());
        // Luna | Golden Retriever | Age: 3

        // Changing one object does NOT affect the other
        rex.haveBirthday();
        System.out.println(rex.getAge());   // 6
        System.out.println(luna.getAge());  // 3 — unchanged!
    }
}

class Dog {
    // Attributes (instance variables) — each Dog has its own copy
    private String name;
    private String breed;
    private int age;

    Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    void haveBirthday() {
        age++;  // Only THIS dog's age changes
    }

    int getAge() { return age; }

    String getInfo() {
        return name + " | " + breed + " | Age: " + age;
    }
}
