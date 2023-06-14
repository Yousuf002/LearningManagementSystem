import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
class GlobalData {
    public static lms l;
}
public class Studentlogin   {
    public Scene scene;

   public  Studentlogin() {
   
       TextField emailTextField = new TextField();
       PasswordField passwordTextField = new PasswordField();
   
       Label passwordLabel = new Label("Password ");
       Label emailLabel = new Label("Email ");
       emailLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
       passwordLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
   
   
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
                GlobalData.l = new lms();
               if (GlobalData.l.studentLogin(email, password)) {
                   //Successful login
                   System.out.println("Login successful");
                   StudentHomescreen t = new StudentHomescreen();
                   Scene studentScene = t.scene;
                   Stage primaryStage = new Stage();
                   primaryStage.setScene(studentScene);
                   primaryStage.setTitle("Student Portal");
                   primaryStage.show();
               } else {
                   // Failed login
                   errorLabel.setText("Invalid email or password");
               }
       });


   }
   
//    private boolean isValidEmail(String email) {
//        // Email validation regex pattern
//        String emailPattern = "^(\\\\w+)([\\\\.-]?\\\\w+)(@\\\\w+\\\\.\\\\w{2,3}(\\\\.\\\\w{2,3})?)$";
//        return email.matches(emailPattern);
//    }
   
//    private boolean isValidPassword(String password) {
//        // Password validation regex pattern
//        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
//        return password.matches(passwordPattern);
//    }

}
class StudentHomescreen {
    Scene scene;

