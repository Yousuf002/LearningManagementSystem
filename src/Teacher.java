import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Teacher extends User {
   

    //MarksHandlerFactory m //= new MarksHandlerFactory();
    private Filehandler filehandler;
    private static final String FILENAME = "teachers.txt";
    private Course course;
    private Map<String, Student> students;
    public Teacher(String name, int id, String email, String password) {
        super(name, id, email, password);
    }

    public Teacher(String name, int id) {
        this.setName(name);
        this.setId(id);
        
    }
    public Teacher() {
        super();
        students = new HashMap<>();
        filehandler = new Filehandler();

    }

    public void addStudent(Student student) {
        students.put(student.getName(), student);
    }

    public Student getStudentByName(String name) {
        return students.get(name);
    }

    public void markStudentAbsent(Student student, LocalDate date) {
        student.markAttendance(date, "A");
    }
   
    // public void readStudentsFromFile(String fileName) {
    //     try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
    //         String line;
    //         while ((line = reader.readLine()) != null) {
    //             String[] parts = line.split(",");
    //             if (parts.length >= 2) {
    //                 String studentName = parts[0].trim();
    //                 int studentId;
    //                 try {
    //                     studentId = Integer.parseInt(parts[1].trim());
    //                 } catch (NumberFormatException e) {
    //                     System.out.println("Invalid student ID: " + parts[1]);
    //                     continue;
    //                 }
    //                 Student student = new Student(studentName, studentId);
    //                 addStudent(student);
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

   
    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean login(String email, String password) {
        try (Scanner scanner = new Scanner(new File(FILENAME))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[2].trim().equals(email) && parts[3].equals(password)) {
                    // Successful login
                    String name = parts[0].trim();
                    int id = Integer.parseInt(parts[1].trim());
                    this.setName(name);
                    this.setId(id);
                    this.setEmail(email);
                    this.setPassword(password);
                    return true;
                }
            }
            // Login failed
            return false;
        } catch (IOException e) {
            // Handle file I/O exception
            e.printStackTrace();
            return false;
        }
    }
    public Student getStudentByNameAndId(String name, int id) {
        for (Student student : students.values()) {
            if (student.getName().equals(name) && student.getId() == id) {
                return student;
            }
        }
        return null; // Student not found
    }

    public Student getStudentById(int studentId) {
        for (Student student : students.values()) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }
    public String markStudentAbsent(String courseName, String studentName, String studentIdString, LocalDate date) {
        if (courseName.isEmpty() || studentName.isEmpty() || studentIdString.isEmpty() || date == null) {
            return "Please enter course name, student name, student ID, and date.";
        }

        int studentId;
        try {
            studentId = Integer.parseInt(studentIdString);
        } catch (NumberFormatException e) {
            return "Invalid student ID. Please enter a valid integer value.";
        }

        boolean studentFound = false;

        // Check if the course-specific file exists
        String courseFileName = courseName + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                if (studentData[0].equals(studentName) && Integer.parseInt(studentData[1]) == studentId) {
                    studentFound = true;
                    break;
                }
            }
        } catch (IOException e) {
            return "An error occurred while reading the course-specific file.";
        }

        if (!studentFound) {
            return "Student not found in the course.";
        }

        // Create or update student-specific attendance file
        String attendanceFileName = studentId + "_attendance_" + courseName + ".txt";
        try (FileWriter writer = new FileWriter(attendanceFileName, true)) {
            writer.write("Absent on " + date.toString() + System.lineSeparator());
        } catch (IOException e) {
            return "An error occurred while updating the attendance file.";
        }

        return "Student marked absent on " + date.toString();
    }
    public String updateStudentMarks(String courseName, String studentName, String studentIdString, String marksType, int marks) {
        System.out.println(courseName + "  " + studentName);

        int studentId;
        try {
            studentId = Integer.parseInt(studentIdString);
        } catch (NumberFormatException e) {
            return "Invalid student ID. Please enter a valid integer value.";
        }
        
        boolean studentFound = false;
        System.out.println(courseName + "  " + studentName);
        // Check if the course-specific file exists
        String courseFileName = courseName + ".txt";
        System.out.println("  " + courseFileName);
        File courseFile = new File(courseFileName);
        if (courseFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(courseFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] studentData = line.split(",");
                    if (studentData[0].equals(studentName) && Integer.parseInt(studentData[1]) == studentId) {
                        studentFound = true;
                        break;
                    }
                }
            } catch (IOException e) {
                return "An error occurred while reading the course-specific file.";
            }
        }
    
        if (!studentFound) {
            return "Student not found in the course.";
        }
        Marks newMarks = MarksHandlerFactory.getInstance().createMarks(marksType, marks);

        // Create or update student-specific marks file
        String marksFileName = studentId + "_marks_" + courseName + ".txt";
        File marksFile = new File(marksFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(marksFile, true))) {
            writer.write(marksType + ": " + newMarks.getMarks());
            writer.newLine();
        } catch (IOException e) {
            return "An error occurred while updating the marks file.";
        }
    
        return "Marks updated successfully.";
    }
    
    public String Announcement(String courseName, String announcement) {
            if (courseName.isEmpty()) {
                return "Please select a course.";
            }
        
            // Create or update course-specific announcement file
            String announcementFileName = "announcement_" + courseName + ".txt";
            File announcementFile = new File(announcementFileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(announcementFile, true))) {
                writer.write("Announcement: " + announcement);
                writer.newLine();
            } catch (IOException e) {
                return "An error occurred while updating the announcement file.";
            }
        
            return "Announcement updated successfully.";
        }
        
    
}
