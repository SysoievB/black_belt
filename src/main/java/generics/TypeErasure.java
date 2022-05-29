package generics;

public class TypeErasure {
    public static void main(String[] args) {

    }

    /*public void abc(Info1<String> info){
        String s = info.getValue();
    }*/

    public void abc(Info1<Integer> info){
        Integer i = info.getValue();
    }
}
class Info1<T> {
    private T value;

    public Info1(T value) {
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