    public StudentHomescreen() {
        Image logoImage = new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(398);
        Text nameLabel = new Text("Hello, " + GlobalData.l.getStudentName()); // Assuming Admin class has a static method getName() that returns the name

        Text welcomeLabel = new Text("Welcome to Student Portal");
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
        nameLabel.setFont(Font.font("Times New Roman", FontWeight.LIGHT, 12));

        Button registerCourseButton = new Button("Register course");
        registerCourseButton.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px;-fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button attendanceButton = new Button("Attendance");
        attendanceButton.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button viewMarksButton = new Button("View Marks");
        viewMarksButton.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button announcementbtn = new Button("View Announcement");
        announcementbtn.setStyle("-fx-background-color: #005cb9; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        String hoverStyle = "-fx-background-color: #D3D3D3; -fx-text-fill: #005cb9; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-height: 160px; -fx-pref-width: 160px; -fx-font-family: 'Times New Roman'";
        registerCourseButton.setOnMouseEntered(e -> registerCourseButton.setStyle(hoverStyle));
        registerCourseButton.setOnMouseExited(e -> registerCourseButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        attendanceButton.setOnMouseEntered(e -> attendanceButton.setStyle(hoverStyle));
        attendanceButton.setOnMouseExited(e -> attendanceButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        viewMarksButton.setOnMouseEntered(e -> viewMarksButton.setStyle(hoverStyle));
        viewMarksButton.setOnMouseExited(e -> viewMarksButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        announcementbtn.setOnMouseEntered(e -> announcementbtn.setStyle(hoverStyle));
        announcementbtn.setOnMouseExited(e -> announcementbtn.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));


        registerCourseButton.setOnAction(e -> {
            RegisterCourseGUI a = new RegisterCourseGUI();
            Scene muserScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(muserScene);
            primaryStage.setTitle("Course registration");
            primaryStage.show();
        });
        attendanceButton.setOnAction(e -> {
            ViewAttendanceGUI a = new ViewAttendanceGUI();
            Scene coursesScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(coursesScene);
            primaryStage.setTitle("Attendance");
            primaryStage.show();
        });
        viewMarksButton.setOnAction(e -> {
            ViewMarksGUI a = new ViewMarksGUI();
            Scene viewMarksScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(viewMarksScene);
            primaryStage.setTitle("View Marks");
            primaryStage.show();
        });
        announcementbtn.setOnAction(e -> {
            ViewAnnouncementGUI a = new ViewAnnouncementGUI();
            Scene muserScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(muserScene);
            primaryStage.setTitle("View Announcement");
            primaryStage.show();
        });
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.add(registerCourseButton, 0, 0);
        buttonGrid.add(attendanceButton, 1, 0);
        buttonGrid.add(viewMarksButton, 0, 1);
       buttonGrid.add(announcementbtn, 1, 1);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(logoImageView,nameLabel, welcomeLabel, buttonGrid);

        scene = new Scene(vBox, 400, 300);
    }
}
 class RegisterCourseGUI {
    private static final String COURSES_FILE = "courses.txt";

    private ListView<String> courseListView;
    private Student student;
    public Scene scene;
    private Label messageLabel;
    lms l;
    public RegisterCourseGUI() {
        // Create the Student object (replace with your own way of getting the student information)
        //l = new lms();
        student = GlobalData.l.getStudent(); 
      student = new Student();
        if (student.getRegisteredCourses() == null) {
            student.setRegisteredCourses(new ArrayList<>());
        }
        ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
        imageView.setFitWidth(398); // Adjust the width of the image
        imageView.setPreserveRatio(true);
        // Read the available courses from courses.txt
        List<String> availableCourses = readAvailableCourses();

        // Create the GUI elements
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.getChildren().add(imageView);

        courseListView = new ListView<>();
        ObservableList<String> courseItems = FXCollections.observableArrayList(availableCourses);
        courseListView.setItems(courseItems);
        root.getChildren().add(courseListView);
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        Button registerButton = new Button("Register");
        registerButton.setPrefWidth(120);
        registerButton.setPrefHeight(30);
        registerButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px;");
        registerButton.setOnAction(e -> registerCourse());
        root.getChildren().add(registerButton);

        // Create the message label
        messageLabel = new Label();
        root.getChildren().add(messageLabel);

        // Create the scene and set it to the primary stage
        scene = new Scene(root, 400, 300);
        
        // Initialize the CourseRegistration object
    }

    private List<String> readAvailableCourses() {
        List<String> courses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(COURSES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courses.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return courses;
    }

    private void registerCourse() {
        String selectedCourse = courseListView.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            String result = GlobalData.l.registerCourse(selectedCourse);
            messageLabel.setText(result);
        }
    }
}


 class ViewAttendanceGUI  {
    private TextField courseNameField;
  //  private TextField studentIdField;
    private TextArea attendanceTextArea;
    public Scene scene;


   
    public ViewAttendanceGUI() {
        //primaryStage.setTitle("View Attendance");

        Label courseNameLabel = new Label("Course Name:");
        courseNameField = new TextField();
        ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
        imageView.setFitWidth(398); // Adjust the width of the image
        imageView.setPreserveRatio(true);
        Label studentIdLabel = new Label("Student ID:");
       // studentIdField = new TextField();
     
        Button viewAttendanceButton = new Button("View Attendance");
        viewAttendanceButton.setPrefWidth(120);
        viewAttendanceButton.setPrefHeight(30);
        viewAttendanceButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px;");
        viewAttendanceButton.setOnAction(e -> viewAttendance());

        attendanceTextArea = new TextArea();
        attendanceTextArea.setEditable(false);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(imageView,courseNameLabel, courseNameField, studentIdLabel, viewAttendanceButton, attendanceTextArea);

        scene = new Scene(root, 400, 300);
        //primaryStage.setScene(scene);
       // primaryStage.show();
    }

    private void viewAttendance() {
        String courseName = courseNameField.getText();
    //    String studentIdString = studentIdField.getText();

        if (courseName.isEmpty()) {
            showAlert("Please enter the course name.");
            return;
        }
      
        String attendance = GlobalData.l.viewAttendance(courseName);
        attendanceTextArea.setText(attendance);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Attendance");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
class ViewMarksGUI  {
    private TextField courseNameField;
  //  private TextField studentIdField;
    private TextArea attendanceTextArea;
    public Scene scene;


   
    public ViewMarksGUI() {
        //primaryStage.setTitle("View Attendance");

        Label courseNameLabel = new Label("Course Name:");
        courseNameField = new TextField();

        Label studentIdLabel = new Label("Student ID:");
       // studentIdField = new TextField();
       ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
       imageView.setFitWidth(398); // Adjust the width of the image
       imageView.setPreserveRatio(true);
        Button viewAttendanceButton = new Button("View Marks");
        viewAttendanceButton.setPrefWidth(120);
        viewAttendanceButton.setPrefHeight(30);
        viewAttendanceButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px;");
        viewAttendanceButton.setOnAction(e -> viewMarks());

        attendanceTextArea = new TextArea();
        attendanceTextArea.setEditable(false);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(imageView,courseNameLabel, courseNameField, studentIdLabel, viewAttendanceButton, attendanceTextArea);

        scene = new Scene(root, 400, 300);
        //primaryStage.setScene(scene);
       // primaryStage.show();
    }

    private void viewMarks() {
        String courseName = courseNameField.getText();
    //    String studentIdString = studentIdField.getText();

        if (courseName.isEmpty()) {
            showAlert("Please enter the course name.");
            return;
        }

        String attendance = GlobalData.l.viewMarks(courseName);
        attendanceTextArea.setText(attendance);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Marks");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
class ViewAnnouncementGUI  {
    private TextField courseNameField;
  //  private TextField studentIdField;
    private TextArea attendanceTextArea;
    public Scene scene;


   
    public ViewAnnouncementGUI() {
        //primaryStage.setTitle("View Attendance");

        Label courseNameLabel = new Label("Course Name:");
        courseNameField = new TextField();


        Button viewAttendanceButton = new Button("View Announcement");
        viewAttendanceButton.setPrefWidth(150);
        viewAttendanceButton.setPrefHeight(30);
        viewAttendanceButton.setStyle("-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px;");
        viewAttendanceButton.setOnAction(e -> viewAnnouncement());
        ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
        imageView.setFitWidth(398); // Adjust the width of the image
        imageView.setPreserveRatio(true);
        attendanceTextArea = new TextArea();
        attendanceTextArea.setEditable(false);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(imageView,courseNameLabel, courseNameField,viewAttendanceButton, attendanceTextArea);

        scene = new Scene(root, 400, 300);
        //primaryStage.setScene(scene);
       // primaryStage.show();
    }

    private void viewAnnouncement() {
        String courseName = courseNameField.getText();
    //    String studentIdString = studentIdField.getText();

        if (courseName.isEmpty()) {
            showAlert("Please enter the course name.");
            return;
        }

        String attendance = GlobalData.l.viewAnnouncement(courseName);
        attendanceTextArea.setText(attendance);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Marks");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
