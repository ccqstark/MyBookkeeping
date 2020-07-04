package bkfunc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ViewBook extends Application {

    public Stage primaryStage = new Stage();
    TableView tableView = new TableView();

    public ViewBook() throws Exception {
        start(this.primaryStage);
    }

    public TableView createTable(Integer pageIndex,int mode) throws SQLException, ClassNotFoundException {
        TableView table = new TableView();
        table.setEditable(true);

        TableColumn dateCol = new TableColumn("日期");
        TableColumn desCol = new TableColumn("描述");
        TableColumn cateCol = new TableColumn("分类");
        TableColumn proCol = new TableColumn("项目");
        TableColumn moneyCol = new TableColumn("金额");
        desCol.setPrefWidth(110);
        //对列添加数据工厂（对应字段）
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        cateCol.setCellValueFactory(new PropertyValueFactory<>("cate"));
        proCol.setCellValueFactory(new PropertyValueFactory<>("pro"));
        moneyCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        table.getColumns().addAll(dateCol,desCol,cateCol,proCol,moneyCol);

        //读取数据
        Controller controller = new Controller();
        if (mode==1){
            controller.getAllBill(pageIndex);
        } else if (mode==2){
            //搜索结果
        }

        //数据加载进table
        table.setItems(Controller.data);

        this.tableView = table;
        return this.tableView;
    }

    public void getPage(Pagination pagination,int mode){
        pagination.setPageFactory((Integer pageIndex) -> {
            try {
                return createTable(pageIndex,mode);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        });
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox(5);
        HBox hBox = new HBox(2);


        TextField searchTextField = new TextField();
        Button searchIt = new Button("🔍");
        Text space = new Text("             ");
        Button opera = new Button("操作");
        Button refresh = new Button("刷新");
        hBox.getChildren().add(searchTextField);
        hBox.getChildren().add(searchIt);
        hBox.getChildren().add(space);
        hBox.getChildren().add(opera);
        hBox.getChildren().add(refresh);
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(hBox);

        //分页
        Pagination pagination = new Pagination(28, 0);
        pagination.setStyle("-fx-border-color:black;");
        getPage(pagination,1);

        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.getChildren().addAll(pagination);
        vBox.getChildren().add(anchor);

        //提示板
        Stage NoticeStage = new Stage();
        BorderPane noticePane = new BorderPane();
        Label notice = new Label(" 请先选择一条记录！");
        notice.setFont(Font.font("微软雅黑", FontWeight.BOLD,21));
        notice.setTextFill(Color.RED);
        noticePane.setCenter(notice);
        BorderPane.setAlignment(notice,Pos.CENTER);
        notice.setAlignment(Pos.CENTER);
        Scene noticeScene = new Scene(noticePane,250,120);
        NoticeStage.setScene(noticeScene);
        NoticeStage.setTitle("错误提示");
        NoticeStage.getIcons().add(new Image("file:./images/tips.png"));

        //操作监听
        opera.setOnMouseClicked(e->{
            //获取当前的选中记录
            Bill now_bill  = (Bill) this.tableView.getSelectionModel().getSelectedItem();
            if (now_bill==null){
                //无选中记录提示
                NoticeStage.show();
            }else {
                try {
                    //修改界面，预填值
                    String pre_des = now_bill.getDescription();
                    String pre_money = Float.toString(now_bill.getMoney());
                    ManageStage manageStage = new ManageStage(pre_des,pre_money,now_bill);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //刷新
        refresh.setOnMouseClicked(e->{
            getPage(pagination,1);
        });

        //搜索
        searchIt.setOnMouseClicked(e->{
            String keyword = searchTextField.getText();
            if (!keyword.equals("")){
                Controller controller = new Controller();
                try {
                    //搜索并显示搜索结果
                    controller.search(keyword);
                    getPage(pagination,2);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(vBox,350,440);
        primaryStage.setScene(scene);
        primaryStage.setTitle("我的账本");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.setResizable(false);
    }

    public void show(){
        this.primaryStage.show();
    }

}

class ManageStage extends Application{

    private Stage primaryStage = new Stage();
    private String pre_des;
    private String pre_money;
    private Bill bill;

    private LocalDate date;
    private String dateStr;
    private String cate;
    private String pro;
    private String description;
    private String moneyStr;
    private float moneyFloat = -1;

    public ManageStage(String pre_des,String pre_money,Bill bill) throws Exception {
        this.pre_des = pre_des;
        this.pre_money = pre_money;
        this.bill = bill;
        this.start(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(8);
        HBox dateHBox = new HBox(1);
        HBox choiceHBox = new HBox(7);
        AnchorPane buttonPane = new AnchorPane();
        borderPane.setCenter(vBox);

        Text noteTitle = new Text("操作此账目");
        noteTitle.setFont(Font.font("微软雅黑", FontWeight.BOLD,27));
        borderPane.setTop(noteTitle);
        noteTitle.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(noteTitle,Pos.CENTER);

        //日期输入框
        dateHBox.setPadding(new Insets(5,5,5,5));
        dateHBox.getChildren().add(new Text("日 期: "));
        //自带日期选择器
        DatePicker datePicker = new DatePicker();
        dateHBox.getChildren().add(datePicker);

        //把日期输入栏加入
        vBox.getChildren().add(dateHBox);
        dateHBox.setAlignment(Pos.CENTER);

        //下拉栏
        choiceHBox.getChildren().add(new Text("收支类别:"));
        ChoiceBox revenueChoiceBox = new ChoiceBox(FXCollections.observableArrayList(
                "支出","收入"
        ));
        choiceHBox.getChildren().add(revenueChoiceBox);


        choiceHBox.getChildren().add(new Text("项目:"));
        ChoiceBox itemChoiceBox = new ChoiceBox(FXCollections.observableArrayList(
                "餐饮","学习","日用","数码","医疗","运动"
        ));
        choiceHBox.getChildren().add(itemChoiceBox);

        //把选择下拉栏加入
        vBox.getChildren().add(choiceHBox);
        choiceHBox.setAlignment(Pos.CENTER);

        //描述与金额输入框
        GridPane gridPane = new GridPane();
        gridPane.add(new Text("描      述: "),0,0);
        TextField descriptionTextField = new TextField(this.pre_des);
        gridPane.add(descriptionTextField,1,0);


        gridPane.add(new Text("金      额: "),0,1);
        TextField moneyTextField = new TextField(this.pre_money);
        gridPane.add(moneyTextField,1,1);

        //把描述和金额的输入框加入
        vBox.getChildren().add(gridPane);
        gridPane.setVgap(8);
        gridPane.setAlignment(Pos.CENTER);

        //删除按钮
        Button deleteButton = new Button("删除账目");
        deleteButton.setFont(new Font(13));
        buttonPane.getChildren().add(deleteButton);
        buttonPane.setTopAnchor(deleteButton,5.0);
        buttonPane.setLeftAnchor(deleteButton,45.0);

        //修改按钮
        Button modifyButton = new Button("确定修改");
        modifyButton.setFont(new Font(13));
        buttonPane.getChildren().add(modifyButton);
        buttonPane.setTopAnchor(modifyButton,5.0);
        buttonPane.setRightAnchor(modifyButton,45.0);

        //把按钮板加上
        buttonPane.setPadding(new Insets(5));
        borderPane.setBottom(buttonPane);
        borderPane.setPadding(new Insets(13));
        BorderPane.setAlignment(buttonPane,Pos.CENTER);

        //提示板
        Stage NoticeStage = new Stage();
        BorderPane noticePane = new BorderPane();
        Label notice = new Label(" 还没填完整！");
        notice.setFont(Font.font("微软雅黑", FontWeight.BOLD,21));
        notice.setTextFill(Color.RED);
        noticePane.setCenter(notice);
        BorderPane.setAlignment(notice,Pos.CENTER);
        notice.setAlignment(Pos.CENTER);
        Scene noticeScene = new Scene(noticePane,250,120);
        NoticeStage.setScene(noticeScene);
        NoticeStage.setTitle("错误提示");
        NoticeStage.getIcons().add(new Image("file:./images/tips.png"));

        //监听
        //修改按钮
        modifyButton.setOnMouseClicked(e->{
           date = datePicker.getValue();
           //转化为字符串
           if (date!=null) {
               dateStr = LocalDateToString(date);
           }
           cate = (String) revenueChoiceBox.getValue();
           pro = (String) itemChoiceBox.getValue();
           description = descriptionTextField.getText();
           moneyStr = moneyTextField.getText();
           //转化为浮点数
           if (!moneyStr.equals("")) {
               moneyFloat = Float.parseFloat(moneyStr);
           }

           if (date==null || cate==null || pro==null || description.equals("") || moneyStr.equals("")){
               //错误提示
               NoticeStage.show();
           } else {
               Controller controller = new Controller();
               try {
                   //输入赋值
                   bill.setDate(dateStr);
                   bill.setCate(cate);
                   bill.setPro(pro);
                   bill.setDescription(description);
                   bill.setMoney(moneyFloat);
                   //执行修改
                   controller.modifyBill(this.bill);
                   //成功提示
                   notice.setText(" 账目已成功修改！");
                   notice.setTextFill(Color.GREEN);
                   NoticeStage.setTitle("成功提示");
                   NoticeStage.show();
               } catch (SQLException ex) {
                   ex.printStackTrace();
               } catch (ClassNotFoundException ex) {
                   ex.printStackTrace();
               }
           }
       });

       //删除按钮
       deleteButton.setOnMouseClicked(e->{
           int bill_id = this.bill.getBill_id();
           Controller controller = new Controller();
           try {
               //删除账目
               controller.deleteBill(bill_id);
               //成功提示
               notice.setText(" 账目已被删除！");
               notice.setTextFill(Color.GREEN);
               NoticeStage.setTitle("成功提示");
               NoticeStage.show();

           } catch (SQLException ex) {
               ex.printStackTrace();
           } catch (ClassNotFoundException ex) {
               ex.printStackTrace();
           }
       });


        Scene scene = new Scene(borderPane,350,230);
        primaryStage.setScene(scene);
        primaryStage.setTitle("修改账目");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/modify.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //LocalDate格式转字符串
    public String LocalDateToString(LocalDate date) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = date.format(fmt);
        return dateStr;
    }

}
