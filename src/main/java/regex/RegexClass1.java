package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexClass1 {
    public static void main(String[] args) {
        System.out.println("0 example");
        String str = "ABCD ABCE ABCDFABCG";
        Pattern pattern = Pattern.compile("ABCD");
        Matcher matcher = pattern.matcher(str);
        while ((matcher.find())) {
            System.out.println("Position: " + matcher.start() + " " + matcher.group());
        }

        System.out.println("___________________________________________");
        System.out.println("1 example");
        String str1 = "ADFABCG";
        Pattern pattern1 = Pattern.compile("[ABC]");
        Matcher matcher1 = pattern1.matcher(str1);
        while ((matcher1.find())) {
            System.out.println("Position: " + matcher1.start() + " " + matcher1.group());
        }

        System.out.println("___________________________________________");
        System.out.println("2 example");
        String str2 = "ABDOP";
        Pattern pattern2 = Pattern.compile("AB[C-F]OP");
        Matcher matcher2 = pattern2.matcher(str2);
        while ((matcher2.find())) {
            System.out.println("Position: " + matcher2.start() + " " + matcher2.group());
        }

        System.out.println("___________________________________________");
        System.out.println("3 example");
        String str3 = "abcd abce abc5abcg6abch";
        Pattern pattern3 = Pattern.compile("abc[e-g4-7]");
        Matcher matcher3 = pattern3.matcher(str3);
        while ((matcher3.find())) {
            System.out.println("Position: " + matcher3.start() + " " + matcher3.group());
        }

        System.out.println("___________________________________________");
        System.out.println("4 example");
        String str4 = "abcd abce abc5abcg6abch";
        Pattern pattern4 = Pattern.compile("abc[^e-g4-7]");//^ - nor
        Matcher matcher4 = pattern4.matcher(str4);
        while ((matcher4.find())) {
            System.out.println("Position: " + matcher4.start() + " " + matcher4.group());
        }

        System.out.println("___________________________________________");
        System.out.println("5 example");
        String str5 = "abcd abce abc5abcg6abch";
        Pattern pattern5 = Pattern.compile("abc(e|5)");//| - or
        Matcher matcher5 = pattern5.matcher(str5);
        while ((matcher5.find())) {
            System.out.println("Position: " + matcher5.start() + " " + matcher5.group());
        }

        System.out.println("___________________________________________");
        System.out.println("6 example");
        String str6 = "abcd abce abc5abcg6abch";
        Pattern pattern6 = Pattern.compile("abc.");//. - any symbol
        Matcher matcher6 = pattern6.matcher(str6);
        while ((matcher6.find())) {
            System.out.println("Position: " + matcher6.start() + " " + matcher6.group());
        }

        System.out.println("___________________________________________");
        System.out.println("7 example");
        String str7 = "abcd abce abc5abcg6abch";
        Pattern pattern7 = Pattern.compile("ch$");//$ - end of line
        Matcher matcher7 = pattern7.matcher(str7);
        while ((matcher7.find())) {
            System.out.println("Position: " + matcher7.start() + " " + matcher7.group());
        }

        System.out.println("___________________________________________");
        System.out.println("8 example");
        String str8 = "abcd abce abc5abcg6abch";
        Pattern pattern8 = Pattern.compile("\\d");//\d - means any number from 0 to 9, the same as [0-9]
        Matcher matcher8 = pattern8.matcher(str8);
        while ((matcher8.find())) {
            System.out.println("Position: " + matcher8.start() + " " + matcher8.group());
        }

        System.out.println("___________________________________________");
        System.out.println("9 example");
        String str9 = "abcd abce abc5abcg6abch";
        Pattern pattern9 = Pattern.compile("\\w");//\w - [A-Za-z0-9_]
        Matcher matcher9 = pattern9.matcher(str9);
        while ((matcher9.find())) {
            System.out.println("Position: " + matcher9.start() + " " + matcher9.group());
        }

        System.out.println("___________________________________________");
        System.out.println("10 example");
        String str10 = "abcd abce abc5abcg6abch";
        Pattern pattern10 = Pattern.compile("\\w+");//\w - [A-Za-z0-9_] more than one symbol
        Matcher matcher10 = pattern10.matcher(str10);
        while ((matcher10.find())) {
            System.out.println("Position: " + matcher10.start() + " " + matcher10.group());
        }

        System.out.println("___________________________________________");
        System.out.println("10 example");
        String str11 = "abcd be poka zaur hjs afafasfaf";
        Pattern pattern11 = Pattern.compile("\\w{4}");//4 any symbols
        Matcher matcher11 = pattern11.matcher(str11);
        while ((matcher11.find())) {
            System.out.println("Position: " + matcher11.start() + " " + matcher11.group());
        }
    }
}
