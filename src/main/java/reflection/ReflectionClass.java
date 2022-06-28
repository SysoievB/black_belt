package reflection;

import java.lang.reflect.*;
import java.util.Arrays;

public class ReflectionClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //creation
        Class employee1 = Class.forName("reflection.Employee");
        Class employee2 = Employee.class;
        Class employee3 = new Employee().getClass();

        //fields
        Field idField = employee1.getField("id");
        System.out.println("Type of id field: " + idField.getType());

        System.out.println("---------------------------------------------");
        System.out.println("All fields without private access modifier");
        Arrays.stream(employee1.getFields()).forEach(System.out::println);

        System.out.println("---------------------------------------------");
        System.out.println("All fields even with private access modifier");
        Arrays.stream(employee1.getDeclaredFields()).forEach(System.out::println);

        //methods
        System.out.println("---------------------------------------------");
        Method employeeMethod = employee1.getMethod("increment");
        System.out.println("Get only public method " + employeeMethod);
        System.out.println("Return type of increment method is " + employeeMethod.getReturnType()
                + ", parameter types " + Arrays.toString(employeeMethod.getParameterTypes()));

        System.out.println("---------------------------------------------");
        System.out.println("Get info about all public methods: ");
        Arrays.stream(employee1.getMethods()).forEach(System.out::println);

        System.out.println("---------------------------------------------");
        System.out.println("Get info about all methods: ");
        Arrays.stream(employee1.getDeclaredMethods()).forEach(System.out::println);

        System.out.println("---------------------------------------------");
        System.out.println("Get info about all public methods with Modifier.class: ");
        Arrays.stream(employee1.getDeclaredMethods())
                .filter(m -> Modifier.isPublic(m.getModifiers()))
                .forEach(System.out::println);

        //constructor
        System.out.println("---------------------------------------------");
        System.out.println("Constructor: " + employee1.getConstructor());

        System.out.println("---------------------------------------------");
        System.out.println("All constructors: ");
        Arrays.stream(employee1.getConstructors()).forEach(System.out::println);

        System.out.println("---------------------------------------------");
        System.out.println("Certain constructor: " + employee1.getConstructor(int.class));

        System.out.println("---------------------------------------------");
        var constructor = employee1.getConstructor(int.class).getParameterTypes();
        System.out.println("Parameter types constructor: " + Arrays.toString(constructor));

        //class
        Constructor<Employee> constructor1 = employee1.getConstructor();
        Object object1 = constructor1.newInstance();

        Constructor<Employee> constructor2 = employee2.getConstructor(int.class, String.class, String.class, double.class);
        Object object2 = constructor2.newInstance(5, "Dan", "IT", 0.0);
        System.out.println(object2);

        Method method = employee1.getMethod("setSalary", double.class);
        method.invoke(object2, 5453.78);
        System.out.println(object2);
    }
}

class Employee {
    public int id;
    public String name;
    public String department;
    private double salary;

    public Employee() {
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int increment() {
        return ++this.id;
    }

    private void changeDepartment(String newDepartment) {
        this.department = newDepartment;
        System.out.println("new department is: " + department);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}