import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

//import javafx.collections.ObservableList;

public class lms {
    private Admin a;
   private Teacher t;
    private Student s;
   // private User u;
   // private User u;
  
   public lms()
   {
     a = new Admin();
     t = new Teacher();
     s = new Student();
   } 
   Boolean AddTeacher(Teacher t) throws IOException
   {
    return this.a.addTeacher(t);

   }
   boolean studentLogin(String email,String password)
   {
      s = new Student();
        return this.s.login(email, password);
   }
   String getStudentName()
   {
    return this.s.getName();
   }
   Student getStudent()
   {

    return this.s;
   }
   String registerCourse(String selectedCourses)
   {
      return this.s.registerCourse(selectedCourses);
   }
   String viewAttendance(String cname)
   {
        return this.s.viewAttendance(cname);
   }
   String viewMarks(String m)
   {
     return this.s.viewMarks(m);
   }
   String viewAnnouncement(String cn)
   {
    return this.s.viewAnnouncement(cn);
   }
   boolean adminLogin(String email, String password)
   {
        return this.a.login(email, password);
   }
   String getAdminName()
   {
        return this.a.getName();
   }
   List<Teacher> readTeachersFromFile() throws IOException
   {
     return this.a.readTeachersFromFile();
   }
   boolean removeTeacher(String name, int id) throws IOException
   {
    return this.a.removeTeacher(name, id);
   }
   boolean addStudent(Student sd) throws IOException
   {
    return this.a.addStudent(sd);
   }
   boolean removeStudent(String name, int id) throws IOException
   {
        return this.a.removeStudent(name, id);
   }
   List<Student> readStudentsFromFile() throws IOException
   {
     return this.a.readStudentsFromFile();
   }
   List<Student> viewStudents() throws IOException
   {
     return this.a.viewStudents();
   }
   String getTeacherName()
   {
     return t.getName();
   }
   boolean TeacherLogin(String email,String password)
   {
        return this.t.login(email, password);
   }
   String markStudentAbsent(String s,String p, String r, LocalDate d)
   {
     return t.markStudentAbsent(s,p,r, d);
   }
   String Announcement(String p, String q)
   {
     return t.Announcement(p, q);
   }
   String UpdateStudentMarks(String r, String s,String t1,String u, int m)
   {
     return t.updateStudentMarks(r, s, t1, u, m);
   }
   List<Teacher> viewTeachers() throws IOException
   {
     return a.viewTeachers();
   }
   boolean addCourse(Course course) throws IOException
   {
     return a.addCourse(course);
   }
   boolean removeCourse(String s, String q) throws IOException
   {
     return a.removeCourse(s, q);
   }
   List<Course> viewCourses() throws IOException
   {
    return a.viewCourses();
   }
   String assignCourseToTeacher(Teacher t, Course c)
   {
     return a.assignCourseToTeacher(t, c);
   }
}
