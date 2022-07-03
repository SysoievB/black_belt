package regex;

public class PrintfFormatClass {
    static void employeeInfo(Employee employee) {
        System.out.printf("%03d \t %-12s \t %-12s \t %,.1f \n",
                employee.id,
                employee.name,
                employee.surname,
                employee.salary * (1 + employee.bonus));
    }

    public static void main(String[] args) {
        Employee employee = new Employee(1, "Zaur", "Tregulow", 3000, 0.14);
        Employee employee1 = new Employee(2, "Maria", "Tregulowa", 2000, 0.25);
        Employee employee2 = new Employee(3, "Semen", "Sidorow", 5000, 0.1);

        employeeInfo(employee);
        employeeInfo(employee1);
        employeeInfo(employee2);

        String newString = String.format("%03d \t %-12s \t %-12s \t %,.1f \n",
                24,
                "Nazar",
                "Ivanov",
                2300 * (1 + 0.3));
        System.out.println(newString);
    }
}

class Employee {
    int id;
    String name;
    String surname;
    int salary;
    double bonus;

    public Employee(int id, String name, String surname, int salary, double bonus) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.bonus = bonus;
    }
}