package UI;

import Constants.Examples;
import Controller.Controller;
import Exceptions.FinishedRunningException;
import Exceptions.InterpreterException;
import Model.ADT.Interfaces.IDictionary;
import Model.ADT.MyDictionary;
import Model.Command.Command;
import Model.Command.RunExample;
import Model.ProgramState;
import Model.Value.Interfaces.IValue;
import Repository.Interfaces.IRepository;
import Repository.Repository;
import Utilities.Programs;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GUI extends Application {
    private IRepository repository = new Repository(
            "log1.txt");
    private Controller controller = new Controller(repository);
    private Map<String, Command> commands = new HashMap<>();
    private TableView<Pair<String, String>> heapTable = new TableView<>();
    private TableView<String> symbolTable = new TableView<>();
    private ListView<String> fileTable = new ListView<>();
    private ListView<Integer> programIds = new ListView<>();
    private ListView<String> executionStack = new ListView<>();
    private ListView<String> out = new ListView<>();
    private MyDictionary<Integer, IDictionary<String, IValue>> symbolTableLocalStorage = new MyDictionary<>();
    private MyDictionary<Integer, List<String>> executionStackLocalStorage = new MyDictionary<>();
    private String programSelectedIndex = null;
    private boolean clearOutputAfterThis = false;

    @Override
    public void start(Stage stage) throws Exception {
        setAllCommands();
        final ListView<String> programStatesListView = new ListView<>();
        TableColumn<Pair<String, String>, String> heapTableKey = new TableColumn<>("Address");
        TableColumn<Pair<String, String>, String> heapTableValue = new TableColumn<>("Value");
        heapTableKey.setCellValueFactory(new PropertyValueFactory<Pair<String, String>, String>("Key"));
        heapTableValue.setCellValueFactory(new PropertyValueFactory<Pair<String, String>, String>("Value"));
        heapTable.getColumns().addAll(heapTableKey, heapTableValue);

        TableColumn<String, String> symbolTableKey = new TableColumn<>("Key");
        TableColumn<String, String> symbolTableValue = new TableColumn<>("Value");
        symbolTableKey.setCellValueFactory(new PropertyValueFactory<String, String>("Key"));
        symbolTableValue.setCellValueFactory(new PropertyValueFactory<String, String>("Value"));
        symbolTable.getColumns().addAll(symbolTableKey, symbolTableValue);

        TilePane mainLayout = new TilePane(Orientation.VERTICAL);
        Label outLabel = new Label("Out table");
        Label heapTableLabel = new Label("Heap table");
        Label symbolTableLabel = new Label("Symbol table");
        Label fileTableLabel = new Label("File table");
        Label programIdsLabel = new Label("Program Ids table");
        Label executionStackLabel = new Label("Execution stack");

        VBox outLayout = new VBox();
        outLayout.getChildren().add(outLabel);
        outLayout.getChildren().add(out);
        outLayout.setAlignment(Pos.CENTER);

        VBox heapTableLayout = new VBox();
        heapTableLayout.getChildren().add(heapTableLabel);
        heapTableLayout.getChildren().add(heapTable);
        heapTableLayout.setPrefWidth(700);
        heapTable.setPrefWidth(700);
        heapTableLayout.setAlignment(Pos.CENTER);

        VBox symbolTableLayout = new VBox();

        symbolTableLayout.getChildren().add(symbolTableLabel);
        symbolTableLayout.getChildren().add(symbolTable);
        symbolTableLayout.setAlignment(Pos.CENTER);

        VBox fileTableLayout = new VBox();

        fileTableLayout.getChildren().add(fileTableLabel);
        fileTableLayout.getChildren().add(fileTable);
        fileTableLayout.setAlignment(Pos.CENTER);

        VBox programIdsLayout = new VBox();

        programIdsLayout.getChildren().add(programIdsLabel);
        programIdsLayout.getChildren().add(programIds);
        programIdsLayout.setAlignment(Pos.CENTER);

        VBox executionStackLayout = new VBox();
        executionStackLayout.getChildren().add(executionStackLabel);
        executionStackLayout.getChildren().add(executionStack);
        executionStackLayout.setAlignment(Pos.CENTER);

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
            controller.setOneStepRunning(false);
            resetListViews();
            try {
                if (programSelectedIndex == null) {
                    showAlert("No program selected!", AlertType.ERROR);
                    return;
                }
                commands.get(programSelectedIndex).execute();
                clearOutputAfterThis = true;
            } catch (FinishedRunningException finishedRunningException) {
                clearOutputAfterThis = true;
            } catch (InterpreterException interpreterException) {
                showAlert(interpreterException.getMessage(), AlertType.ERROR);
            }
            catch(RuntimeException re){
                System.out.println(re.getMessage());
            }
            catch (Exception exception){
                showAlert(exception.getMessage(),AlertType.ERROR);
            }

        });

        oneStepButton.setOnAction(actionEvent -> {
            controller.setOneStepRunning(true);
            if (clearOutputAfterThis) {
                resetListViews();
                clearOutputAfterThis = false;
            }
            try {
                if (programSelectedIndex == null) {
                    showAlert("No program selected!", AlertType.ERROR);
                    return;
                }
                commands.get(programSelectedIndex).execute();
            } catch (FinishedRunningException finishedRunningException) {
                showAlert(finishedRunningException.getMessage(), AlertType.WARNING);
                clearOutputAfterThis = true;
            } catch (InterpreterException interpreterException) {
                showAlert(interpreterException.getMessage(), AlertType.ERROR);
            }

        });

        Consumer<ProgramState> displayResultsConsumer = this::displayProgramStateResults;
        controller.addObservant(displayResultsConsumer);
        allStepsButton.setText("All steps");
        oneStepButton.setText("One step");
        configureProgramIdListView();
        populateProgramListView(programStatesListView);
        configureLoggerListView(out);
        configureLoggerListView(fileTable);
        configureLoggerListView(executionStack);
        configureLoggerListView(programIds);
        StackPane root = new StackPane();
        addToGUI(tilePaneLayout, programStatesListView);
        addToGUI(loggerLayout, executionStackLayout);
        addToGUI(loggerLayout, outLayout);
        addToGUI(loggerLayout, heapTableLayout);
        addToGUI(loggerLayout, symbolTableLayout);
        addToGUI(loggerLayout, fileTableLayout);
        addToGUI(loggerLayout, programIdsLayout);
        addToGUI(buttonsLayout, allStepsButton);
        addToGUI(buttonsLayout, oneStepButton);
        addToGUI(tilePaneLayout, buttonsLayout);

        tilePaneLayout.setPrefRows(4);
        tilePaneLayout.setPrefTileHeight(200);

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

    private void resetListViews() {
        fileTable.getItems().clear();
        programIds.getItems().clear();
        symbolTable.getItems().clear();
        heapTable.getItems().clear();
        executionStack.getItems().clear();
        out.getItems().clear();
        symbolTableLocalStorage = new MyDictionary<>();
        executionStackLocalStorage = new MyDictionary<>();

    }

    private void configureProgramIdListView() {
        programIds.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                Integer selectedProgramId = programIds.getSelectionModel().getSelectedItem();
                if (selectedProgramId == null) {
                    return;
                }

                executionStack
                        .setItems(FXCollections.observableArrayList(executionStackLocalStorage.get(selectedProgramId)));
                symbolTable.setItems(FXCollections.observableArrayList(symbolTableLocalStorage
                        .get(selectedProgramId)
                        .getKeys()
                        .stream()
                        .map(key -> new Pair(key, symbolTableLocalStorage.get(selectedProgramId).get((String) key)))
                        .toList()));
            }
        });
    }

    private void displayProgramStateResults(ProgramState programState) {
        // programIds.getItems().clear();
        ObservableList<Integer> programIdsContent = FXCollections.observableArrayList(programState.getId());
        programIdsContent.addAll(programIds.getItems());
        programIds.setItems(FXCollections.observableArrayList(
                programIdsContent.stream().collect(Collectors.toSet())));

        out.getItems().clear();
        ObservableList<String> outList = FXCollections.observableArrayList();

        programState.getOut().forEach(e -> outList.add(e.toString()));
        out.setItems(outList);

        fileTable.getItems().clear();
        fileTable.setItems(FXCollections.observableArrayList(programState.getOutFiles().getKeys()));

        heapTable.getItems().clear();

        ObservableList<Pair<String, String>> heapContent = FXCollections.observableArrayList();

        programState.getHeap().getContent().keySet().stream()
                .forEach(p -> heapContent
                        .add(new Pair<String, String>(p.toString(), programState.getHeap().get(p).toString())));

        heapTable.setItems(heapContent);

        symbolTableLocalStorage.insert(programState.getId(), programState.getSymbolTable());
        if (!executionStackLocalStorage.isDefined(programState.getId())) {
            executionStackLocalStorage.insert(programState.getId(), new ArrayList<String>());
        }
        try {
            executionStackLocalStorage.get(programState.getId())
                    .add(programState.getExeStack().getTop().toString());
        } catch (Exception ex) {

        }

    }

    private void showAlert(String message, AlertType type) {
        Alert alert = new Alert(type, message);
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

        Command example11 = new RunExample(
                "11",
                "Press 11 to run example 11\n" + Examples.example11(),
                controller,
                Programs.program11());

        Command example12 = new RunExample(
                "12",
                "Press 12 to run example 12\n" + Examples.example12(),
                controller,
                Programs.program12());

        addCommand(example12);
        addCommand(example11);
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
        listView.setPrefHeight(300);
        listView.setPrefWidth(300);
        listView.setId("programStatesListView");
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                if (selectedIndex + 1 > commands.size()) {
                    programSelectedIndex = null;
                } else {
                    repository.clear();
                    resetListViews();
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
        data.add(Examples.example11());
        data.add(Examples.example12());

        listView.setItems(data);
    }

}
