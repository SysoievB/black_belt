package commonLang;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.Fraction;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ArrayNumberFractionCommonTest {

    @Test
    public void whenCalledtoString_thenCorrect() {
        String[] array = {"a", "b", "c"};
        assertThat(ArrayUtils.toString(array))
                .isEqualTo("{a,b,c}");
    }

    @Test
    public void whenCalledtoStringIfArrayisNull_thenCorrect() {
        assertThat(ArrayUtils.toString(null, "Array is null"))
                .isEqualTo("Array is null");
    }

    @Test
    public void whenCalledhashCode_thenCorrect() {
        String[] array = {"a", "b", "c"};
        assertThat(ArrayUtils.hashCode(array))
                .isEqualTo(997619);
    }

    @Test
    public void whenCalledtoMap_thenCorrect() {
        String[][] array = {{"1", "one", }, {"2", "two", }, {"3", "three"}};
        Map<String, String> map = new HashMap<>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        assertThat(ArrayUtils.toMap(array))
                .isEqualTo(map);
    }

    @Test
    public void whenCalledisSameLength_thenCorrect() {
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        assertThat(ArrayUtils.isSameLength(array1, array2))
                .isTrue();
    }

    @Test
    public void whenCalledIndexOf_thenCorrect() {
        int[] array = {1, 2, 3};
        assertThat(ArrayUtils.indexOf(array, 1, 0))
                .isEqualTo(0);
    }


    @Test
    public void whenCalledcompareWithIntegers_thenCorrect() {
        assertThat(NumberUtils.compare(1, 1))
                .isEqualTo(0);
    }

    @Test
    public void whenCalledcompareWithLongs_thenCorrect() {
        assertThat(NumberUtils.compare(1L, 1L))
                .isEqualTo(0);
    }

    @Test
    public void whenCalledcreateNumber_thenCorrect() {
        assertThat(NumberUtils.createNumber("123456"))
                .isEqualTo(123456);
    }

    @Test
    public void whenCalledisDigits_thenCorrect() {
        assertThat(NumberUtils.isDigits("123456")).isTrue();
    }

    @Test
    public void whenCalledgetFraction_thenCorrect() {
        assertThat(Fraction.getFraction(5, 6)).isInstanceOf(Fraction.class);
    }

    @Test
    public void givenTwoFractionInstances_whenCalledadd_thenCorrect() {
        Fraction fraction1 = Fraction.getFraction(1, 4);
        Fraction fraction2 = Fraction.getFraction(3, 4);
        assertThat(fraction1.add(fraction2).toString()).isEqualTo("1/1");
    }

    @Test
    public void givenTwoFractionInstances_whenCalledsubstract_thenCorrect() {
        Fraction fraction1 = Fraction.getFraction(3, 4);
        Fraction fraction2 = Fraction.getFraction(1, 4);
        assertThat(fraction1.subtract(fraction2).toString()).isEqualTo("1/2");
    }

    @Test
    public void givenTwoFractionInstances_whenCalledmultiply_thenCorrect() {
        Fraction fraction1 = Fraction.getFraction(3, 4);
        Fraction fraction2 = Fraction.getFraction(1, 4);
        assertThat(fraction1.multiplyBy(fraction2).toString()).isEqualTo("3/16");
    }
}
