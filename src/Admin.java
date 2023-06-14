import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private static final String FILENAME = "admin.txt";
    private String name;
    private String password;
    private String email;
    Filehandler filehandler;

    public Admin() {
        name = "";
        password = "";
        email = "";
        filehandler = new Filehandler();

    }

    public Admin(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public static boolean registerUser(String name, String email, String password) {
        try {
            File file = new File(FILENAME);

            // Check if the file exists, create it if it doesn't
            if (!file.exists()) {
                file.createNewFile();
            }

            // Check if the email already exists
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[2].equals(email)) {
                    // Email already exists, registration failed
                    scanner.close();
                    return false;
                }
            }
            scanner.close();

            // Append the new user information to the text file
            FileWriter writer = new FileWriter(file, true);
            writer.write(name + "," + password + "," + email + "\n");
            writer.close();

            // Successful registration
            return true;
        } catch (IOException ex) {
            // Handle file I/O exception
            ex.printStackTrace();
            return false;
        }
    }

    // private boolean isValidEmail(String email) {
    //     // Email validation regex pattern
    //     String emailPattern = "^(\\w+)([\\.-]?\\w+)*@nu\\.edu\\.pk$";
    //     // Check if email matches pattern
    //     if (email.matches(emailPattern)) {
    //         return true;
    //     }

    //     return false;
    // }

    // private boolean isValidPassword(String password) {
    //     // Password validation criteria
    //     boolean hasUppercase = false;
    //     boolean hasLowercase = false;
    //     boolean hasNumber = false;

    //     // Check each character in password
    //     for (int i = 0; i < password.length(); i++) {
    //         char c = password.charAt(i);

    //         if (Character.isUpperCase(c)) {
    //             hasUppercase = true;
    //         } else if (Character.isLowerCase(c)) {
    //             hasLowercase = true;
    //         } else if (Character.isDigit(c)) {
    //             hasNumber = true;
    //         }
    //     }

    //     // Check if password meets criteria
    //     if (hasUppercase && hasLowercase && hasNumber && password.length() >= 8) {
    //         return true;
    //     }

    //     return false;
    // }

    public  boolean login(String email, String password) {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                // File does not exist, no users registered yet
                return false;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 3 && parts[2].equals(email) && parts[1].equals(password)) {
            // Successful login
            scanner.close();
            this.setName(parts[0]);
            return true;
            }
            }
            scanner.close();     // Login failed
            return false;
        } catch (IOException ex) {
            // Handle file I/O exception
            ex.printStackTrace();
            return false;
        }
    }
    public boolean addTeacher(Teacher teacher) throws IOException {
        Path filePath = Path.of("teachers.txt");
    
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    
        List<Teacher> teachers = readTeachersFromFile(); // Read the existing teachers from file
    
        // Check if the teacher ID already exists
        for (Teacher existingTeacher : teachers) {
            if (existingTeacher.getId() == teacher.getId()) {
                System.out.println("Teacher with the same ID already exists.");
                return false; // Teacher ID already exists, return false
            }
        }
    
        String teacherData = teacher.getName() + "," + teacher.getId() + "," + teacher.getEmail() + "," + teacher.getPassword();
        String teacherDataWithNewLine = teacherData + System.lineSeparator();
    
        Files.write(filePath, teacherDataWithNewLine.getBytes(), StandardOpenOption.APPEND);
        return true; // Teacher added successfully, return true
    }
    public boolean removeTeacher(String name, int id) throws IOException {
        List<Teacher> teachers = readTeachersFromFile(); // Read the existing teachers from file

        // Find the teacher to remove
        Teacher teacherToRemove = null;
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name) && teacher.getId() == id) {
                teacherToRemove = teacher;
                break;
            }
        }

        // Remove the teacher if found
        if (teacherToRemove != null) {
            teachers.remove(teacherToRemove);
            writeTeachersToFile(teachers);
            System.out.println("Teacher removed: " + teacherToRemove);
            return true;
        } else {
            System.out.println("Teacher not found.");
            return false;
        }
    }

    public List<Teacher> readTeachersFromFile() throws IOException {
       return filehandler.readTeachersFromFile();
    }

    private void writeTeachersToFile(List<Teacher> teachers) throws IOException {
        filehandler.writeTeachersToFile(teachers);
    }
    public boolean addStudent(Student student) throws IOException {
        Path filePath = Path.of("students.txt");
    
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    
        List<Student> students = readStudentsFromFile(); // Read the existing students from file
    
        // Check if the student ID already exists
        for (Student existingStudent : students) {
            if (existingStudent.getId() == student.getId()) {
                System.out.println("Student with the same ID already exists.");
                return false; // Exit the method without writing to the file
            }
        }
    
        String studentData = student.getName() + "," + student.getId() + "," + student.getEmail() + "," + student.getPassword();
        String studentDataWithNewLine = studentData + System.lineSeparator();
    
        Files.write(filePath, studentDataWithNewLine.getBytes(), StandardOpenOption.APPEND);
    
        return true; // Student added successfully
    }
    public boolean removeStudent(String name, int id) throws IOException {
        List<Student> students = readStudentsFromFile(); // Read the existing students from file
    
        // Find the student to remove
        Student studentToRemove = null;
        for (Student student : students) {
            if (student.getName().equals(name) && student.getId() == id) {
                studentToRemove = student;
                break;
            }
        }
    
        // Remove the student if found
        if (studentToRemove != null) {
            students.remove(studentToRemove);
    
            // Read course names from course.txt
            List<String> courseNames = readCourseNamesFromFile();
    
            // Remove student from the course files
            for (String courseName : courseNames) {
                String courseFileName = courseName + ".txt";
                File courseFile = new File(courseFileName);
                if (courseFile.exists() && courseFile.isFile()) {
                    List<String> fileLines = new ArrayList<>();
                    try (BufferedReader reader = new BufferedReader(new FileReader(courseFile))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (!line.contains(name)) {
                                fileLines.add(line);
                            }
                        }
                    }
    
                    try (FileWriter writer = new FileWriter(courseFile)) {
                        for (String line : fileLines) {
                            writer.write(line + "\n");
                        }
                    }
                    System.out.println("Student removed from the course file: " + courseFileName);
                }
            }
    
            // Delete associated attendance files
            for (String courseName : studentToRemove.getRegisteredCourses()) {
                String attendanceFileName = studentToRemove.getId() + "_attendance_" + courseName + ".txt";
                File attendanceFile = new File(attendanceFileName);
                if (attendanceFile.exists() && attendanceFile.isFile()) {
                    if (attendanceFile.delete()) {
                        System.out.println("Attendance file deleted: " + attendanceFileName);
                    } else {
                        System.out.println("Failed to delete the attendance file: " + attendanceFileName);
                    }
                }
    
                // Delete associated marks files
                String marksFileName = studentToRemove.getId() + "_marks_" + courseName + ".txt";
                File marksFile = new File(marksFileName);
                if (marksFile.exists() && marksFile.isFile()) {
                    if (marksFile.delete()) {
                        System.out.println("Marks file deleted: " + marksFileName);
                    } else {
                        System.out.println("Failed to delete the marks file: " + marksFileName);
                    }
                }
            }
    
            writeStudentsToFile(students); // Update the student file
            System.out.println("Student removed: " + studentToRemove.getName());
            return true;
        } else {
            System.out.println("Student not found.");
            return false;
        }
    }
    
    private List<String> readCourseNamesFromFile() throws IOException {
       return filehandler.readCourseNamesFromFile();
    }
    private void writeStudentsToFile(List<Student> students) throws IOException {
       filehandler.writeStudentsToFile(students);
    }
    public List<Student> readStudentsFromFile() throws IOException {
       return filehandler.readStudentsFromFile();
    }
    public List<Student> viewStudents() throws IOException {
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
    public List<Teacher> viewTeachers() throws IOException {
        List<Teacher> teachers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("teachers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int id = Integer.parseInt(parts[1]);
                String email = parts[2];
                String password = parts[3];
                Teacher student = new Teacher(name, id, email, password);
                teachers.add(student);
            }
        }

        return teachers;
    }
    public boolean addCourse(Course course) throws IOException {
        Path filePath = Path.of("courses.txt");
    
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    
        List<Course> courses = readCoursesFromFile(); // Read the existing courses from file
    
        // Check if the course ID or name already exists
        for (Course existingCourse : courses) {
            if (existingCourse.getName().equals(course.getName()) || existingCourse.getUniqueId().equals(course.getUniqueId())) {
                System.out.println("Course with the same ID or name already exists.");
                return false; // Exit the method without writing to the file
            }
        }
    
        String courseData = course.getName() + "," + course.getUniqueId() + "," + course.getCrdthrs();
        String courseDataWithNewLine = courseData + System.lineSeparator();
    
        Files.write(filePath, courseDataWithNewLine.getBytes(), StandardOpenOption.APPEND);
    
        // Create a new file with the course name
        String courseFileName = course.getName() + ".txt";
        Path courseFilePath = Path.of(courseFileName);
        Files.createFile(courseFilePath);
    
        return true;
    }
    
    public boolean removeCourse(String name, String id) throws IOException {
        List<Course> courses = readCoursesFromFile(); // Read the existing student from file

        // Find the student to remove
        Course courseToRemove = null;
        for (Course course : courses) {
            if (course.getName().equals(name) && course.getUniqueId().equals(id)) {
                courseToRemove = course;
                break;
            }
        }

        // Remove the student if found
        if (courseToRemove != null) {
            courses.remove(courseToRemove);
            writeCoursesToFile(courses); //
            System.out.println("Student removed: " + courseToRemove);
        return true;
        } else {
        System.out.println("Course not found.");
        return false;
        }
    }
    private void writeCoursesToFile(List<Course> courses) throws IOException {
        filehandler.writeCoursesToFile(courses);
    }
    private List<Course> readCoursesFromFile() throws IOException {
       return filehandler.readCoursesFromFile();
    }
    public List<Course> viewCourses() throws IOException {
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
    
    public String assignCourseToTeacher(Teacher teacher, Course course) {
        try {
            if (teacherExists(teacher) && courseExists(course) && !isTeacherAlreadyAssigned(teacher, course)) {
                String courseAssignment = teacher.getName() + "," + teacher.getId() + "," +
                        course.getName() + "," + course.getUniqueId();
    
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("teachers_courses.txt", true))) {
                    writer.write(courseAssignment);
                    writer.newLine();
    
                    // Update the teacher object with the assigned course
                    teacher.setCourse(course);
                //}
                        return "Course assigned successfully and teacher assigned to the course file.";
                //   catch (IOException e) {
                //         return "An error occurred while assigning the teacher to the course file.";
                //     }
                } catch (IOException e) {
                    return "An error occurred while writing to the file.";
                }
            } else {
                return "Teacher or course not found or already assigned.";
            }
        } catch (IOException e) {
            return "An error occurred while reading the file.";
        }
    }
    private static boolean teacherExists(Teacher teacher) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("teachers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String existingTeacherName = parts[0].trim();
                int existingTeacherId = Integer.parseInt(parts[1]);
                if (existingTeacherName.equals(teacher.getName()) && existingTeacherId == teacher.getId()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean courseExists(Course course) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("courses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String existingCourseName = parts[0].trim();
                String existingCourseId = parts[1].trim();
                if (existingCourseName.equals(course.getName()) && existingCourseId.equals(course.getUniqueId())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean isTeacherAlreadyAssigned(Teacher teacher, Course course) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("teachers_courses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String existingTeacherName = parts[0].trim();
                int existingTeacherId = Integer.parseInt(parts[1].trim());
                String existingCourseName = parts[2].trim();
                String existingCourseId = parts[3].trim();
                if (existingTeacherName.equals(teacher.getName()) && existingTeacherId == teacher.getId()
                        && existingCourseName.equals(course.getName()) && existingCourseId.equals(course.getUniqueId())) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean updateStudentFee(String studentName, int studentId, double feeAmount) {
        String fileName = "students.txt";
        String tempFileName = "temp.txt";
        String line;
        boolean found = false;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {
    
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int currentId;
                try {
                    currentId = Integer.parseInt(data[1].trim());
                } catch (NumberFormatException e) {
                    continue; // Skip invalid lines
                }
    
                if (data[0].equals(studentName) && currentId == studentId) {
                    data[data.length - 1] = data[data.length - 2] + "," + feeAmount;
                    line = String.join(",", data);
                    found = true;
                }
                writer.write(line);
                writer.newLine();
            }
    
        } catch (IOException e) {
            System.out.println("An error occurred while updating the student fee.");
        }
    
        // Delete the original file
        File originalFile = new File(fileName);
        if (!originalFile.delete()) {
            System.out.println("An error occurred while updating the student fee.");
            return false;
        }
    
        // Rename the temporary file to the original file
        File tempFile = new File(tempFileName);
        if (!tempFile.renameTo(originalFile)) {
            System.out.println("An error occurred while updating the student fee.");
            return false;
        }
    
        if (found) {
            System.out.println("Student fee updated successfully.");
            return true;
        } else {
            System.out.println("Student not found.");
            return false;
        }
    }
    
    
    
}

   

 