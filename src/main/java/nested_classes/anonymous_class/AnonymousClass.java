package nested_classes.anonymous_class;

public class AnonymousClass {
    public static void main(String[] args) {
        MathInterface mathInterface = new MathInterface() {
            @Override
            public int doOperation(int a, int b) {
                return a + b;
            }
        };
        System.out.println(mathInterface.doOperation(22, 66));
    }
}

interface MathInterface {
    int doOperation(int a, int b);
}