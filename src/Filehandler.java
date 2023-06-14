import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Filehandler {
    
    public List<Teacher> readTeachersFromFile() throws IOException {
        List<Teacher> teachers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("teachers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int id = Integer.parseInt(parts[1]);
                String email = parts[2];
                String password = parts[3];
                Teacher teacher = new Teacher(name, id, email, password);
                teachers.add(teacher);
            }
        }

        return teachers;
    }
    public void writeTeachersToFile(List<Teacher> teachers) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("teachers.txt"))) {
            for (Teacher teacher : teachers) {
                writer.write(teacher.getName() + "," + teacher.getId() + "," + teacher.getEmail() + "," + teacher.getPassword());
                writer.newLine();
            }
        }
    }
    public List<String> readCourseNamesFromFile() throws IOException {
        List<String> courseNames = new ArrayList<>();
        File courseFile = new File("courses.txt");
        if (courseFile.exists() && courseFile.isFile()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(courseFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 0) {
                        courseNames.add(parts[0].trim());
                    }
                }
            }
        }
        return courseNames;
    }
    public void writeStudentsToFile(List<Student> students) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(student.getName() + "," + student.getId() + "," + student.getEmail() + "," + student.getPassword());
                writer.newLine();
            }
        }
    }
    public List<Student> readStudentsFromFile() throws IOException {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int id = Integer.parseInt(parts[1]);
                String email = parts[2];
                String password = parts[3];
                Student student = new Student(name, id, email, password);
                students.add(student);
            }
        }

        return students;
    }
    public void writeCoursesToFile(List<Course> courses) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("courses.txt"))) {
            for (Course course : courses) {
                writer.write(course.getName() + "," + course.getUniqueId() + "," + course.getCrdthrs());
                writer.newLine();
            }
        }
    }
    public List<Course> readCoursesFromFile() throws IOException {
        List<Course> courses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("courses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String id = parts[1];
                int crdthrs = Integer.parseInt(parts[2]);
                Course course = new Course(name, id,crdthrs);
                courses.add(course);
            }
        }

        return courses;
    }
    public void writeStudentToCourseFile(String courseName,Student s) {
        int commaIndex = courseName.indexOf(",");
        if (commaIndex != -1) {
            courseName = courseName.substring(0, commaIndex).trim();
        }
    
        String fileName = courseName + ".txt";
        Path filePath = Paths.get(fileName);
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String studentInfo = s.getName() + "," + s.getId();
            writer.write(studentInfo);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        String fileName2 = "student_courses.txt";
        Path filePath2 = Paths.get(fileName2);
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName2, true))) {
            String studentInfoCourseinfo = s.getName() + "," + s.getId() + "," + courseName;
            writer.write(studentInfoCourseinfo);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public void updateStudentCoursesFile(Student s,List<String> registeredCourses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_courses.txt", true))) {
            for (String courseName : registeredCourses) {
                int index = courseName.indexOf(',');
                String courseFileName = (index != -1) ? courseName.substring(0, index) : courseName;
                courseFileName = courseFileName.trim() + ".txt";
                
                try (BufferedWriter courseWriter = new BufferedWriter(new FileWriter(courseFileName, true))) {
                    // Write student name and ID to the course-specific file
                    courseWriter.write(s.getName() + "," + s.getId());
                    courseWriter.newLine();
                    System.out.println("Course file " + courseFileName + " updated successfully.");
                } catch (IOException e) {
                    System.out.println("An error occurred while updating the course file " + courseFileName);
                    e.printStackTrace();
                }
    
                // Write to student_courses.txt
                writer.write(s.getId() + "," + s.getName() + "," + courseName);
                writer.newLine();
            }
            writer.flush();
            System.out.println("Student courses file updated successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while updating the student courses file.");
            e.printStackTrace();
        }
    }
}
