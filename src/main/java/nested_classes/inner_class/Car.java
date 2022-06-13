package nested_classes.inner_class;

public class Car {

    private String color;
    private int doorCount;
    private Engine engine;

    public Car(String color, int doorCount, int horsePower) {
        this.color = color;
        this.doorCount = doorCount;
        this.engine = this.new Engine(horsePower);
    }

    public Car() {
    }


    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", doorCount=" + doorCount +
                ", engine=" + engine +
                '}';
    }

    class Engine {
        private int horsePower;

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
        Car.Engine engine = new Car().new Engine(130);
        System.out.println(engine.toString());

        Car car = new Car("red", 5, 300);
        Car.Engine engine1 = car.new Engine(230);
        System.out.println(car.toString());
        System.out.println(engine1.toString());
        //car.method();
    }
}
