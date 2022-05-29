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
#### Type erasure 

- info about generic in compiling erasuring
```
public void abc(Info1<String> info){//compiler see both methods like abc(Info info)
        String s = info.getValue();
    }

    public void abc(Info1<Integer> info){
        Integer i = info.getValue();
    }
```
#### Wildcards
```
        List<Number> list = new ArrayList<Integer>(); //do not compile
        List<?> list1 = new ArrayList<Integer>();//compile
        list1.add(5);//do not compile
        
        //where we can use wildcards 
        static void showListInfo(List<?> list) {
        System.out.println(list);
    }
```
? - means any type
we can write Object but it will be the collection with Objects only
