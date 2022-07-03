package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceAllGroupMethodsClass {
    public static void main(String[] args) {
        String str1 = "Hello how      are      you! How     is your     Java studying???";
        System.out.println(str1);
        str1 = str1.replaceAll(" {2,}", " ");
        System.out.println(str1);

        String cardNumbers = "12345678901234561234567;" +
                "09876543210987651234567;" +
                "12345678900987651234567;";
        // 03/25 1234 1234 1234 1234 (987)
        Pattern pattern = Pattern.compile("(\\d{4})(\\d{4})(\\d{4})(\\d{4})(\\d{2})(\\d{2})(\\d{3})");
        Matcher matcher = pattern.matcher(cardNumbers);
        String newCardsView = matcher.replaceAll("$5/$6 $1 $2 $3 $4 ($7)");
        System.out.println(newCardsView);

        System.out.println("group method");
        Matcher matcher1 = pattern.matcher(cardNumbers);
        while (matcher1.find()) {
            System.out.println(matcher1.group(7));
        }
    }
}
