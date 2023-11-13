package lab3.task2;

// Person class
class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Person: %s", name);
    }
}

// Citizen class extending Person
class Citizen extends Person {
    private final String nationality;

    public Citizen(String name, String nationality) {
        super(name);
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nNationality: %s", nationality);
    }
}

// Student class extending Citizen
class Student extends Citizen {
    private final int studentId;

    public Student(String name, String nationality, int studentId) {
        super(name, nationality);
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nStudent ID: %s", studentId);
    }
}

// Employee class extending Person
class Employee extends Person {
    private final String position;

    public Employee(String name, String position) {
        super(name);
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nPosition: %s", position);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Creating an array of references to different objects in the hierarchy
        Person[] people = {
                new Person("John Doe"),
                new Citizen("Alice Smith", "American"),
                new Student("Bob Johnson", "Canadian", 12345),
                new Employee("Eve Williams", "Manager")
        };

        // Displaying information for each object
        for (Person person : people) {
            System.out.println(person + "\n"); // Adding a line break for better readability
        }
    }
}
