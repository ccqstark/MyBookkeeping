package bkfunc;

import DatabaseConnect.*;
import userbase.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    //添加新的账单
    public void takeNewNote(String date, String cate, String pro, String description,float money) throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        int user_id = userbase.Controller.user_id;
        date = addQuotation(date);
        cate = addQuotation(cate);
        pro = addQuotation(pro);
        description = addQuotation(description);

        String sql = "INSERT INTO bill_table (owner,date,category,project,description,money) " +
                "VALUES ("+ user_id +','+ date+',' + cate+',' + pro +',' + description + ',' + money +')';

        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);

        statement.close();
        conn.close();
    }


    //给字符串加上单引号，用于sql
    public String addQuotation(String str){
        String addedStr = "'" + str +"'";
        return addedStr;
    }

}