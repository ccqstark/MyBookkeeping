package bkfunc;

import DatabaseConnect.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    public static ObservableList<Bill> data = FXCollections.observableArrayList();

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


    //获取此用户全部账单信息
    public void getAllBill(int pageIndex) throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        int user_id = userbase.Controller.user_id;
        int start = (pageIndex)*12;

        String sql = "SELECT * FROM bill_table WHERE owner = "+user_id+" LIMIT "+start+", 12";

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Controller.data.clear();
        //构造data数据集
        while (resultSet.next()){
            int bill_id = resultSet.getInt("bill_id");
            String date_t = resultSet.getString("date");
            String cate_t = resultSet.getString("category");
            String pro_t  = resultSet.getString("project");
            String description_t = resultSet.getString("description");
            float money_t = resultSet.getFloat("money");

            data.add(new Bill(bill_id,date_t,cate_t,pro_t,description_t,money_t));
        }

        resultSet.close();
        statement.close();
        conn.close();
    }


    //修改账目
    public void modifyBill(Bill bill) throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        int bill_id = bill.getBill_id();
        String date = addQuotation(bill.getDate());
        String cate = addQuotation(bill.getCate());
        String pro = addQuotation(bill.getPro());
        String description = addQuotation(bill.getDescription());
        float money = bill.getMoney();

        String sql = "UPDATE bill_table SET date = "
                +date+", category = "+cate+", project = "+pro +", description = "+description+", money = "+money+
                "WHERE bill_id = "+ bill_id;

        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
    }

    public void deleteBill(int bill_id) throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        String sql = "DELETE FROM bill_table WHERE bill_id = "+bill_id;

        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
    }

    //搜索
    public void search(String keyword) throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        keyword = '%' + keyword + '%';
        keyword = addQuotation(keyword);
        String sql = "SELECT * FROM bill_table WHERE date LIKE " + keyword +
                " OR category LIKE " + keyword+
                " OR project LIKE " + keyword+
                " OR description LIKE " + keyword+
                " OR money LIKE " + keyword;


        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        Controller.data.clear();
        //构造data数据集
        while (resultSet.next()){
            int bill_id = resultSet.getInt("bill_id");
            String date_t = resultSet.getString("date");
            String cate_t = resultSet.getString("category");
            String pro_t  = resultSet.getString("project");
            String description_t = resultSet.getString("description");
            float money_t = resultSet.getFloat("money");

            data.add(new Bill(bill_id,date_t,cate_t,pro_t,description_t,money_t));
        }

        resultSet.close();
        statement.close();
        conn.close();
    }

    public String getUsername() throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        int user_id = userbase.Controller.user_id;
        String sql = "SELECT * FROM user_info WHERE user_id = "+user_id;

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        String username = null;
        while (resultSet.next()){
            username = resultSet.getString("username");
        }

        resultSet.close();
        statement.close();
        conn.close();

        return username;
    }


    public float[] getPercent() throws SQLException, ClassNotFoundException {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection conn = connectionClass.getConnection();

        int user_id = userbase.Controller.user_id;
        String sql = "SELECT * FROM bill_table WHERE owner = "+user_id;

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        float[] value = {0,0,0,0,0,0};
        while (resultSet.next()){
           String pro = resultSet.getString("project");
           float money = resultSet.getFloat("money");
           if (pro.equals("餐饮")) {
                value[0]+=money;
           } else if (pro.equals("学习")){
               value[1]+=money;
           } else if (pro.equals("日用")){
               value[2]+=money;
           } else if (pro.equals("数码")){
               value[3]+=money;
           } else if (pro.equals("医疗")){
               value[4]+=money;
           } else if (pro.equals("运动")){
               value[5]+=money;
           }
        }

        return value;
    }
}