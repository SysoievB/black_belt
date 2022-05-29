package generics.game;

public class Game {
    public static void main(String[] args) {
        Schoolar schoolar1 = new Schoolar("ivan", 15);
        Schoolar schoolar2 = new Schoolar("denis", 12);
        Employee employee1 = new Employee("andrei", 45);
        Employee employee2 = new Employee("bogdan", 35);
        Student student1 = new Student("anna", 22);
        Student student2 = new Student("anastasia", 21);

        Team schoolTeam = new Team("schoolars");
        schoolTeam.addNewParticipant(schoolar1);
        schoolTeam.addNewParticipant(schoolar2);
        // schoolTeam.addNewParticipant(employee1);


        Team employeeTeam = new Team("workers");
        employeeTeam.addNewParticipant(employee1);
        employeeTeam.addNewParticipant(employee2);

        Team studentTeam = new Team("students");
        studentTeam.addNewParticipant(student1);
        studentTeam.addNewParticipant(student2);

        schoolTeam.playWith(employeeTeam);
    }
}
