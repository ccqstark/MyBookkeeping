package bkfunc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class Editor extends Application {

    @Override
    public void start(Stage primaryStage) {
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(450);
        htmlEditor.setPrefWidth(700);
        Scene scene = new Scene(htmlEditor);

        primaryStage.setScene(scene);
        primaryStage.setTitle("文本编辑器");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}