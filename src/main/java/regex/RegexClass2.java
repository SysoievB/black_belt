package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexClass2 {
    public static void main(String[] args) {
        String str = "Ivanov Ivan, Ukraine, Kiev, Maidan street,34, flat 14,email: ivanovi@mail.ua, Postcode: AE232, Phone number: +123456789" +
                "Vasiliev Inokentii, Ukraine, Dnipro, Shevchenko street,13, flat 68,email: vasilievi@mail.ua, Postcode: SD432, Phone number: +987654321" +
                "Aksenova Anna, Poland, Warsaw, Duda street,94, flat 44,email: aksenovanna@mail.com, Postcode: DF433, Phone number: +482342345";

        System.out.println("find all home and flat numbers");
        Pattern pattern = Pattern.compile("\\b\\d{2}\\b");//\\b means border of word
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }

        System.out.println("----------------------------------------");
        System.out.println("find all phone numbers");
        Pattern pattern1 = Pattern.compile("\\+\\d{9}");
        Matcher matcher1 = pattern1.matcher(str);
        while (matcher1.find()) {
            System.out.println(matcher1.group());
        }

        System.out.println("----------------------------------------");
        System.out.println("find all emails");
        Pattern pattern2 = Pattern.compile("\\w+@\\w+\\.(ua|com)");
        Matcher matcher2 = pattern2.matcher(str);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
        }
    }
}
