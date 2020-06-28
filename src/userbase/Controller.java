package userbase;

import DatabaseConnect.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    private TextField textField1; //用户名输入框
    private TextField textField2; //密码输入框
    private Label label;

    Controller(TextField textField1,TextField textField2){
        this.textField1 = textField1;
        this.textField2 = textField2;
    }

    public void LoginButton() throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();  //获取连接

        String username = "'" + this.textField1.getText() +"'"; //获取文本框内容
        String password = "'" + this.textField2.getText() +"'";

        String sql = "INSERT INTO user_info (username, password) " +
                "VALUES ("+username+','+password+')';

        System.out.println(sql);

        System.out.println("实例化Statement对象...");
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql); //执行SQL语句
    }

}

