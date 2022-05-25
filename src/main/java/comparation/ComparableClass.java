package comparation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComparableClass {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>(Arrays.asList(
                new Employee(10, "Tom", "Soyer", 1200),
                new Employee(2, "Ann", "Soyer", 800),
                new Employee(3, "Nikolai", "Verman", 700)
        ));
        System.out.println("before sort");
        System.out.println(list);
        Collections.sort(list);
        System.out.println("after sort");
        System.out.println(list);

    }
}

class Employee implements Comparable<Employee> {
    Integer id;
    String name;
    String surname;
    int salary;

    public Employee(Integer id, String name, String surname, int salary) {
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

    @Override
    public int compareTo(Employee anotherEmployee) {
        /*if (this.id == anotherEmployee.id) return 0;
        else if (this.id < anotherEmployee.id) return -1;
        else return 1;*/

        // return this.id - anotherEmployee.id;
        // return this.id.compareTo(anotherEmployee.id);

        int result = this.name.compareTo(anotherEmployee.name);
        if (result == 0) {
            result = this.surname.compareTo(anotherEmployee.surname);
        }
        return result;
    }
}