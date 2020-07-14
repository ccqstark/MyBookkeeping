package userbase;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.SQLException;


public class Register extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        GridPane inputPane = new GridPane();
        borderPane.setCenter(inputPane);
        inputPane.setAlignment(Pos.CENTER);
        BorderPane.setAlignment(inputPane,Pos.CENTER);
        GridPane buttonPane = new GridPane();
        inputPane.setVgap(6);
        inputPane.setHgap(3);

        //输入框
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();
        PasswordField textField3 = new PasswordField();

        inputPane.add(new Text("用户名: "),0,0);
        inputPane.add(new Text("密码: "),0,1);
        inputPane.add(new Text("再次输入: "),0,2);
        inputPane.add(textField1,1,0);
        inputPane.add(textField2,1,1);
        inputPane.add(textField3,1,2);

        //标题
        Text registerTitle= new Text("新用户");
        registerTitle.setFont(Font.font("微软雅黑", FontWeight.BOLD,27));
        borderPane.setTop(registerTitle);
        registerTitle.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(registerTitle,Pos.BOTTOM_CENTER);

        borderPane.setBottom(buttonPane);
        borderPane.setPadding(new Insets(10));

        //为了居中的占位
        Text playHolder = new Text("        ");
        playHolder.setFont(new Font(26));
        buttonPane.add(playHolder,0,0);
        GridPane.setHalignment(playHolder,HPos.CENTER);
        GridPane.setValignment(playHolder,VPos.CENTER);
        buttonPane.setHgap(45);

        //注册按钮
        Button btRegister = new Button("立即注册");
        btRegister.setFont(new Font(12));
        buttonPane.add(btRegister,1,0);
        GridPane.setHalignment(btRegister,HPos.CENTER);
        GridPane.setValignment(btRegister,VPos.CENTER);
        //登录按钮
        Button btGoToLogin = new Button("前往登录");
        btGoToLogin.setFont(new Font(12));
        buttonPane.add(btGoToLogin,2,0);
        GridPane.setHalignment(btGoToLogin,HPos.CENTER);
        GridPane.setValignment(btGoToLogin,VPos.CENTER);

        //转到登录
        Login login = new Login();
        btGoToLogin.setOnMouseClicked(e->{
            primaryStage.hide();
            login.show();
        });

        //点击注册(匿名内部类)
        btRegister.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String psw1 = textField2.getText();
                String psw2 = textField3.getText();
                if (psw1.equals(psw2)){
                    Controller controller = new Controller(textField1,textField2);
                    try { //插入数据库
                        controller.register();
                    } catch (SQLException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    //注册成功跳到登录界面
                    primaryStage.hide();
                    login.show();
                }else { //两次密码输入不一致
                    playHolder.setFont(new Font(11));
                    playHolder.setFill(Color.RED);
                    buttonPane.setHalignment(playHolder,HPos.CENTER);
                    playHolder.setText(" 两次密码不同");
                }
            }
        });


        Scene scene = new Scene(borderPane,310,190);
        primaryStage.setScene(scene);
        primaryStage.setTitle("注册");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}


