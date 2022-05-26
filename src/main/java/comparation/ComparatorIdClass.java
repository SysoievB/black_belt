package comparation;

import java.util.*;

class MainClass {
    public static void main(String[] args) {
        List<EmployeeComp> list1 = new ArrayList<>(Arrays.asList(
                new EmployeeComp(10, "Tom", "Soyer", 1200),
                new EmployeeComp(22, "Ann", "Soyer", 800),
                new EmployeeComp(3, "Nikolai", "Verman", 700)
        ));
        System.out.println("before sort");
        System.out.println(list1);
        Collections.sort(list1, new ComparatorIdClass());
        System.out.println("after sort");
        System.out.println(list1);

        Comparator<EmployeeComp> nameComparator = Comparator.comparing(EmployeeComp::getName);
        Collections.sort(list1, nameComparator);
        System.out.println("after name sort");
        System.out.println(list1);
    }
}

public class ComparatorIdClass implements Comparator<EmployeeComp> {
    @Override
    public int compare(EmployeeComp emp1, EmployeeComp emp2) {
        return emp1.id.compareTo(emp2.id);
    }
}

class EmployeeComp {
    Integer id;
    String name;
    String surname;
    int salary;

    public EmployeeComp(Integer id, String name, String surname, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + surname + " " + salary;
    }
}