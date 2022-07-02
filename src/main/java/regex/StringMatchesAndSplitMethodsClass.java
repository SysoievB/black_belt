package regex;

import java.util.Arrays;

public class StringMatchesAndSplitMethodsClass {
    public static void main(String[] args) {
        String str = "Ivanov Ivan, Ukraine, Kiev, Maidan street,34, flat 14,email: ivanovi@mail.ua, Postcode: AE232, Phone number: +123456789" +
                "Vasiliev Inokentii, Ukraine, Dnipro, Shevchenko street,13, flat 68,email: vasilievi@mail.ua, Postcode: SD432, Phone number: +987654321" +
                "Aksenova Anna, Poland, Warsaw, Duda street,94, flat 44,email: aksenovanna@mail.com, Postcode: DF433, Phone number: +482342345";
        String strEmail= "aksenovanna@mail.com";

        boolean result= strEmail.matches("\\w+@\\w+\\.(com|ua)");
        System.out.println(result);

        String []array = str.split(" ");
        System.out.println(Arrays.toString(array));
    }
}
