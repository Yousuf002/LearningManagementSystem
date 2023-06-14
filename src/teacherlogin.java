

//import javafx.application.Application;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class GlobalData2 {
    public static lms l;
}

public class teacherlogin   {
    public Scene scene;

   public  teacherlogin() {
    GlobalData2.l = new lms();
       TextField emailTextField = new TextField();
       PasswordField passwordTextField = new PasswordField();
   
       Label passwordLabel = new Label("Password ");
       Label emailLabel = new Label("Email ");
       emailLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       passwordLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       System.out.println("hello  " + GlobalData2.l.getTeacherName());

   
       GridPane gridPane = new GridPane();
       //gridPane.setStyle("-fx-background-color: #CCCCCC;");
       gridPane.setAlignment(Pos.CENTER);
       gridPane.setPadding(new Insets(10));
       gridPane.setHgap(10);
       gridPane.setVgap(10);
       gridPane.setPadding(new Insets(50, 10, 10, 10));
       gridPane.add(emailLabel, 0, 0);
       gridPane.add(emailTextField, 1, 0);
       gridPane.add(passwordLabel, 0, 1);
       gridPane.add(passwordTextField, 1, 1);
   
       Button loginButton = new Button("Login");
       loginButton.setStyle("-fx-background-color: white; -fx-text-fill: #005cb9; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #005cb9; -fx-border-width: 1px;");
       gridPane.add(loginButton, 1, 2);
       // create image view and set image
       Image image = new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG");
       System.out.println("Image path: " + image.getUrl()); // print the image path
   
       ImageView imageView = new ImageView(image);
         imageView.setFitWidth(398); // set fit width to 200 pixels
       //  imageView.setFitHeight(200); // set fit height to 200 pixels
        imageView.setStyle("-fx-background-color: transparent;");
   
       
       // create stack pane and add nodes
       StackPane stackPane = new StackPane();
       stackPane.getChildren().addAll(imageView, gridPane);
       StackPane.setAlignment(imageView, Pos.TOP_CENTER);
       stackPane.setStyle("-fx-background-color: white;");
       scene = new Scene(stackPane, 400, 300);
   
       // handle login button click event
       Label errorLabel = new Label();
       errorLabel.setTextFill(javafx.scene.paint.Color.RED);

       gridPane.add(errorLabel, 1, 3);
       loginButton.setOnAction(e -> {
           String email = emailTextField.getText();
           String password = passwordTextField.getText();

               if (GlobalData2.l.TeacherLogin(email, password)) {
                   //Successful login
                   System.out.println("Login successful");
                   TeacherHomescreen t = new TeacherHomescreen();
                   Scene teacherScene = t.scene;
                   Stage primaryStage = new Stage();
                   primaryStage.setScene(teacherScene);
                   primaryStage.setTitle("Teacher Portal");
                   primaryStage.show();
               } else {
                   // Failed login
                   errorLabel.setText("Invalid email or password");
               }
       });


   }
   
  
}
class TeacherHomescreen {
    Scene scene;

    public TeacherHomescreen() {
        Image logoImage = new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(398);
        Text nameLabel = new Text("Hello, " + GlobalData2.l.getTeacherName()); // Assuming Admin class has a static method getName() that returns the name

        Text welcomeLabel = new Text("Welcome to Teacher Portal");
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        nameLabel.setFont(Font.font("Times New Roman", FontWeight.LIGHT, 12));

        Button announcementButton = new Button("Announcement");
        announcementButton.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px;-fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button attendanceButton = new Button("Attendance");
        attendanceButton.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button uploadMarksbButton = new Button("Upload Marks");
        uploadMarksbButton.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button btn = new Button("more features later");
        btn.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        String hoverStyle = "-fx-background-color: #D3D3D3; -fx-text-fill: #005cb9; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-height: 160px; -fx-pref-width: 160px; -fx-font-family: 'Times New Roman'";
        announcementButton.setOnMouseEntered(e -> announcementButton.setStyle(hoverStyle));
        announcementButton.setOnMouseExited(e -> announcementButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        attendanceButton.setOnMouseEntered(e -> attendanceButton.setStyle(hoverStyle));
        attendanceButton.setOnMouseExited(e -> attendanceButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        uploadMarksbButton.setOnMouseEntered(e -> uploadMarksbButton.setStyle(hoverStyle));
        uploadMarksbButton.setOnMouseExited(e -> uploadMarksbButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));


        announcementButton.setOnAction(e -> {
            AnnouncementGUI a = new AnnouncementGUI();
            Scene muserScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(muserScene);
            primaryStage.setTitle("View Courses");
            primaryStage.show();
        });
        attendanceButton.setOnAction(e -> {
            AttendanceGUI a = new AttendanceGUI(GlobalData2.l.getTeacherName());
            Scene coursesScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(coursesScene);
            primaryStage.setTitle("Attendance");
            primaryStage.show();
        });
        uploadMarksbButton.setOnAction(e -> {
            MarksGUI a = new MarksGUI(GlobalData2.l.getTeacherName());
            Scene marksScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(marksScene);
            primaryStage.setTitle("Upload Marks");
            primaryStage.show();
        });
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.add(announcementButton, 0, 0);
        buttonGrid.add(attendanceButton, 1, 0);
        buttonGrid.add(uploadMarksbButton, 0, 1);
       buttonGrid.add(btn, 1, 1);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(logoImageView,nameLabel, welcomeLabel, buttonGrid);

        scene = new Scene(vBox, 400, 300);
    }
}
class AttendanceGUI {
    private TableView<String> courseTable;
    private DatePicker datePicker;
    private String loggedInTeacher;
    private String selectedCourse;
    private BorderPane root;
    public Scene scene;

