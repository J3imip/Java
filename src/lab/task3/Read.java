package lab.task3;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Read {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("group.zip");
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();

            if (ze != null) {
                ObjectInputStream ois = new ObjectInputStream(zis);
                AcademicGroup group = (AcademicGroup) ois.readObject();

                System.out.println("Group Name: " + group.getGroupName());
                for (Student student : group.getStudents()) {
                    System.out.println("Student Name: " + student.getName());
                    System.out.println("Student Age: " + student.getAge());
                    System.out.println("Student ID: " + student.getId());
                }

                ois.close();
            }

            zis.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}