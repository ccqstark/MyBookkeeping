package main;

import bkfunc.AccountPage;
import bkfunc.GraphPage;
import bkfunc.TakeNote;
import bkfunc.ViewBook;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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

        //提示板
        Stage NoticeStage = new Stage();
        BorderPane noticePane = new BorderPane();
        Scene noticeScene = new Scene(noticePane,222,90);
        NoticeStage.setScene(noticeScene);
        NoticeStage.setTitle("加载中...");
        NoticeStage.getIcons().add(new Image("file:./images/loading.png"));

        //子窗口实例化与按钮绑定
        TakeNote takeNote = new TakeNote();
        ViewBook viewBook = new ViewBook();
        AccountPage accountPage = new AccountPage();
        GraphPage graphPage = new GraphPage();

        //立刻记账
        takeNoteButton.setOnMouseClicked(e->{
            primaryStage.hide();
            takeNote.show();
        });
        takeNote.back.setOnMouseClicked(e->{
            takeNote.primaryStage.hide();
            primaryStage.show();
        });
        takeNote.primaryStage.setOnCloseRequest(e->{
            primaryStage.show();
        });

        //我的账本
        manageBookButton.setOnMouseClicked(e->{
            primaryStage.hide();
            NoticeStage.show();
            viewBook.show();
            NoticeStage.hide();
        });
        viewBook.primaryStage.setOnCloseRequest(e->{
            primaryStage.show();
        });

        //账户信息
        userButton.setOnMouseClicked(e->{
            primaryStage.hide();
            accountPage.show();
        });
        accountPage.primaryStage.setOnCloseRequest(e->{
            primaryStage.show();
        });

        //统计图表
        graphButton.setOnMouseClicked(e->{
            primaryStage.hide();
            graphPage.show();
        });
        graphPage.primaryStage.setOnCloseRequest(e->{
            primaryStage.show();
        });

        Scene scene = new Scene(mainPane,405,365);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Bookkeeping");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

