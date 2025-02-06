package commonLang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollectionUtilsTests {

    private final Customer customer1 = new Customer(1, "Daniel", new Address("locality1", "city1"));
    private final Customer customer2 = new Customer(2, "Fredrik", new Address("locality2", "city2"));
    private final Customer customer3 = new Customer(3, "Kyle", new Address("locality3", "city3"));
    private final Customer customer4 = new Customer(4, "Bob", new Address("locality4", "city4"));
    private final Customer customer5 = new Customer(5, "Cat", new Address("locality5", "city5"));
    private final Customer customer6 = new Customer(6, "John", new Address("locality6", "city6"));

    private List<Customer> list1 = Arrays.asList(customer1, customer2, customer3);
    private List<Customer> list2 = Arrays.asList(customer4, customer5, customer6);
    private List<Customer> list3 = Arrays.asList(customer1, customer2);


    @Test
    public void givenList_whenAddIgnoreNull_thenNoNullAdded() {
        CollectionUtils.addIgnoreNull(list1, null);

        assertFalse(list1.contains(null));
    }

    @Test
    public void givenNonEmptyList_whenCheckedIsNotEmpty_thenTrue() {
        assertTrue(CollectionUtils.isNotEmpty(list1));
    }

    @Test
    public void givenCustomerListAndASubcollection_whenChecked_thenTrue() {
        assertTrue(CollectionUtils.isSubCollection(list3, list1));
    }

    @Test
    public void givenTwoLists_whenSubtracted_thenCheckElementNotPresentInA() {
        Collection<Customer> result = CollectionUtils.subtract(list1, list3);
        assertFalse(result.contains(customer1));
    }

    @Test
    public void givenTwoLists_whenUnioned_thenCheckElementPresentInResult() {
        Collection<Customer> union = CollectionUtils.union(list1, list2);

        assertTrue(union.contains(customer1));
        assertTrue(union.contains(customer4));
    }


    @Getter
    @Setter
    @AllArgsConstructor
    private static class Customer {
        private Integer id;
        private String name;
        private Address address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class Address {
        private String locality;
        private String city;
    }
}
