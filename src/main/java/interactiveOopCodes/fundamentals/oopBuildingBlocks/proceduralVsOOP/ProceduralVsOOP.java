package interactiveOopCodes.fundamentals.oopBuildingBlocks.proceduralVsOOP;

// === PROCEDURAL APPROACH ===
// Data and functions are separate
class ProceduralCar {
    static void accelerate(String brand, int[] speed) {
        speed[0] += 10;
        System.out.println(brand + " accelerated to " + speed[0]);
    }
}

// === OOP APPROACH ===
// Data and behavior are bundled together
class Car {
    private String brand;
    private int speed;

    Car(String brand) {
        this.brand = brand;
        this.speed = 0;
    }

    void accelerate() {
        speed += 10;
        System.out.println(brand + " accelerated to " + speed);
    }
}

public class ProceduralVsOOP {
    public static void main(String[] args) {
        // Procedural: pass data to functions
        int[] speed = {0};
        ProceduralCar.accelerate("Toyota", speed);

        // OOP: objects manage their own data
        Car car = new Car("Toyota");
        car.accelerate();
    }
}
