package com.example.interfazdinamicafxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Locale spanish = new Locale("es", "ES");
        Locale english = new Locale("en", "UK");

        ResourceBundle bundle = ResourceBundle.getBundle("i18n/strings", spanish);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"),bundle);

        Scene scene = new Scene(fxmlLoader.load(), 300, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}