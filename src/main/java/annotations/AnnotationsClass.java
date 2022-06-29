package annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class AnnotationsClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Parent parent = new Parent("dan");
        Child child = new Child("ann");
        parent.showInfo();
        child.showInfo();
        AddedEmployee addedEmployee = new AddedEmployee(1, "Ivan");
        System.out.println(addedEmployee);
        Class birthEmployeeField = Class.forName("annotations.AddedEmployee");
        Field field = birthEmployeeField.getDeclaredField("birthday");
        Annotation annotation = field.getAnnotation(MyAnnotation.class);
        System.out.println(annotation);

    }
}

class Parent {
    String name;

    public Parent(String name) {
        this.name = name;
    }

    @Deprecated
    void showInfo() {
        System.out.println("parent " + this.name);
    }
}

class Child extends Parent {
    public Child(String name) {
        super(name);
    }

    @Override
    void showInfo() {
        System.out.println("child " + this.name);
    }
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    int birth() default 1995;
}

class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


class AddedEmployee extends Employee {
    @MyAnnotation(birth = 2002)
    int birthday;

    public AddedEmployee(int id, String name) {
        super(id, name);
    }
}