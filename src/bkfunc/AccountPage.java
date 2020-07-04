package bkfunc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AccountPage extends Application{

    public Stage primaryStage = new Stage();

    public AccountPage() throws Exception {
        start(this.primaryStage);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        ImageView profile = new ImageView(new Image("file:./images/default.png"));
        profile.setFitHeight(150);
        profile.setFitWidth(150);
        //头像
        anchorPane.getChildren().add(profile);
        anchorPane.setLeftAnchor(profile,80.0);
        anchorPane.setTopAnchor(profile,50.0);

        //文本
        Controller controller = new Controller();
        String nameOfUser = controller.getUsername();
        Label username = new Label("用户名: "+nameOfUser);
        Label recording = new Label("随手记: ");
        username.setFont(Font.font("微软雅黑", FontWeight.BOLD,20));
        recording.setFont(Font.font("微软雅黑", FontWeight.BOLD,20));
        anchorPane.getChildren().add(username);
        anchorPane.getChildren().add(recording);
        anchorPane.setLeftAnchor(username,250.0);
        anchorPane.setTopAnchor(username,55.0);
        anchorPane.setLeftAnchor(recording,250.0);
        anchorPane.setTopAnchor(recording,90.0);
        //按钮
        Button changeProfile = new Button("更改头像");
        Button openEditor = new Button("编辑");
        anchorPane.getChildren().add(changeProfile);
        anchorPane.getChildren().add(openEditor);
        anchorPane.setLeftAnchor(changeProfile,124.0);
        anchorPane.setTopAnchor(changeProfile,215.0);
        anchorPane.setLeftAnchor(openEditor,325.0);
        anchorPane.setTopAnchor(openEditor,92.0);

        //开发者
        Text developer = new Text("Developer: Ccq");
        anchorPane.getChildren().add(developer);
        developer.setFont(Font.font("微软雅黑", FontWeight.BOLD,15));
        anchorPane.setTopAnchor(developer,290.0);
        anchorPane.setRightAnchor(developer,10.0);

        //按钮绑定
        openEditor.setOnMouseClicked(e->{
            Editor editor = new Editor();
        });

        //修改头像
        changeProfile.setOnMouseClicked(e->{
            //文件选择器
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择图片作为头像");
            //过滤为图片格式
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            //获得新头像路径后重设头像
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file!=null) {
                String imagePath = file.getAbsolutePath();
                imagePath = "file:" + imagePath;
                profile.setImage(new Image(imagePath));
            }
        });


        Scene scene = new Scene(anchorPane,520,310);
        primaryStage.setScene(scene);
        primaryStage.setTitle("账户信息");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.setResizable(false);
    }

    public void show(){
        this.primaryStage.show();
    }

}
