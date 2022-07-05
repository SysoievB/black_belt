package abstract_class;

public abstract class A {
    abstract void m1();

    abstract void m2();
}

class B extends A {
    void m1() {
        System.out.println("One");
    }

    void m2() {
        System.out.println("Two");
    }

    void m3() {
        System.out.println("Three");
    }
}

class Test {
    public static void main(String[] args) {
        A a = new B();
        a.m1();
        a.m2();
        // a.m3();//A do not have m2 method that is why we should create B
    }
}
