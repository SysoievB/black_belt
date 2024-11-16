package switch_;

/**
 * <code>
 * enum Color { RED, YELLOW, GREEN }
 * <p>
 * int numLetters = switch (color) {   // Error - not exhaustive!
 * <p>
 * case RED -> 3;
 * <p>
 * case GREEN -> 5;
 * <p>
 * }
 * </code>
 * <p>
 * This switch expression over an enum class is not exhaustive because the
 * anticipated input YELLOW is not covered. As expected, adding a case
 * label to handle the YELLOW enum constant is sufficient to make the
 * switch exhaustive:
 * <p>
 *     <code>
 * int numLetters = switch (color) {   // Exhaustive!
 * <p>
 * case RED -> 3;
 * <p>
 * case GREEN -> 5;
 * <p>
 * case YELLOW -> 6;
 * <p>
 * }</code>
 */

public class SwitchClass {
    public static void main(String[] args) {
        System.out.println(whenWithSwitchExpression(-34));
    }

    // Prior to Java 21
    static String formatter(Object obj) {
        String formatted = "unknown";
        if (obj instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (obj instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (obj instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (obj instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }

    // As of Java 21
    static String patternMatchingWithSwitchExpression(Object obj) {
        return switch (obj) {
            case Integer i -> String.format("int %d", i);
            case Long l -> String.format("long %d", l);
            case Double d -> String.format("double %f", d);
            case String s -> String.format("String %s", s);
            default -> obj.toString();
        };
    }

    static Integer whenWithSwitchExpression(Integer anyValue) {
        return switch (anyValue) {
            case null -> -1;
            case 1, 2 -> 1;
            case Integer i when i > 0 -> i;
            default -> 0;
        };
    }

    // Prior to Java 21
    static void testFooBarOld(String s) {
    // We have to put null check prior to switch statement to avoid NPE
        if (s == null) {
            System.out.println("Oops!");
            return;
        }
        switch (s) {
            case "Foo", "Bar" -> System.out.println("Great");
            default -> System.out.println("Ok");
        }
    }

    // As of Java 21
    static void testFooBarNew(String s) {
        switch (s) {
            case null -> System.out.println("Oops");
            case "Foo", "Bar" -> System.out.println("Great");
            default -> System.out.println("Ok");
        }
    }

    // As of Java 21
    static void testStringOld(String response) {
        switch (response) {
            case null -> {
            }
            case String s -> {
                if (s.equalsIgnoreCase("YES"))
                    System.out.println("You got it");
                else if (s.equalsIgnoreCase("NO"))
                    System.out.println("Shame");
                else
                    System.out.println("Sorry?");
            }
        }
    }

    // As of Java 21
    static void testStringNew(String response) {
        switch (response) {
            case null -> {
            }
            case String s
                    when s.equalsIgnoreCase("YES") -> {
                System.out.println("You got it");
            }
            case String s
                    when s.equalsIgnoreCase("NO") -> {
                System.out.println("Shame");
            }
            case String s -> {
                System.out.println("Sorry?");
            }
        }
    }
}
