package nested_classes.local_inner_class;

public class LocalInnerClass {
    public static void main(String[] args) {
        Math math = new Math();
        math.getResult();
    }
}

class Math {
    public void getResult() {

        class Division {
            private int firstValue;
            private int secondValue;

            public int getFirstValue() {
                return firstValue;
            }

            public void setFirstValue(int firstValue) {
                this.firstValue = firstValue;
            }

            public int getSecondValue() {
                return secondValue;
            }

            public void setSecondValue(int secondValue) {
                this.secondValue = secondValue;
            }

            public int getResultOfDivision() {
                return firstValue / secondValue;
            }

            public int getRemainderOfDivision() {
                return firstValue % secondValue;
            }
        }
        Division division = new Division();
        division.setFirstValue(10);
        division.setSecondValue(2);
        System.out.println(division.getResultOfDivision());
        System.out.println(division.getRemainderOfDivision());
    }
}