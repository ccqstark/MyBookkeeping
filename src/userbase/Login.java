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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane loginPane = new GridPane();
        loginPane.setAlignment(Pos.CENTER);
        loginPane.setPadding(new Insets(10));
        loginPane.setHgap(5); //格子的水平间距
        loginPane.setVgap(5); //格子的垂直间距

        loginPane.add(new Label("用户名: "),0,0); //左列右行的格子坐标
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();
        loginPane.add(textField1,1,0);
        loginPane.add(new Label("密码: "),0,1);
        loginPane.add(textField2,1,1);  //密码暗文显示输入框
        Button btLogin = new Button("登录");
        loginPane.add(btLogin,1,3);
        GridPane.setHalignment(btLogin, HPos.RIGHT); //设置在格子中的水平位置
        GridPane.setValignment(btLogin, VPos.BOTTOM); //设置在格子中的垂直位置

        //BorderPane作为背板
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(loginPane);
        //标题
        Text loginTitle = new Text("My Bookkeeping");
        loginTitle.setFont(Font.font("Verdana", FontWeight.BOLD,27));
        borderPane.setTop(loginTitle);
        loginTitle.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(loginTitle,Pos.BOTTOM_CENTER);

        //按钮事件
        btLogin.setOnMouseClicked(event->{
            Controller controller = new Controller(textField1,textField2);
            try {
                controller.LoginButton();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });


        Scene scene = new Scene(borderPane,300,180);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



}
