package com.example.interpretor.UI;

import com.example.interpretor.HelloApplication;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GUI  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final ListView programStatesListView = new ListView();
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

    private void populateProgramListView(ListView listView){

    }

}
