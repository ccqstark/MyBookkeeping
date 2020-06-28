package bkfunc;

import com.mysql.cj.x.protobuf.MysqlxExpect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TakeNote extends Application{
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(8);
        HBox dateHBox = new HBox(1);
        HBox choiceHBox = new HBox(7);
        GridPane buttonPane = new GridPane();
        borderPane.setCenter(vBox);

        Text noteTitle = new Text("新    建");
        noteTitle.setFont(Font.font("微软雅黑", FontWeight.BOLD,27));
        borderPane.setTop(noteTitle);
        noteTitle.setTextAlignment(TextAlignment.CENTER);
        BorderPane.setAlignment(noteTitle,Pos.CENTER);

        //日期输入框
        dateHBox.setPadding(new Insets(5,5,5,5));
        dateHBox.getChildren().add(new Text("日 期: "));
//        //年
//        TextField yearTextField = new TextField();
//        yearTextField.setPrefColumnCount(3);
//        dateHBox.getChildren().add(yearTextField);
//        dateHBox.getChildren().add(new Text("年 "));
//        //月
//        TextField monthTextField = new TextField();
//        monthTextField.setPrefColumnCount(2);
//        dateHBox.getChildren().add(monthTextField);
//        dateHBox.getChildren().add(new Text("月 "));
//        //日
//        TextField dayTextField = new TextField();
//        dayTextField.setPrefColumnCount(2);
//        dateHBox.getChildren().add(dayTextField);
//        dateHBox.getChildren().add(new Text("日"));

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
        TextField descriptionTextField = new TextField();
        gridPane.add(descriptionTextField,1,0);

        gridPane.add(new Text("金      额: "),0,1);
        TextField moneyTextField = new TextField();
        gridPane.add(moneyTextField,1,1);

        //把描述和金额的输入框加入
        vBox.getChildren().add(gridPane);
        gridPane.setVgap(8);
        gridPane.setAlignment(Pos.CENTER);


        //为了居中的占位
        Text playHolder = new Text("       ");
        playHolder.setFont(new Font(22));
        buttonPane.add(playHolder,0,0);
        GridPane.setHalignment(playHolder,HPos.CENTER);
        GridPane.setValignment(playHolder,VPos.CENTER);

        //完成按钮
        Button done = new Button("完成");
        done.setFont(new Font(13));
        buttonPane.add(done,1,0);
        GridPane.setHalignment(done,HPos.CENTER);
        GridPane.setValignment(done,VPos.CENTER);

        //返回按钮
        Button back = new Button("返回");
        back.setFont(new Font(13));
        buttonPane.add(back,2,0);
        GridPane.setHalignment(back,HPos.CENTER);
        GridPane.setValignment(back,VPos.CENTER);

        //把按钮板加上
        buttonPane.setPadding(new Insets(5));
        buttonPane.setHgap(91);
        borderPane.setBottom(buttonPane);
        borderPane.setPadding(new Insets(13));
        BorderPane.setAlignment(buttonPane,Pos.CENTER);


        Scene scene = new Scene(borderPane,350,230);
        primaryStage.setScene(scene);
        primaryStage.setTitle("立即记账");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.show();

    }


}
