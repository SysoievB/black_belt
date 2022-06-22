package static_var;

public class StatClass {
    private static String statMessage = "stat hello";
    private String message = "hello";

    public static void main(String[] args) {
        System.out.println(new StatClass().message);
        System.out.println(statMessage);
        System.out.println('*');
        new StatClass().nonStat();
    }

    public void nonStat() {
        System.out.println(StatClass.statMessage);
        System.out.println(message);
    }
}
