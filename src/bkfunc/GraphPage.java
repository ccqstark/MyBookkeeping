package bkfunc;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.text.DecimalFormat;

public class GraphPage extends Application{

    public Stage primaryStage = new Stage();

    public GraphPage() throws Exception {
        start(this.primaryStage);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Group(),500,440);

        //从数据库获取各项数据
        Controller controller = new Controller();
        float[] value = controller.getPercent();
        //添加数据项和数据值
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("餐饮",value[0]),
                        new PieChart.Data("学习", value[1]),
                        new PieChart.Data("日用", value[2]),
                        new PieChart.Data("数码", value[3]),
                        new PieChart.Data("医疗", value[4]),
                        new PieChart.Data("运动", value[5]));

        //标题与百分比格式
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("各项目花费饼状图");
        final Label caption = new Label("");
        caption.setTextFill(Color.GREY);
        caption.setStyle("-fx-font: 24 bold;");

        //计算总额
        float sum = 0;
        for (float data:value){
            sum+=data;
        }


        //监听被点击的部分，显示百分比
        for (final PieChart.Data data : chart.getData()) {
            float finalSum = sum;
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    new EventHandler<MouseEvent>() {
                        @Override public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            //保留2为小数
                            DecimalFormat df = new DecimalFormat("#.00");
                            String percent = String.valueOf(df.format(data.getPieValue()/ finalSum*100));
                            caption.setText(percent + "%");
                        }
                    });
        }

        //添加到scene上
        ((Group) scene.getRoot()).getChildren().addAll(chart, caption);

        primaryStage.setScene(scene);
        primaryStage.setTitle("统计图表");
        //图标
        primaryStage.getIcons().add(new Image("file:./images/icon.png"));
        primaryStage.setResizable(false);
    }

    public void show(){
        this.primaryStage.show();
    }

}
