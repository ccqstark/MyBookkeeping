package userbase;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class Register extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        GridPane inputPane = new GridPane();
        borderPane.setCenter(inputPane);
        inputPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(inputPane,Pos.CENTER);
        inputPane.setVgap(6);
        inputPane.setHgap(3);


        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();
        PasswordField textField3 = new PasswordField();

        inputPane.add(new Text("用户名: "),0,0);
        inputPane.add(new Text("密码: "),0,1);
        inputPane.add(new Text("再次输入: "),0,2);
        inputPane.add(textField1,1,0);
        inputPane.add(textField2,1,1);
        inputPane.add(textField3,1,2);


        Text registerTitle= new Text("新用户");
        registerTitle.setFont(Font.font("微软雅黑", FontWeight.BOLD,27));
        borderPane.setTop(registerTitle);
        registerTitle.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(registerTitle,Pos.BOTTOM_CENTER);

        Button btRegister = new Button("立即注册");
        btRegister.setFont(new Font(12));
        borderPane.setBottom(btRegister);
        BorderPane.setAlignment(btRegister,Pos.CENTER);
        borderPane.setPadding(new Insets(15));


        Scene scene = new Scene(borderPane,300,190);
        primaryStage.setScene(scene);
        primaryStage.setTitle("注册");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.show();
    }

}
