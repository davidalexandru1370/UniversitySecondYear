package UI;

import Constants.Examples;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final ListView<String> programStatesListView = new ListView<>();
        configureProgramListView(programStatesListView);
        populateProgramListView(programStatesListView);
        StackPane root = new StackPane();
        root.getChildren().add(programStatesListView);
        Scene scene = new Scene(root,640,480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }

    private void configureProgramListView(ListView listView){
        listView.setMaxHeight(300);
        listView.setMaxWidth(300);
    }

    private void populateProgramListView(ListView<String> listView){
        ObservableList<String> data = FXCollections.observableArrayList();

        data.add(Examples.example1());
        data.add(Examples.example2());
        data.add(Examples.example3());
        data.add(Examples.example4());
        data.add(Examples.example5());
        data.add(Examples.example6());
        data.add(Examples.example7());
        data.add(Examples.example8());
        data.add(Examples.example9());
        data.add(Examples.example10());

        listView.setItems(data);
    }

}