    public AttendanceGUI(String loggedInTeacher) {
        this.loggedInTeacher = loggedInTeacher;

        Label courseLabel = new Label("Select Course:");
        courseTable = new TableView<>();
        initializeCourseTable();
        courseTable.setOnMouseClicked(e -> updateStudentTable());

        ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
        imageView.setFitWidth(598); // Adjust the width of the image
        imageView.setPreserveRatio(true);

        Label dateLabel = new Label("Date:");
        datePicker = new DatePicker();

        VBox courseBox = new VBox(10);
        courseBox.setPadding(new Insets(10));
        courseBox.getChildren().addAll(courseLabel, courseTable, dateLabel, datePicker);

        root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        root.setTop(imageView); // Position the image at the top center
        BorderPane.setMargin(imageView, new Insets(10));

        root.setLeft(courseBox);
        scene = new Scene(root, 600, 400);

        updateCourseTable();
    }

    private void initializeCourseTable() {
        TableColumn<String, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        courseTable.getColumns().add(courseColumn);
    }

    private void updateCourseTable() {
        try (BufferedReader reader = new BufferedReader(new FileReader("teachers_courses.txt"))) {
            ObservableList<String> courses = FXCollections.observableArrayList();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String teacher = data[0];
                String course = data[2];
                if (teacher.equals(loggedInTeacher)) {
                    courses.add(course);
                } else {
                    System.out.println("no teacher");
                }
            }
            courseTable.setItems(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateStudentTable() {
        selectedCourse = courseTable.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            TableView<Student> studentTable = new TableView<>();
            initializeStudentTable(studentTable);
            ObservableList<Student> students = getStudents(selectedCourse);
            studentTable.setItems(students);
            root.setCenter(studentTable);
        } else {
            root.setCenter(null);
        }
    }

    private void initializeStudentTable(TableView<Student> studentTable) {
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        studentTable.getColumns().addAll(nameColumn, idColumn);
        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                studentSelected(newSelection);
            }
        });
    }

    private ObservableList<Student> getStudents(String courseName) {
        String courseFileName = courseName + ".txt";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            ObservableList<Student> students = FXCollections.observableArrayList();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                String name = studentData[0];
                int id = Integer.parseInt(studentData[1].trim()); // Trim leading/trailing spaces before parsing
                students.add(new Student(name, id));
            }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private void studentSelected(Student student) {
        Stage studentStage = new Stage();
        studentStage.setTitle("Student Details");

        Label studentNameLabel = new Label("Student Name: " + student.getName());
        Label studentIdLabel = new Label("Student ID: " + student.getId());

        Button markAbsentButton = new Button("Mark Absent");
        markAbsentButton.setPrefWidth(120);
        markAbsentButton.setPrefHeight(30);
        markAbsentButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px;");
        markAbsentButton.setOnAction(e -> markStudentAbsent(student));

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(studentNameLabel, studentIdLabel, markAbsentButton);

        Scene studentScene = new Scene(root, 400, 200);
        studentStage.setScene(studentScene);
        studentStage.show();
    }

    private void markStudentAbsent(Student student) {
        LocalDate date = datePicker.getValue();

        if (selectedCourse != null && student != null && date != null) {
            String studentName = student.getName();
            String studentIdString = String.valueOf(student.getId());

            String result = GlobalData2.l.markStudentAbsent(selectedCourse, studentName, studentIdString, date);
            showAlert(result);

            datePicker.setValue(null);
        } else {
            showAlert("Please select a course, a student, and choose a date.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Attendance");
        alert.setHeaderText(null);

        alert.setContentText(message);
        alert.showAndWait();
    }
}
//  class MarksGUI {
//     private TextField courseNameField;
//     private TextField studentNameField;
//     private TextField studentIdField;
//     private ComboBox<String> marksTypeComboBox;
//     private TextField marksField;
//     private Button markButton;
//     public Scene scene;
//     //private AttendanceManager attendanceManager;

