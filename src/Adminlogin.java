//import java.io.File;
//import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
//import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

class GlobalData3{
    public static lms l;
}
public class Adminlogin   {
     public Scene scene;

    public  Adminlogin() {
        GlobalData3.l = new lms();
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
        loginButton.setStyle("-fx-background-color: white; -fx-text-fill: #336699; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #336699; -fx-border-width: 1px;");
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

                if (GlobalData3.l.adminLogin(email, password)) {
                    //Successful login
                    System.out.println("Login successful");
                    AdminHomescreen t = new AdminHomescreen();
                    Scene admiScene = t.scene;
                    Stage primaryStage = new Stage();
                    primaryStage.setScene(admiScene);
                    primaryStage.setTitle("Admin Portal");
                    primaryStage.show();
                } else {
                    // Failed login
                    errorLabel.setText("Invalid email or password");
                }
        });


    }
    
    

}
class AdminHomescreen {
    Scene scene;

    public AdminHomescreen() {

        Image logoImage = new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(398);
        Text nameLabel = new Text("Hello, " + GlobalData3.l.getAdminName()); // Assuming Admin class has a static method getName() that returns the name
        nameLabel.setFont(Font.font("Times New Roman", FontWeight.LIGHT, 12));
        Text welcomeLabel = new Text("Welcome to Admin Portal");
        welcomeLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));

        Button manageuserButton = new Button("Manage Users");
        manageuserButton.setStyle("-fx-background-color: #336699; -fx-font-size: 14px; -fx-pref-width: 160px;-fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button managecoursesButton = new Button("Manage Courses");
        managecoursesButton.setStyle("-fx-background-color: #336699; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button managefeesButton = new Button("Update Fees");
        managefeesButton.setStyle("-fx-background-color: #336699; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        Button btn = new Button("more features later");
        btn.setStyle("-fx-background-color: #336699; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight:bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        String hoverStyle = "-fx-background-color: #D3D3D3; -fx-text-fill: #336699; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-height: 160px; -fx-pref-width: 160px; -fx-font-family: 'Times New Roman'";
        manageuserButton.setOnMouseEntered(e -> manageuserButton.setStyle(hoverStyle));
        manageuserButton.setOnMouseExited(e -> manageuserButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        managecoursesButton.setOnMouseEntered(e -> managecoursesButton.setStyle(hoverStyle));
        managecoursesButton.setOnMouseExited(e -> managecoursesButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        managefeesButton.setOnMouseEntered(e -> managefeesButton.setStyle(hoverStyle));
        managefeesButton.setOnMouseExited(e -> managefeesButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));
        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-width: 160px; -fx-pref-height: 160px; -fx-font-family: 'Times New Roman'"));


        manageuserButton.setOnAction(e -> {
            ManageUserGUI a = new ManageUserGUI();
            Scene muserScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(muserScene);
            primaryStage.setTitle("Manage User");
            primaryStage.show();
        });
        managecoursesButton.setOnAction(e -> {
            ManageCoursesGUI a = new ManageCoursesGUI();
            Scene coursesScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(coursesScene);
            primaryStage.setTitle("Manage Courses");
            primaryStage.show();
        });
        managefeesButton.setOnAction(e -> {
            UpdateFeeGUI a = new UpdateFeeGUI();
            Scene muserScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(muserScene);
            primaryStage.setTitle("Manage Fee");
            primaryStage.show();
        });
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.add(manageuserButton, 0, 0);
        buttonGrid.add(managecoursesButton, 1, 0);
        buttonGrid.add(managefeesButton, 0, 1);
       buttonGrid.add(btn, 1, 1);
        

  

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(logoImageView,nameLabel, welcomeLabel, buttonGrid);

        scene = new Scene(vBox, 400, 300);
    }
}
class ManageUserGUI {
    Scene scene;

    public ManageUserGUI() {

        Button addTeacherButton = createButton("Add Teacher");
        Button removeTeacherButton = createButton("Remove Teacher");
        Button addStudentButton = createButton("Add Student");
        Button removeStudentButton = createButton("Remove Student");
        Button viewStudentsButton = createButton("View Students");
        Button viewTeacherButton = createButton("View Teachers");
        ImageView imageView = new ImageView(new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG"));
        imageView.setFitWidth(398); // Adjust the width of the image
        imageView.setPreserveRatio(true);
        addTeacherButton.setOnAction(e -> {
            Addteacher a = new Addteacher();
            Scene addTeacherScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(addTeacherScene);
            primaryStage.setTitle("Add teacher");
            primaryStage.show();
        });
        removeTeacherButton.setOnAction(e -> {
            RemoveTeacher a = new RemoveTeacher();
            Scene removeTeacherScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(removeTeacherScene);
            primaryStage.setTitle("Remove teacher");
            primaryStage.show();
        });
        addStudentButton.setOnAction(e -> {
            AddStudent a = new AddStudent();
            Scene addStudentScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(addStudentScene);
            primaryStage.setTitle("Add Student");
            primaryStage.show();
        });
        removeStudentButton.setOnAction(e -> {
            RemoveStudent a = new RemoveStudent();
            Scene removeStudentScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(removeStudentScene);
            primaryStage.setTitle("Remove Student");
            primaryStage.show();
        });
        viewStudentsButton.setOnAction(e -> {
            ViewStudentsGUI a = new ViewStudentsGUI();
            Scene viewStudentScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(viewStudentScene);
            primaryStage.setTitle("Student list");
            primaryStage.show();
        });
        viewTeacherButton.setOnAction(e -> {
            ViewTeachersGUI a = new ViewTeachersGUI();
            Scene viewTeacherScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(viewTeacherScene);
            primaryStage.setTitle("Teachers list");
            primaryStage.show();
        });
      
        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.add(addTeacherButton, 0, 0);
        buttonGrid.add(removeTeacherButton, 1, 0);
        buttonGrid.add(addStudentButton, 0, 1);
        buttonGrid.add(removeStudentButton, 1, 1);
        buttonGrid.add(viewStudentsButton, 0, 2);
        buttonGrid.add(viewTeacherButton, 1, 2);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(imageView,buttonGrid);

        scene = new Scene(vBox, 400, 300);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(160);
        button.setPrefHeight(160);
        button.setStyle("-fx-background-color: #336699; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Times New Roman'");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #D3D3D3; -fx-text-fill: #336699; -fx-font-size: 14px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman'"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #336699; -fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Times New Roman'"));
        return button;
    }

    public Scene getScene() {
        return scene;
    }
}
class ManageCoursesGUI {
    Scene scene;

    public ManageCoursesGUI() {
        Image logoImage = new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG");
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(398);

        

        Button addCourseButton = new Button("Add course");
        Button removeCourseButton = new Button("Remove course");
        Button assignButton = new Button("Assign course to\nTeacher");
        Button viewCourseButton = new Button("View All Courses");
        viewCourseButton.setOnAction(e -> {
            ViewCourseGUI a = new ViewCourseGUI();
            Scene viewCourseScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(viewCourseScene);
            primaryStage.setTitle("Courses list");
            primaryStage.show();
        });
        addCourseButton.setOnAction(e -> {
            Addcourse a = new Addcourse();
            Scene addCourseScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(addCourseScene);
            primaryStage.setTitle("Add course");
            primaryStage.show();
        });
        removeCourseButton.setOnAction(e -> {
            RemoveCourse a = new RemoveCourse();
            Scene removeCourseScene = a.scene;
            Stage primaryStage = new Stage();
            primaryStage.setScene(removeCourseScene);
            primaryStage.setTitle("Remove course");
            primaryStage.show();
        });
        assignButton.setOnAction(e -> {
            AssignCourseGUI assignCourseGUI = new AssignCourseGUI();
            Scene assignCourseScene = assignCourseGUI.scene;
        
            Stage primaryStage = new Stage();
            primaryStage.setScene(assignCourseScene);
            primaryStage.setTitle("Assign Course");
            primaryStage.show();
        });
        // Set button styles    
        String buttonStyle = "-fx-background-color: #336699; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-weight: bold; -fx-pref-height: 160px; -fx-text-fill: white; -fx-font-family: 'Times New Roman'";
        String hoverStyle = "-fx-background-color: #D3D3D3; -fx-text-fill: #336699; -fx-font-size: 14px; -fx-font-weight: bold; -fx-pref-height: 160px; -fx-pref-width: 160px; -fx-font-family: 'Times New Roman'";
        addCourseButton.setStyle(buttonStyle);
        removeCourseButton.setStyle(buttonStyle);
        assignButton.setStyle(buttonStyle);
        viewCourseButton.setStyle(buttonStyle);

        // Set hover styles
        addCourseButton.setOnMouseEntered(e -> addCourseButton.setStyle(hoverStyle));
        addCourseButton.setOnMouseExited(e -> addCourseButton.setStyle(buttonStyle));
        removeCourseButton.setOnMouseEntered(e -> removeCourseButton.setStyle(hoverStyle));
        removeCourseButton.setOnMouseExited(e -> removeCourseButton.setStyle(buttonStyle));
        assignButton.setOnMouseEntered(e -> assignButton.setStyle(hoverStyle));
        assignButton.setOnMouseExited(e -> assignButton.setStyle(buttonStyle));
        viewCourseButton.setOnMouseEntered(e -> viewCourseButton.setStyle(hoverStyle));
        viewCourseButton.setOnMouseExited(e -> viewCourseButton.setStyle(buttonStyle));

        GridPane buttonGrid = new GridPane();
        buttonGrid.setAlignment(Pos.CENTER);
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);
        buttonGrid.setPadding(new Insets(10));
        buttonGrid.add(addCourseButton, 0, 0);
        buttonGrid.add(removeCourseButton, 1, 0);
        buttonGrid.add(assignButton, 0, 1);
        buttonGrid.add(viewCourseButton, 1, 1);

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(logoImageView, buttonGrid);

        scene = new Scene(vBox, 400, 300);
    }
}


 class Addteacher {
    public Scene scene;

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(100);
        button.setPrefHeight(30);
        button.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        return button;
    }

    public Addteacher() {
      //  lms l = new lms();
        
        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label errorLabel = createErrorLabel();

        Button addButton = createButton("Add Teacher");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String idText = idField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
        
            // Check for empty fields
            if (name.isEmpty() || idText.isEmpty() || email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("All fields must be filled");
                return;
            }
        
            // Validate email format
            if (!email.endsWith("@nu.edu.pk")) {
                errorLabel.setText("Email must end with '@nu.edu.pk'");
                return;
            }
        
            int id = Integer.parseInt(idText);
            GlobalData3.l = new lms();
            Teacher teacher = new Teacher(name, id, email, password);
            try {
                boolean addedSuccessfully = GlobalData3.l.AddTeacher(teacher);
                if (addedSuccessfully) {
                    errorLabel.setText("Added successfully");
                    errorLabel.setStyle("-fx-text-fill: green");
                } else {
                    errorLabel.setText("Teacher with the same ID already exists");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
            // Clear the fields
            nameField.clear();
            idField.clear();
            emailField.clear();
            passwordField.clear();
        
            // Clear error label
            //errorLabel.setText("Added Successfully");
        });

        // Add components to the grid pane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(idField, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(passwordField, 1, 3);
        gridPane.add(addButton, 0, 4, 2, 1);
        gridPane.add(errorLabel, 0, 5, 2, 1);

        // Create the scene and set it on the stage
        scene = new Scene(gridPane, 300, 250);
    }

    public Scene getScene() {
        return scene;
    }
}
class RemoveTeacher{

    private TableView<Teacher> teacherTable;
   // private lms l;
    public Scene scene;

    private void initializeTable() {
        teacherTable = new TableView<>();
        TableColumn<Teacher, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Teacher, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Teacher, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        teacherTable.getColumns().addAll(nameColumn, idColumn, emailColumn);
        teacherTable.setItems(getTeachers());
    }

    private ObservableList<Teacher> getTeachers() {
        // Read teachers from file and return as ObservableList
        try {
            return FXCollections.observableArrayList(GlobalData3.l.readTeachersFromFile());
        } catch (IOException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(120);
        button.setPrefHeight(30);
        button.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        return button;
    }

    public RemoveTeacher() {
        GlobalData3.l = new lms();

        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label errorLabel = createErrorLabel();

        Button removeButton = createButton("Remove Teacher");
        removeButton.setPrefWidth(120);
        removeButton.setOnAction(e -> {
            Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
            if (selectedTeacher == null) {
                errorLabel.setText("Please select a teacher.");
                return;
            }

            try {
                boolean removed = GlobalData3.l.removeTeacher(selectedTeacher.getName(), selectedTeacher.getId());
                if (removed) {
                    // Clear error label
                    errorLabel.setText("");
                    errorLabel.setText("Removed successfully");
                    errorLabel.setStyle("-fx-text-fill: green");

                    // Refresh the table
                    teacherTable.setItems(getTeachers());
                } else {
                    errorLabel.setText("Teacher not found.");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        initializeTable();

        TextField searchField = new TextField();
        searchField.setPromptText("Search by name");

        Button searchButton = createButton("Search");
        searchButton.setOnAction(e -> {
            String searchText = searchField.getText();
            searchTeachers(searchText);
        });

        // Add components to the grid pane
        gridPane.add(removeButton, 0, 3);
        gridPane.add(errorLabel, 1, 3);
        gridPane.add(teacherTable, 0, 2);
        gridPane.add(searchField, 0, 0);
        gridPane.add(searchButton, 1, 0);

        scene = new Scene(gridPane, 500, 400);
    }

    private void searchTeachers(String searchText) {
        ObservableList<Teacher> filteredTeachers = getTeachers().filtered(teacher ->
                teacher.getName().toLowerCase().contains(searchText.toLowerCase())
        );
        teacherTable.setItems(filteredTeachers);
    }


}
class AddStudent {
    public Scene scene;

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(100);
        button.setPrefHeight(30);
        button.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        return button;
    }

    public AddStudent() {
       // lms l = new lms();
        GlobalData3.l = new lms();
        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label errorLabel = createErrorLabel();

        Button addButton = createButton("Add Student");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String idText = idField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
        
            // Check for empty fields
            if (name.isEmpty() || idText.isEmpty() || email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("All fields must be filled");
                return;
            }
            
            // Validate email format
            if (!email.endsWith("@nu.edu.pk")) {
                errorLabel.setText("Email must end with '@nu.edu.pk'");
                return;
            }
        
            int id = Integer.parseInt(idText);
        
            Student student = new Student(name, id, email, password);
            try {
                boolean added = GlobalData3.l.addStudent(student);
                if (added) {
                    errorLabel.setText("Added successfully");
                    errorLabel.setStyle("-fx-text-fill: green");
                } else {
                    errorLabel.setText("Student ID already exists");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        
            // Clear the fields
            nameField.clear();
            idField.clear();
            emailField.clear();
            passwordField.clear();
        
            // Clear error label
          //  errorLabel.setText("Added successfully");
            errorLabel.setStyle("-fx-text-fill: green");
        });

        // Add components to the grid pane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(idField, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(passwordLabel, 0, 3);
        gridPane.add(passwordField, 1, 3);
        gridPane.add(addButton, 0, 4, 2, 1);
        gridPane.add(errorLabel, 0, 5, 2, 1);

        // Create the scene and set it on the stage
        scene = new Scene(gridPane, 300, 250);
    }

    public Scene getScene() {
        return scene;
    }
}
class RemoveStudent{

    private TableView<Student> studentTable;
    //private lms l;
    public Scene scene;

    private void initializeTable() {
        studentTable = new TableView<>();

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        studentTable.getColumns().addAll(nameColumn, idColumn, emailColumn);
        studentTable.setItems(getStudents());
    }

    private ObservableList<Student> getStudents() {
        // Read teachers from file and return as ObservableList
        try {
            return FXCollections.observableArrayList(GlobalData3.l.readStudentsFromFile());
        } catch (IOException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(120);
        button.setPrefHeight(30);
        button.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        return button;
    }

    public RemoveStudent() {
        //l = new lms();
        GlobalData3.l = new lms();
        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label errorLabel = createErrorLabel();

        Button removeButton = createButton("Remove Student");
        removeButton.setPrefWidth(120);
        removeButton.setOnAction(e -> {
            Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
            if (selectedStudent == null) {
                errorLabel.setText("Please select a Student.");
                return;
            }

            try {
                boolean removed = GlobalData3.l.removeStudent(selectedStudent.getName(), selectedStudent.getId());
                if (removed) {
                    // Clear error label
                    errorLabel.setText("");
                    errorLabel.setText("Removed successfully");
                    errorLabel.setStyle("-fx-text-fill: green");

                    // Refresh the table
                    studentTable.setItems(getStudents());
                } else {
                    errorLabel.setText("Teacher not found.");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        initializeTable();

        TextField searchField = new TextField();
        searchField.setPromptText("Search by name");

        Button searchButton = createButton("Search");
        searchButton.setOnAction(e -> {
            String searchText = searchField.getText();
            searchStudents(searchText);
        });

        // Add components to the grid pane
        gridPane.add(removeButton, 0, 3);
        gridPane.add(errorLabel, 1, 3);
        gridPane.add(studentTable, 0, 2);
        gridPane.add(searchField, 0, 0);
        gridPane.add(searchButton, 1, 0);

        scene = new Scene(gridPane, 500, 400);
    }

    private void searchStudents(String searchText) {
        ObservableList<Student> filteredStudents = getStudents().filtered(student ->
                student.getName().toLowerCase().contains(searchText.toLowerCase())
        );
        studentTable.setItems(filteredStudents);
    }


}
class ViewStudentsGUI {
    public Scene scene;

    public ViewStudentsGUI() {
      //  Admin admin = new Admin();
        GlobalData3.l = new lms();
        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label studentsLabel = createLabel();
        studentsLabel.setText("Students:");

        TableView<Student> tableView = new TableView<>();
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Student, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Student, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableView.getColumns().addAll(nameColumn, idColumn, emailColumn, passwordColumn);
        GridPane.setConstraints(tableView, 0, 1, 1, 1); // Set the TableView to span one column and one row

        try {
            ObservableList<Student> students = FXCollections.observableArrayList(GlobalData3.l.viewStudents());
            tableView.setItems(students);
        } catch (IOException e) {
            studentsLabel.setText("Error reading students data from file.");
        }

        gridPane.add(studentsLabel, 0, 0);
        gridPane.getChildren().add(tableView); // Add the TableView to the GridPane's children list

        // Create the scene and set it on the stage
        scene = new Scene(gridPane);
    }

    private Label createLabel() {
        Label label = new Label();
        label.setTextFill(Color.BLACK);
        return label;
    }

    public Scene getScene() {
        return scene;
    }
}
class ViewTeachersGUI {
    public Scene scene;

    public ViewTeachersGUI() {
        lms l = new lms();

        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label teachersLabel = createLabel();
        teachersLabel.setText("Teachers:");

        TableView<Teacher> tableView = new TableView<>();
        TableColumn<Teacher, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Teacher, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Teacher, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Teacher, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        tableView.getColumns().addAll(nameColumn, idColumn, emailColumn, passwordColumn);

        try {
            ObservableList<Teacher> teachers = FXCollections.observableArrayList(l.viewTeachers());
            tableView.setItems(teachers);
        } catch (IOException e) {
            teachersLabel.setText("Error reading teachers data from file.");
        }

        gridPane.add(teachersLabel, 0, 0);
        gridPane.add(tableView, 0, 1);

        // Create the scene and set it on the stage
        scene = new Scene(gridPane);
    }

    private Label createLabel() {
        Label label = new Label();
        label.setTextFill(Color.BLACK);
        return label;
    }

    public Scene getScene() {
        return scene;
    }
}
class Addcourse {
    public Scene scene;

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(100);
        button.setPrefHeight(30);
        button.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        return button;
    }

    public Addcourse() {
        lms l = new lms();

        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label cdthrsLabel = new Label("credit hrs:");
        TextField cdthrsField = new TextField();

        Label errorLabel = createErrorLabel();

        Button addButton = createButton("Add Course");
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String idText = idField.getText();
          //  String cdthrs = cdthrsField.getText();

            String inputText = cdthrsField.getText();
            // Check for empty fields
            if (name.isEmpty() || idText.isEmpty() || inputText.isEmpty()) {
                errorLabel.setText("All fields must be filled");
                return;
            }

            int cdthr = Integer.parseInt(inputText);

            Course course = new Course(name, idText, cdthr);
            try {
                if (!l.addCourse(course)) {
                    errorLabel.setText("Course with the same ID or name already exists.");
                    return;
                }
               
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            // Clear the fields
            nameField.clear();
            idField.clear();
            cdthrsField.clear();

            // Clear error label
            errorLabel.setText("Added successfully");
            errorLabel.setStyle("-fx-text-fill: green");

        });

        // Add components to the grid pane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(idField, 1, 1);
        gridPane.add(cdthrsLabel, 0, 2);
        gridPane.add(cdthrsField, 1, 2);
        gridPane.add(addButton, 0, 4, 2, 1);
        gridPane.add(errorLabel, 0, 5, 2, 1);

        // Create the scene and set it on the stage
        scene = new Scene(gridPane, 300, 250);
    }

    public Scene getScene() {
        return scene;
    }
}
class RemoveCourse {
    public Scene scene;

    private Label createErrorLabel() {
        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        return errorLabel;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(100);
        button.setPrefHeight(30);
        button.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        return button;
    }

    public RemoveCourse() {
        lms l = new lms();
        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label idLabel = new Label("ID:");
        TextField idField = new TextField();

        Label errorLabel = createErrorLabel();
        Label successLabel = new Label();
         successLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
        Button removeButton = createButton("Remove Course");
        removeButton.setOnAction(e -> {
            String name = nameField.getText();
            String idText = idField.getText();

            // Check for empty fields
            if (name.isEmpty() || idText.isEmpty()) {
                errorLabel.setText("Name and ID fields must be filled");
                return;
            }

          //  int id = Integer.parseInt(idText);

            try {
                boolean removed = l.removeCourse(name, idText);
                if (removed) {
                    // Clear the fields
                    nameField.clear();
                    idField.clear();

                    // Clear error label
                    errorLabel.setText("");
                    errorLabel.setText("Removed successfully");
                    errorLabel.setStyle(" -fx-text-fill: green");
                } else {
                    errorLabel.setText("Course not found.");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // Add components to the grid pane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(idField, 1, 1);
        gridPane.add(removeButton, 0, 2, 2, 1);
        gridPane.add(errorLabel, 0, 3, 2, 1);
    
        // Create the scene and set it on the stage
        scene = new Scene(gridPane, 300, 150);
    }
    
    public Scene getScene() {
        return scene;
    }
}
class ViewCourseGUI {
    public Scene scene;

    public ViewCourseGUI() {
        lms l = new lms();

        // Create UI components
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label courseLabel = createLabel();
        courseLabel.setText("Courses:");

        TableView<Course> courseTable = new TableView<>();
        TableColumn<Course, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Course, String> idColumn = new TableColumn<>("ID");
        TableColumn<Course, String> creditHrsColumn = new TableColumn<>("Credit Hours");

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUniqueId()));
        creditHrsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getCrdthrs())));

        courseTable.getColumns().addAll(nameColumn, idColumn, creditHrsColumn);

        try {
            List<Course> courses = l.viewCourses();
            ObservableList<Course> courseData = FXCollections.observableArrayList(courses);
            courseTable.setItems(courseData);
        } catch (IOException e) {
            courseTable.setPlaceholder(createLabel("Error reading courses data from file."));
        }

        gridPane.add(courseLabel, 0, 0);
        gridPane.add(courseTable, 0, 1);

        scene = new Scene(gridPane, 400, 300);
    }

    private Label createLabel() {
        Label label = new Label();
        label.setTextFill(Color.BLACK);
        return label;
    }

    private Label createLabel(String text) {
        Label label = createLabel();
        label.setText(text);
        return label;
    }

    public Scene getScene() {
        return scene;
    }
}
 class AssignCourseGUI {

    private TableView<Teacher> teacherTable;
    private TableView<Course> courseTable;
    private Label messageLabel;
    private lms l;
    Scene scene;
    private Stage parentStage;

    public AssignCourseGUI() {
        l = new lms();
        // Create UI components

        courseTable = new TableView<>(); // Initialize the courseTable object before calling initializeCourseTable

        Label teacherLabel = new Label("Select Teacher:");
        teacherTable = new TableView<>();
        initializeTeacherTable();

        Label courseLabel = new Label("Select Course:");
        initializeCourseTable();

        Button assignButton = new Button("Assign Course");
        assignButton.setPrefWidth(120);
        assignButton.setPrefHeight(30);
        assignButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        assignButton.setOnAction(e -> assignCourseButtonClicked());

        messageLabel = new Label();
        messageLabel.setWrapText(true);

        // Create layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(teacherLabel, 0, 0);
        gridPane.add(teacherTable, 1, 0);
     //   gridPane.add(courseLabel, 0, 1);
       // gridPane.add(courseTable, 1, 1);
  //      gridPane.add(assignButton, 0, 2, 2, 1);
        gridPane.add(messageLabel, 0, 3, 2, 1);
         scene = new Scene(gridPane, 400, 300);
        Stage stage = new Stage();

        teacherTable.getSelectionModel().selectedItemProperty().addListener((obs, oldTeacher, newTeacher) -> {
            if (newTeacher != null) {
                showAssignCourseWindow(newTeacher, stage);
            }
        });
    }

    private void initializeTeacherTable() {
        TableColumn<Teacher, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Teacher, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        teacherTable.getColumns().addAll(nameColumn, idColumn);
        teacherTable.setItems(getTeachers());
    }

    private ObservableList<Teacher> getTeachers() {
        ObservableList<Teacher> teachers = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("teachers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int id = Integer.parseInt(parts[1].trim());
                teachers.add(new Teacher(name, id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    private void initializeCourseTable() {
        TableColumn<Course, String> nameColumn = new TableColumn<>("Course Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    
        TableColumn<Course, String> idColumn = new TableColumn<>("Course ID");
        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Course, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Course, String> param) {
                return new SimpleStringProperty(param.getValue().getUniqueId());
            }
        });
    
        courseTable.getColumns().addAll(nameColumn, idColumn);
        courseTable.setItems(getCourses());
    }
    private ObservableList<Course> getCourses() {
        ObservableList<Course> courses = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("courses.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String id = parts[1].trim();
                courses.add(new Course(name, id));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private void assignCourseButtonClicked() {
        
        Teacher selectedTeacher = teacherTable.getSelectionModel().getSelectedItem();
        Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();

        if (selectedTeacher == null || selectedCourse == null) {
            messageLabel.setText("Please select a teacher and a course.");
            messageLabel.setTextFill(Color.RED);
            return;
        }

        String result = l.assignCourseToTeacher(selectedTeacher, selectedCourse);
        messageLabel.setText(result);
        if (result.contains("successfully")) {
            messageLabel.setTextFill(Color.GREEN);
        } else {
            messageLabel.setTextFill(Color.RED);
        }
    }

    private void showAssignCourseWindow(Teacher teacher, Stage parentStage) {
        Stage stage = new Stage();
        stage.initOwner(parentStage);
    
        Label courseLabel = new Label("Select\nCourse:");
        // Remove the redeclaration of courseTable here
      //  initializeCourseTable();
        Button assignButton = new Button("Assign Course");
        assignButton.setPrefWidth(120);
        assignButton.setPrefHeight(30);
        assignButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;");
        assignButton.setOnAction(e -> {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                String result = l.assignCourseToTeacher(teacher, selectedCourse);
                messageLabel.setText(result);
                if (result.contains("successfully")) {
                    messageLabel.setTextFill(Color.GREEN);
                } else {
                    messageLabel.setTextFill(Color.RED);
                }
                stage.close();
    
            }
        });
    
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
    
        gridPane.add(courseLabel, 0, 0);
        gridPane.add(courseTable, 1, 0);
        gridPane.add(assignButton, 0, 1, 2, 1);
    
        Scene scene1 = new Scene(gridPane, 400, 300);
        stage.setScene(scene1);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
}
class UpdateFeeGUI {
    private TextField studentNameField;
    private TextField feeAmountField;
    private Label messageLabel;
    private TextField studentIdField;
    private Button updateButton;


    public Scene scene;

    public UpdateFeeGUI() {
      
       // primaryStage.setTitle("Update Student Fee");

       Label nameLabel = new Label("Student Name:");
       studentNameField = new TextField();

       Label idLabel = new Label("Student ID:");
       studentIdField = new TextField();

       Label feeLabel = new Label("Fee Amount:");
       feeAmountField = new TextField();

       updateButton = new Button("Update Fee");
       updateButton.setPrefWidth(120);
       updateButton.setPrefHeight(30);
       updateButton.setStyle("-fx-background-color: silver; -fx-text-fill: black;-fx-font-weight:bold; -fx-font-size: 12px;");

       updateButton.setOnMouseEntered(e -> {
           if (!updateButton.isDisabled()) {
               updateButton.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-font-size: 12px;-fx-font-weight:bold");
           }
       });

       updateButton.setOnMouseExited(e -> {
           if (!updateButton.isDisabled()) {
               updateButton.setStyle("-fx-background-color: silver; -fx-text-fill: black; -fx-font-size: 12px;-fx-font-weight:bold");
           }
       });

       updateButton.setOnAction(e -> updateFeeButtonClicked());

        messageLabel = new Label();
        messageLabel.setWrapText(true);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(studentNameField, 1, 0);
        gridPane.add(idLabel, 0, 1);
        gridPane.add(studentIdField, 1, 1);
        gridPane.add(feeLabel, 0, 2);
        gridPane.add(feeAmountField, 1, 2);
        gridPane.add(updateButton, 0, 3, 2, 1);
        gridPane.add(messageLabel, 0, 4, 2, 1);

       scene = new Scene(gridPane, 400, 200);
       // primaryStage.setScene(scene);
       // primaryStage.show();
    }

    private void updateFeeButtonClicked() {
        String studentName = studentNameField.getText();
        int studentId = Integer.parseInt(studentIdField.getText());
        double feeAmount;
    
        try {
            feeAmount = Double.parseDouble(feeAmountField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Invalid fee amount. Please enter a numeric value.");
            messageLabel.setTextFill(Color.RED);
            return;
        }
    
        boolean success = Admin.updateStudentFee(studentName, studentId, feeAmount);
    
        if (success) {
            messageLabel.setText("Student fee updated successfully.");
            messageLabel.setTextFill(Color.GREEN);
        } else {
            messageLabel.setText("Student not found.");
            messageLabel.setTextFill(Color.RED);
        }
    
        // Clear the fields
        studentNameField.clear();
        studentIdField.clear();
        feeAmountField.clear();
    }
}

    