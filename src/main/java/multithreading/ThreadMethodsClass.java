package multithreading;

public class ThreadMethodsClass {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("myThread.getName() " + myThread.getName());
        myThread.setPriority(Thread.MAX_PRIORITY);
        System.out.println("myThread.getPriority() " + myThread.getPriority());
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("hello");
    }
}