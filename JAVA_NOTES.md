## Java notes 

### Generics

```
public class GenericMethod {
//we should write <T> T in that way because we dont use generic in class name public class GenericMethod
    public static <T> T getSecondElement(List<T> arrayList) {
        return arrayList.get(1);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(2, 44, 4654, 8));
        var value = getSecondElement(list);

        System.out.println(value);
    }
}
```
generic class

```
class Info<T> {
    private T value;

    public Info(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + value + "}";
    }
}
```
Type erasure
