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

public class ViewBook extends Application {

    public TableView createPage(Integer pageIndex){
        TableView table = new TableView();
        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn emailCol = new TableColumn("Email");
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        return table;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox(5);
        HBox hBox = new HBox(2);


        TextField searchTextField = new TextField();
        Button searchIt = new Button("🔍");
        Button opera = new Button("操作");
        ChoiceBox orderChoiceBox = new ChoiceBox(FXCollections.observableArrayList(
                "按日期倒序","按日期顺序"
        ));
        hBox.getChildren().add(searchTextField);
        hBox.getChildren().add(searchIt);
        hBox.getChildren().add(opera);
        hBox.getChildren().add(orderChoiceBox);
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(hBox);

        //分页
        Pagination pagination = new Pagination(28, 0);
        pagination.setStyle("-fx-border-color:black;");
        pagination.setPageFactory((Integer pageIndex) -> createPage(pageIndex));

        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(pagination, 10.0);
        AnchorPane.setRightAnchor(pagination, 10.0);
        AnchorPane.setBottomAnchor(pagination, 10.0);
        AnchorPane.setLeftAnchor(pagination, 10.0);
        anchor.getChildren().addAll(pagination);

        vBox.getChildren().add(anchor);






        Scene scene = new Scene(vBox,350,440);
        primaryStage.setScene(scene);
        primaryStage.setTitle("我的账本");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.show();
    }


}
