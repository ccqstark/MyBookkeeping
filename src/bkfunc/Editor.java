package bkfunc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class Editor extends Application {

    Stage primaryStage = new Stage();

    public Editor(){
        this.start(this.primaryStage);
    }

    @Override
    public void start(Stage primaryStage) {
        HTMLEditor htmlEditor = new HTMLEditor();
        htmlEditor.setPrefHeight(450);
        htmlEditor.setPrefWidth(700);
        Scene scene = new Scene(htmlEditor);

        primaryStage.setScene(scene);
        primaryStage.setTitle("文本编辑器");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/modify.png"));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}