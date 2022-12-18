package UI;

import Constants.Examples;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        String css = this.getClass().getResource("application.css").toExternalForm();
        final ListView<String> programStatesListView = new ListView<>();
        TilePane tilePaneLayout = new TilePane(Orientation.HORIZONTAL);
        tilePaneLayout.setPadding(new Insets(20,10,20,10));
        TilePane buttonsLayout = new TilePane(Orientation.VERTICAL);
        buttonsLayout.setHgap(10.0);
        buttonsLayout.setVgap(10.0);
        buttonsLayout.setPadding(new Insets(0,0,0,10));
        Button allStepsButton = new Button("allStepsButton");
        Button oneStepButton = new Button("oneStepButton");
        allStepsButton.setText("All steps");
        oneStepButton.setText("One step");

        configureProgramListView(programStatesListView);
        populateProgramListView(programStatesListView);
        StackPane root = new StackPane();
        addToGUI(tilePaneLayout,programStatesListView);
        addToGUI(buttonsLayout,allStepsButton);
        addToGUI(buttonsLayout,oneStepButton);
        addToGUI(tilePaneLayout,buttonsLayout);


        addToGUI(root,tilePaneLayout);

        Scene scene = new Scene(root,640,480);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    private void addToGUI(Pane root, Control control){
        root.getChildren().add(control);
    }

    private void addToGUI(Pane root, Pane pane){
        root.getChildren().add(pane);
    }

    public static void main(String[] args){
        launch();
    }

    private void configureProgramListView(ListView listView){
        listView.setMaxHeight(300);
        listView.setPrefWidth(300);
        listView.setId("programStatesListView");
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
