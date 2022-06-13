package lambda;

import java.util.Date;
import java.util.function.Supplier;

public class SupplierClass {
    public static void main(String[] args) {
        Supplier<Date> today = Date::new;
        System.out.println(today.get());
    }
}