//     public MarksGUI() {
//         //  attendanceManager = new AttendanceManager();

//         Label courseNameLabel = new Label("Course Name:");
//         courseNameField = new TextField();

//         Label studentNameLabel = new Label("Student Name:");
//         studentNameField = new TextField();
//         ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
//         imageView.setFitWidth(598); // Adjust the width of the image
//         imageView.setPreserveRatio(true);
//         Label studentIdLabel = new Label("Student ID:");
//         studentIdField = new TextField();

//         Label marksTypeLabel = new Label("Marks Type:");
//         marksTypeComboBox = new ComboBox<>();
//         marksTypeComboBox.getItems().addAll("Assignment", "Quiz", "Final");

//         Label marksLabel = new Label("Marks:");
//         marksField = new TextField();

//         markButton = new Button("Mark");
//         markButton.setOnAction(e -> markStudent());

//         VBox root = new VBox(10);
//         root.setPadding(new Insets(10));
//         root.getChildren().addAll(imageView,
//                 courseNameLabel, courseNameField,
//                 studentNameLabel, studentNameField,
//                 studentIdLabel, studentIdField,
//                 marksTypeLabel, marksTypeComboBox,
//                 marksLabel, marksField,
//                 markButton);

//         scene = new Scene(root, 400, 300);
//     }

//     private void markStudent() {
//         String courseName = courseNameField.getText();
//         String studentName = studentNameField.getText();
//         String studentIdString = studentIdField.getText();
//         String marksType = marksTypeComboBox.getValue();
//         String marksString = marksField.getText();

//         int marks;
//         try {
//             marks = Integer.parseInt(marksString);
//         } catch (NumberFormatException e) {
//             showAlert("Invalid marks. Please enter a valid integer value.");
//             return;
//         }

//         String result = GlobalData2.l.t.updateStudentMarks(courseName, studentName, studentIdString, marksType, marks);
//         showAlert(result);

//         // Clear the input fields
//         courseNameField.clear();
//         studentNameField.clear();
//         studentIdField.clear();
//         marksTypeComboBox.getSelectionModel().clearSelection();
//         marksField.clear();
//     }

//     private void showAlert(String message) {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle("Marks");
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
// }
class AnnouncementGUI {
    private TableView<String> courseTable;
    private TextArea announcementField;
    private Button announcementButton;
    public Scene scene;

    public AnnouncementGUI() {
        Label courseLabel = new Label("Select Course:");
        courseTable = new TableView<>();  // Create TableView for course selection
        populateCourseTable();  // Populate the course TableView
        courseTable.setPrefWidth(200); // Adjust the preferred width as needed

        TableColumn<String, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
        courseColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        courseTable.getColumns().add(courseColumn);

        Label announcementLabel = new Label("Announcement:");
        announcementField = new TextArea();
        announcementButton = new Button("Make Announcement");
        
            announcementButton.setPrefWidth(175);
            announcementButton.setPrefHeight(30);
            announcementButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px;");
        
        announcementButton.setOnAction(e -> makeAnnouncement());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(courseLabel, courseTable, announcementLabel, announcementField, announcementButton);

        scene = new Scene(root, 300, 300);
    }

