package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LauncherWrapper extends Application {
public static void main(String[] args) {
    launch(args);
}
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.show();
    }
}
