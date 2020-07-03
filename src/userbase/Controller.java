package userbase;

import DatabaseConnect.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    public static int user_id;
    private TextField textField1; //用户名输入框
    private TextField textField2; //密码输入框
    private Label label;

    Controller(TextField textField1,TextField textField2){
        this.textField1 = textField1;
        this.textField2 = textField2;
    }

    //注册
    public void register() throws SQLException, ClassNotFoundException {

        //获取连接
        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        //获取文本框内容
        String username = "'" + this.textField1.getText() +"'";
        String password = "'" + this.textField2.getText() +"'";

        //构造SQL语句
        String sql = "INSERT INTO user_info (username, password) " +
                "VALUES ("+username+','+password+')';

        System.out.println(sql);

        System.out.println("实例化Statement对象...");
        Statement statement = conn.createStatement();

        statement.executeUpdate(sql); //执行SQL语句

        //关闭资源
        statement.close();
        conn.close();
    }


    public boolean login() throws SQLException, ClassNotFoundException {

        //建立链接
        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        //获取输入
        String usernameInput = "'" + this.textField1.getText() +"'";
        String passwordInput = this.textField2.getText();

        //构建sql
        String sql = "SELECT username,password FROM user_info WHERE username = "+usernameInput;

        //实例化statement来执行sql
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        //获得结果
        if (resultSet.next()) {
            String correctPsw = resultSet.getString("password");
            user_id = resultSet.getInt("user_id"); //用户id 静态变量
            //判断密码
            if (passwordInput.equals(correctPsw)) {
                return true;
            }else {
                return false;
            }
        } else {//查无此用户
            return false;
        }
    }
}

