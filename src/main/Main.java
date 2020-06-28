package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane mainPane = new Pane();
        //按钮文字
        Button takeNoteButton = new Button("立即\n记账");
        Button manageBookButton = new Button("我的\n账本");
        Button userButton = new Button("账户\n信息");
        Button graphButton = new Button("统计\n图表");
        //添加到板上
        mainPane.getChildren().add(takeNoteButton);
        mainPane.getChildren().add(manageBookButton);
        mainPane.getChildren().add(userButton);
        mainPane.getChildren().add(graphButton);
        //按钮字体大小（按钮大小）
        takeNoteButton.setFont(new Font(40));
        manageBookButton.setFont(new Font(40));
        userButton.setFont(new Font(40));
        graphButton.setFont(new Font(40));
        //按钮位置
        takeNoteButton.setLayoutX(50);
        takeNoteButton.setLayoutY(40);
        manageBookButton.setLayoutX(220);
        manageBookButton.setLayoutY(40);
        userButton.setLayoutX(50);
        userButton.setLayoutY(200);
        graphButton.setLayoutX(220);
        graphButton.setLayoutY(200);

        Scene scene = new Scene(mainPane,405,365);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Bookkeeping");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
