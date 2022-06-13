package nested_classes.local_inner_class;

public class LocalInnerClassWithInterface {
    public static void main(String[] args) {
        class Adding implements MathInterface {
            @Override
            public int doOperation(int a, int b) {
                return a + b;
            }
        }
        System.out.println(new Adding().doOperation(2, 8));

    }

    interface MathInterface {
        int doOperation(int a, int b);
    }
}

