import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Student extends User {

    private double feeAmount;
    private List<String> registeredCourses;
    private Map<LocalDate, String> attendance;
    Filehandler filehandler;

    
    public Student()
    {
        super();
        this.registeredCourses = new ArrayList<>();
        filehandler = new Filehandler();
    }
    public Student(String name, int id, String email, String password) {
        super(name, id, email, password);
        this.feeAmount = 0;
        this.registeredCourses = new ArrayList<>();
    }

    public Student(String studentName, int studentId) {
        this.setName(studentName);
        this.setId(studentId);
    }
    public double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(double feeAmount) {
        this.feeAmount = feeAmount;
    }
    public void setRegisteredCourses(List<String> registeredCourses) {
        this.registeredCourses = registeredCourses;
    }
    public void markAttendance(LocalDate date, String status) {
        attendance.put(date, status);
    }
    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

 

    
    public String registerCourse(String courseName) {
        if (isStudentRegistered(courseName)) {
            return "Student already registered for course: " + courseName;
        } else {
            registeredCourses.add(courseName);
          
            writeStudentToCourseFile(courseName,this);
            return "Student registered for course: " + courseName;
        }
    }
    
    private void writeStudentToCourseFile(String courseName,Student s) {
        filehandler.writeStudentToCourseFile(courseName, s);
    }
    
    private boolean isStudentRegistered(String courseName) {
        int commaIndex = courseName.indexOf(",");
        if (commaIndex != -1) {
            courseName = courseName.substring(0, commaIndex).trim();
        }
    
        String fileName = courseName + ".txt";
        Path filePath = Paths.get(fileName);
    
        if (Files.exists(filePath)) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String name = parts[0].trim();
                        int id = Integer.parseInt(parts[1].trim());
                        if (name.equalsIgnoreCase(this.getName()) && id == this.getId()) {
                            return true;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file: " + e.getMessage());
            }
        }
    
        return false;
    }
    public boolean login(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[2].trim().equals(email) && parts[3].equals(password)) {
                    this.setId(Integer.parseInt(parts[1].trim()));
                    this.setName(parts[0].trim());
                    System.out.println(getName());

                    this.setEmail(email);
                    // Successful login
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the student info file.");
            e.printStackTrace();
        }
        // Invalid credentials or file read error
        return false;
    }

    public void updateStudentCoursesFile() {
        filehandler.updateStudentCoursesFile(this,registeredCourses);
    }
    public String viewAttendance(String courseName) {
        String attendanceFileName = this.getId()+ "_attendance_" + courseName + ".txt";
        File attendanceFile = new File(attendanceFileName);
        if (attendanceFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(attendanceFile))) {
                StringBuilder attendanceContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    attendanceContent.append(line).append("\n");
                }
                return attendanceContent.toString();
            } catch (IOException e) {
                System.out.println("An error occurred while reading the attendance file.");
                e.printStackTrace();
            }
        } else {
            return "No absent";
        }
    
        return ""; // Return an empty string if there is an error
    }
    public String viewMarks(String courseName) {
        String marksFileName = this.getId()+ "_marks_" + courseName + ".txt";
        File marksFile = new File(marksFileName);
        if (marksFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(marksFile))) {
                StringBuilder attendanceContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    attendanceContent.append(line).append("\n");
                }
                return attendanceContent.toString();
            } catch (IOException e) {
                System.out.println("An error occurred while reading the marks file.");
                e.printStackTrace();
            }
        } else {
            return "nothing marked";
        }
    
        return ""; // Return an empty string if there is an error
    }
    public String viewAnnouncement(String courseName) {
        String attendanceFileName = "announcement_" + courseName + ".txt";
        File attendanceFile = new File(attendanceFileName);
        if (attendanceFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(attendanceFile))) {
                StringBuilder attendanceContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    attendanceContent.append(line).append("\n");
                }
                return attendanceContent.toString();
            } catch (IOException e) {
                System.out.println("An error occurred while reading the attendance file.");
                e.printStackTrace();
            }
        } else {
            return "No announcements";
        }
    
        return ""; // Return an empty string if there is an error
    }
}
