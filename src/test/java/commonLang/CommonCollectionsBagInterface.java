package commonLang;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.HashBag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommonCollectionsBagInterface {

    /**
     * A Bag defines a collection which, counts the number of times an object appears in the collection.
     * For example, if a Bag contains {a, a, b, c} then getCount("a") will return 2 while uniqueSet()
     * returns the unique values.
     * retainAll(retainList) removes elements that are not in retainList.
     */

    private static final Bag<String> bag = new HashBag<>();

    @BeforeAll
    static void setUp() {
        bag.add("a", 2);
        bag.add("b");
        bag.add("c");
        bag.add("d", 3);
    }


    @Test
    void getCount_returns_count_same_values() {
        assertThat(bag.getCount("d")).isEqualTo(3);
    }

    @Test
    void getCount_returns_count_unique_values() {
        assertThat(bag.uniqueSet()).isEqualTo(Set.of("a", "b", "c", "d"));
    }
}
