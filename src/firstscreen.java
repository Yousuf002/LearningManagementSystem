import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
//import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class firstscreen extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the GridPane
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: white;");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add the buttons to the GridPane
        Button teacherButton = new Button("Teacher");
        Button studentButton = new Button("Student");
        Button adminButton = new Button("Admin");
        teacherButton.setPrefWidth(120);
        studentButton.setPrefWidth(120);
        adminButton.setPrefWidth(120);
        teacherButton.setOnAction(e -> {
            teacherlogin t = new teacherlogin();
            Scene TeacherScene = t.scene;
            primaryStage.setScene(TeacherScene);
            primaryStage.setTitle("Teacher login");

            primaryStage.show();
        });
        adminButton.setOnAction(e -> {
            Adminlogin a = new Adminlogin();
            Scene AdminScene = a.scene;
            primaryStage.setScene(AdminScene);
            primaryStage.setTitle("Admin login");
            primaryStage.show();
        });
        studentButton.setOnAction(e -> {
            Studentlogin s = new Studentlogin();
            Scene studentScene = s.scene;
            primaryStage.setScene(studentScene);
            primaryStage.setTitle("Student login");

            primaryStage.show();
        });
        gridPane.add(teacherButton, 0, 1);
        gridPane.add(studentButton, 0, 2);
        gridPane.add(adminButton, 0, 3);
        teacherButton.setStyle("-fx-background-color: white; -fx-text-fill: #005cb9; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #005cb9; -fx-border-width: 1px;");
        studentButton.setStyle("-fx-background-color: white; -fx-text-fill: #005cb9; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #005cb9; -fx-border-width: 1px;");
        adminButton.setStyle("-fx-background-color: white; -fx-text-fill: #005cb9; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #005cb9; -fx-border-width: 1px;");

        // Add hover effect to buttons
        String hoverStyle = "-fx-background-color: #005cb9; -fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;";
        teacherButton.setOnMouseEntered(e -> teacherButton.setStyle(hoverStyle));
        teacherButton.setOnMouseExited(e -> teacherButton.setStyle("-fx-background-color: white; -fx-text-fill: #005cb9; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #005cb9; -fx-border-width: 1px;"));
        studentButton.setOnMouseEntered(e -> studentButton.setStyle(hoverStyle));
        studentButton.setOnMouseExited(e -> studentButton.setStyle("-fx-background-color: white; -fx-text-fill: #005cb9; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #005cb9; -fx-border-width: 1px;"));
        adminButton.setOnMouseEntered(e -> adminButton.setStyle(hoverStyle));
        adminButton.setOnMouseExited(e -> adminButton.setStyle("-fx-background-color: white; -fx-text-fill: #005cb9; -fx-font-size: 12px; -fx-font-weight: bold; -fx-padding: 5px 10px; -fx-border-color: #005cb9; -fx-border-width: 1px;"));

    
       // Label headingLabel = new Label("NUCES LMS");
       Image image = new Image("file:/D:/New%20folder%20(2)/javafx%20project/LMS/bin/fastlogo2.PNG");
      // System.out.println("Image path: " + image.getUrl()); // print the image path
   
       ImageView imageView = new ImageView(image);
         imageView.setFitWidth(398); // set fit width to 200 pixels
       // headingLabel.setPadding(new Insets(20));
       // headingLabel.setFont(Font.font("Ariel",FontWeight.BOLD,24));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(gridPane, imageView);
        StackPane.setAlignment(imageView, Pos.TOP_CENTER);
    // Create the scene
    Scene scene = new Scene(stackPane, 400, 300);

        // Set the stage properties
        primaryStage.setScene(scene);
        primaryStage.setTitle("Learning Management System");
        primaryStage.show();
    }
}   