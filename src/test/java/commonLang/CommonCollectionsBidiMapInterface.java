package commonLang;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommonCollectionsBidiMapInterface {
    private static BidiMap<String, String> bidi = new TreeBidiMap<>();

    @BeforeAll
    static void setUpBeforeClass() {
        bidi.put("One", "1");
        bidi.put("Two", "2");
        bidi.put("Three", "3");
    }

    @Test
    void get() {
        assertThat(bidi.get("One")).isEqualTo("1");
    }

    @Test
    void getKey() {
        assertThat(bidi.getKey("1")).isEqualTo("One");
    }

    @Test
    void inverseBidiMap() {
        Map<String, String> expectedMap = new LinkedHashMap<>();
        expectedMap.put("1", "One");
        expectedMap.put("2", "Two");
        expectedMap.put("3", "Three");

        assertThat(bidi.inverseBidiMap()).isEqualTo(expectedMap);
    }
}
