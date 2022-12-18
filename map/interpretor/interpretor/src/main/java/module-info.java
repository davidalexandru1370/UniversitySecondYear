module com.example.interpretor {
    requires javafx.controls;
    requires javafx.fxml;


    opens UI to javafx.fxml;
    exports UI;
}