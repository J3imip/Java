package lab.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.Serializable;

public class Write {
    public static void main(String[] args) {
        Student student1 = new Student("John Doe", 20, "ID001");
        Student student2 = new Student("Jane Doe", 19, "ID002");

        AcademicGroup group = new AcademicGroup("Group 1");
        group.addStudent(student1);
        group.addStudent(student2);

        try {
            FileOutputStream fos = new FileOutputStream("group.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry("group.ser"));

            ObjectOutputStream oos = new ObjectOutputStream(zos);
            oos.writeObject(group);

            oos.flush();
            zos.closeEntry();
            oos.close();
            zos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Student implements Serializable {
    private String name;
    private int age;
    private String id;

    public Student(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }
}

class AcademicGroup implements Serializable {
    private String groupName;
    private ArrayList<Student> students;

    public AcademicGroup(String groupName) {
        this.groupName = groupName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getGroupName() {
        return groupName;
    }
}