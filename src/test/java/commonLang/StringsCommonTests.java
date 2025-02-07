package commonLang;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringsCommonTests {
//https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html
    private static final String HELLO = "Hello";
    private static final String WORLD = "World";

    @Test
    void containsAny() {
        String string = "baeldung.com";
        boolean contained1 = StringUtils.containsAny(string, 'a', 'b', 'c');
        boolean contained2 = StringUtils.containsAny(string, 'x', 'y', 'z');
        boolean contained3 = StringUtils.containsAny(string, "abc");
        boolean contained4 = StringUtils.containsAny(string, "xyz");

        assertTrue(contained1);
        assertFalse(contained2);
        assertTrue(contained3);
        assertFalse(contained4);
    }

    @Test
    void containsIgnoreCase() {
        String string = "baeldung.com";
        boolean contained = StringUtils.containsIgnoreCase(string, "BAELDUNG");

        assertTrue(contained);
    }

    @Test
    void countMatches() {
        String string = "welcome to www.baeldung.com";
        int charNum = StringUtils.countMatches(string, 'w');
        int stringNum = StringUtils.countMatches(string, "com");

        assertEquals(4, charNum);
        assertEquals(2, stringNum);
    }

    @Test
    void appendPrepend() {
        String string = "baeldung.com";
        String stringWithSuffix = StringUtils.appendIfMissing(string, ".com");
        String stringWithPrefix = StringUtils.prependIfMissing(string, "www.");

        assertEquals("baeldung.com", stringWithSuffix);
        assertEquals("www.baeldung.com", stringWithPrefix);
    }

    @Test
    void caseChanging() {
        String originalString = "baeldung.COM";
        String swappedString = StringUtils.swapCase(originalString);
        String originalString1 = "baeldung";
        String capitalizedString = StringUtils.capitalize(originalString1);
        String originalString2 = "Baeldung";
        String uncapitalizedString = StringUtils.uncapitalize(originalString2);

        assertEquals("baeldung", uncapitalizedString);
        assertEquals("Baeldung", capitalizedString);
        assertEquals("BAELDUNG.com", swappedString);
    }

    @Test
    void reversing() {
        String originalString = "baeldung";
        String reversedString = StringUtils.reverse(originalString);
        String originalString1 = "www.baeldung.com";
        String reversedString1 = StringUtils.reverseDelimited(originalString1, '.');

        assertEquals("com.baeldung.www", reversedString1);
        assertEquals("gnudleab", reversedString);
    }

    @Test
    void rotate() {
        String originalString = "baeldung";
        String rotatedString = StringUtils.rotate(originalString, 4);

        assertEquals("dungbael", rotatedString);
    }

    @Test
    void difference() {
        String tutorials = "Baeldung Tutorials";
        String courses = "Baeldung Courses";
        String diff1 = StringUtils.difference(tutorials, courses);
        String diff2 = StringUtils.difference(courses, tutorials);

        assertEquals("Courses", diff1);
        assertEquals("Tutorials", diff2);
    }

    @Test
    void whenCalledisBlank_thenCorrect() {
        assertThat(StringUtils.isBlank(" ")).isTrue();
    }

    @Test
    void whenCalledisEmpty_thenCorrect() {
        assertThat(StringUtils.isEmpty("")).isTrue();
    }

    @Test
    void whenCalledisAllLowerCase_thenCorrect() {
        assertThat(StringUtils.isAllLowerCase("abd")).isTrue();
    }

    @Test
    void whenCalledisAllUpperCase_thenCorrect() {
        assertThat(StringUtils.isAllUpperCase("ABC")).isTrue();
    }

    @Test
    void whenCalledisMixedCase_thenCorrect() {
        assertThat(StringUtils.isMixedCase("abC")).isTrue();
    }

    @Test
    void whenCalledisAlpha_thenCorrect() {
        assertThat(StringUtils.isAlpha("abc")).isTrue();
    }

    @Test
    void whenCalledisAlphanumeric_thenCorrect() {
        assertThat(StringUtils.isAlphanumeric("abc123")).isTrue();
    }

    @Test
    void stringUtils_join() {
        assertThat(StringUtils.join(new String[]{HELLO, WORLD}, " "))
                .isEqualTo("Hello World");
    }

    @Test
    void stringUtils_repeat() {
        assertThat(StringUtils.repeat(HELLO, 3))
                .isEqualTo("HelloHelloHello");
    }

}