    private void makeAnnouncement() {
        String selectedCourse = courseTable.getSelectionModel().getSelectedItem();  // Get the selected course
        String announcement = announcementField.getText();
        System.out.println("hello  " + GlobalData2.l.getTeacherName());
        if (selectedCourse == null || selectedCourse.isEmpty()) {
            showAlert("Please select a course.");
            return;
        }

        String result = GlobalData2.l.Announcement(selectedCourse, announcement);
        showAlert(result);

        // Clear the input fields
        courseTable.getSelectionModel().clearSelection();
        announcementField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Announcement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void populateCourseTable() {
        ObservableList<String> courseList = FXCollections.observableArrayList();  // Clear previous items

        // Read teacher_courses.txt file and populate the TableView with matching courses
        try (Scanner scanner = new Scanner(new File("teachers_courses.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(GlobalData2.l.getTeacherName())) {
                    courseList.add(parts[2]);  // Add matching course to the TableView
                }
            }
        } catch (FileNotFoundException e) {
            showAlert("teacher_courses.txt file not found.");
        }

        courseTable.setItems(courseList);
    }
}
class MarksGUI {
    private ComboBox<String> courseComboBox;
    private TableView<Student> studentTable;
    private ComboBox<String> marksTypeComboBox;
    private TextField marksField;
    private Button markButton;
    public Scene scene;
    String loggedInTeacher;

    public MarksGUI(String loggedInTeacher) {
        this.loggedInTeacher = loggedInTeacher;
        Label courseLabel = new Label("Select Course:");
        courseComboBox = new ComboBox<>();
        courseComboBox.setOnAction(e -> updateStudentTable());

        ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
        imageView.setFitWidth(398); // Adjust the width of the image
        imageView.setPreserveRatio(true);

        Label marksTypeLabel = new Label("Marks Type:");
        marksTypeComboBox = new ComboBox<>();
        marksTypeComboBox.getItems().addAll("Assignment", "Quiz", "Final");

        Label marksLabel = new Label("Marks:");
        marksField = new TextField();

        markButton = new Button("Mark");
            markButton.setPrefWidth(100);
            markButton.setPrefHeight(30);
            markButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px;");

        markButton.setOnAction(e -> markStudent());

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        // Create a separate container for the image view
        StackPane imageContainer = new StackPane();
        imageContainer.setAlignment(Pos.TOP_CENTER);
        imageContainer.getChildren().add(imageView);

        // Add the image container and other children to the root VBox
        root.getChildren().addAll(
                imageContainer,
                courseLabel, courseComboBox,
                marksTypeLabel, marksTypeComboBox,
                marksLabel, marksField,
                markButton
        );

        scene = new Scene(root, 400, 325);

        updateCourseComboBox();
    }

    private void updateCourseComboBox() {
        List<String> courses = readTeacherCourses();
        courseComboBox.getItems().addAll(courses);
    }

    private List<String> readTeacherCourses() {
        List<String> courses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("teachers_courses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 3 && data[0].equals(loggedInTeacher)) {
                    courses.add(data[2]);
                }
            }
        } catch (IOException e) {
            showAlert("An error occurred while reading teacher courses.");
        }

        return courses;
    }

    private void updateStudentTable() {
        String selectedCourse = courseComboBox.getValue();
        if (selectedCourse != null) {
            List<Student> studentList = readStudentList(selectedCourse);
            displayStudentList(studentList);
        }
    }

    private ObservableList<Student> readStudentList(String courseName) {
        String courseFileName = courseName + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(courseFileName))) {
            ObservableList<Student> students = FXCollections.observableArrayList();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                String name = studentData[0];
                int id = Integer.parseInt(studentData[1].trim()); // Trim leading/trailing spaces before parsing
                students.add(new Student(name, id));
            }
            return students;
        } catch (IOException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private void displayStudentList(List<Student> studentList) {
        studentTable = new TableView<>();
        TableColumn<Student, String> studentColumn = new TableColumn<>("Students");
        studentColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName())); // Assuming the Student class has a getName() method
        studentTable.getColumns().add(studentColumn);
        studentTable.setItems(FXCollections.observableArrayList(studentList));
        VBox.setMargin(studentTable, new Insets(10));
        VBox root = (VBox) scene.getRoot();
        root.getChildren().add(studentTable);
    }

    private void markStudent() {
        String courseName = courseComboBox.getValue();
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        String marksType = marksTypeComboBox.getValue();
        String marksString = marksField.getText();

        if (courseName == null || selectedStudent == null || marksType == null || marksString.isEmpty()) {
            showAlert("Please enter all the required information.");
            return;
        }

        String studentName = selectedStudent.getName();
        String studentId = String.valueOf(selectedStudent.getId());

        int marks;
        try {
            marks = Integer.parseInt(marksString);
        } catch (NumberFormatException e) {
            showAlert("Invalid marks. Please enter a valid integer value.");
            return;
        }
        System.out.println(courseName + "   ");
        String result = GlobalData2.l.UpdateStudentMarks(courseName, studentName, studentId, marksType, marks);
        showAlert(result);

        // Clear the input fields
        marksField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Marks");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}