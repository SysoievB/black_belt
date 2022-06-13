package nested_classes.static_nested_class;

public class Car {

    private String color;
    private int doorCount;
    private Engine engine;

    public Car(String color, int doorCount, Engine engine) {
        this.color = color;
        this.doorCount = doorCount;
        this.engine = engine;
    }

    void method() {
        System.out.println(Engine.countOfObjects);
        System.out.println(new Engine(233).horsePower);
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", doorCount=" + doorCount +
                ", engine=" + engine +
                '}';
    }

    static class Engine {
        private int horsePower;
        private static int countOfObjects;

        public Engine(int horsePower) {
            this.horsePower = horsePower;
        }

        @Override
        public String toString() {
            return "Engine{" +
                    "horsePower=" + horsePower +
                    '}';
        }
    }
}

class Test {
    public static void main(String[] args) {
        Car.Engine engine = new Car.Engine(1200);
        System.out.println(engine.toString());

        Car car = new Car("red", 5, engine);
        System.out.println(car.toString());
        car.method();
    }
}