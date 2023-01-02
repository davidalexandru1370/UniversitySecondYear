package UI;

import Constants.Examples;
import Controller.Controller;
import Exceptions.InterpreterException;
import Model.Command.Command;
import Model.Command.RunExample;
import Model.ProgramState;
import Repository.Interfaces.IRepository;
import Repository.Repository;
import Utilities.Programs;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUI extends Application {
    private IRepository repository = new Repository(
            "C:\\Users\\David\\Desktop\\folders\\UniversitySecondYear\\map\\lab3\\untitled\\src\\log1.txt");
    private Controller controller = new Controller(repository);
    private Map<String, Command> commands = new HashMap<>();

    private String programSelectedIndex = null;

    @Override
    public void start(Stage stage) throws Exception {
        setAllCommands();
        final ListView<String> programStatesListView = new ListView<>();

        TilePane mainLayout = new TilePane(Orientation.VERTICAL);
        Label outLabel = new Label("Out table");
        Label heapTableLabel = new Label("Heap table");
        Label symbolTableLabel = new Label("Symbol table");
        Label fileTableLabel = new Label("File table");
        Label programIdsLabel = new Label("Program Ids table");

        VBox outLayout = new VBox();
        ListView<String> out = new ListView<>();
        outLayout.getChildren().add(outLabel);
        outLayout.getChildren().add(out);
        outLayout.setAlignment(Pos.CENTER);

        VBox heapTableLayout = new VBox();
        TableView<String> heapTable = new TableView<>();
        heapTableLayout.getChildren().add(heapTableLabel);
        heapTableLayout.getChildren().add(heapTable);
        heapTableLayout.setAlignment(Pos.CENTER);

        VBox symbolTableLayout = new VBox();
        TableView<String> symbolTable = new TableView<>();
        symbolTableLayout.getChildren().add(symbolTableLabel);
        symbolTableLayout.getChildren().add(symbolTable);
        symbolTableLayout.setAlignment(Pos.CENTER);

        VBox fileTableLayout = new VBox();
        ListView<String> fileTable = new ListView<>();
        fileTableLayout.getChildren().add(fileTableLabel);
        fileTableLayout.getChildren().add(fileTable);
        fileTableLayout.setAlignment(Pos.CENTER);

        VBox programIdsLayout = new VBox();
        ListView<Integer> programIds = new ListView<>();
        programIdsLayout.getChildren().add(programIdsLabel);
        programIdsLayout.getChildren().add(programIds);
        programIdsLayout.setAlignment(Pos.CENTER);

        TilePane tilePaneLayout = new TilePane(Orientation.HORIZONTAL);
        tilePaneLayout.setPadding(new Insets(20, 10, 20, 10));
        TilePane buttonsLayout = new TilePane(Orientation.VERTICAL);
        TilePane loggerLayout = new TilePane(Orientation.HORIZONTAL);
        loggerLayout.setId("loggerLayout");
        buttonsLayout.setHgap(10.0);
        buttonsLayout.setVgap(10.0);
        buttonsLayout.setPadding(new Insets(0, 0, 0, 10));
        Button allStepsButton = new Button("allStepsButton");
        Button oneStepButton = new Button("oneStepButton");
        configureProgramListView(programStatesListView);
        allStepsButton.setOnAction(actionEvent -> {
            fileTable.getItems().clear();
            programIds.getItems().clear();
            symbolTable.getItems().clear();
            heapTable.getItems().clear();
            out.getItems().clear();
            try {
                if (programSelectedIndex == null) {
                    showAlert("No program selected!");
                    return;
                }
                commands.get(programSelectedIndex).execute();
                List<ProgramState> programStateList = this.controller.getProgramStateList();
                for (ProgramState program : programStateList) {
                    programIds.setItems((ObservableList<Integer>) ProgramState.getIds().keySet());
                }
            } catch (InterpreterException interpreterException) {
                showAlert(interpreterException.getMessage());
            }
        });

        allStepsButton.setText("All steps");
        oneStepButton.setText("One step");

        populateProgramListView(programStatesListView);
        configureLoggerListView(out);
        configureLoggerListView(fileTable);
        configureLoggerListView(programIds);
        StackPane root = new StackPane();
        addToGUI(tilePaneLayout, programStatesListView);
        addToGUI(loggerLayout, outLayout);
        addToGUI(loggerLayout, heapTableLayout);
        addToGUI(loggerLayout, symbolTableLayout);
        addToGUI(loggerLayout, fileTableLayout);
        addToGUI(loggerLayout, programIdsLayout);
        addToGUI(buttonsLayout, allStepsButton);
        addToGUI(buttonsLayout, oneStepButton);
        addToGUI(tilePaneLayout, buttonsLayout);

        tilePaneLayout.setPrefRows(4);
        tilePaneLayout.setPrefTileHeight(100);
        loggerLayout.setPrefTileHeight(200);
        loggerLayout.setPrefRows(5);
        loggerLayout.setPrefTileWidth(150);
        mainLayout.setPadding(new Insets(0, 0, 0, 600));
        loggerLayout.setHgap(10);
        addToGUI(mainLayout, tilePaneLayout);
        addToGUI(mainLayout, loggerLayout);
        Scene scene = new Scene(root, 1000, 600);
        addToGUI(root, mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    private void configureLoggerListView(ListView listView) {
        listView.setMaxHeight(300);
        listView.setMaxWidth(200);
    }

    private void setAllCommands() {
        Command example1 = new RunExample(
                "1", "Press 1 to run program 1\n" + Examples.example1(),
                controller,
                Programs.program1());
        Command example2 = new RunExample(
                "2", "Press 2 to run program 2\n" + Examples.example2(),
                controller,
                Programs.program2());
        Command example3 = new RunExample(
                "3", "Press 3 to run program 3\n" + Examples.example3(),
                controller,
                Programs.program3());
        Command example4 = new RunExample(
                "4", "Press 4 to run example 4\n" + Examples.example4(),
                controller,
                Programs.program4());
        Command example5 = new RunExample(
                "5", "Press 5 to run example 5\n" + Examples.example5(),
                controller,
                Programs.program5());
        Command example6 = new RunExample(
                "6", "Press 6 to run example 6\n" + Examples.example6(),
                controller,
                Programs.program6());
        Command example7 = new RunExample(
                "7",
                "Press 7 to run example 7\n" + Examples.example7(),
                controller,
                Programs.program7());
        Command example8 = new RunExample(
                "8",
                "Press 8 to run example 8\n" + Examples.example8(),
                controller,
                Programs.program8());

        Command example9 = new RunExample(
                "9",
                "Press 9 to run example 9\n" + Examples.example9(),
                controller,
                Programs.program9());

        Command example10 = new RunExample(
                "10",
                "Press 10 to run example 10\n" + Examples.example10(),
                controller,
                Programs.program10());

        addCommand(example10);
        addCommand(example9);
        addCommand(example8);
        addCommand(example7);
        addCommand(example6);
        addCommand(example5);
        addCommand(example4);
        addCommand(example3);
        addCommand(example2);
        addCommand(example1);
    }

    private void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    private void addToGUI(Pane root, Control control) {
        root.getChildren().add(control);
    }

    private void addToGUI(Pane root, Pane pane) {
        root.getChildren().add(pane);
    }

    public static void main(String[] args) {
        launch();
    }

    private void configureProgramListView(ListView listView) {
        listView.setMaxHeight(300);
        listView.setPrefWidth(300);
        listView.setId("programStatesListView");
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                if (selectedIndex + 1 > commands.size()) {
                    programSelectedIndex = null;
                } else {
                    programSelectedIndex = String.valueOf(selectedIndex + 1);
                }
            }
        });
    }

    private void populateProgramListView(ListView<String> listView) {
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
