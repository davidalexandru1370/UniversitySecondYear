module com.example.interpretor {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.interpretor to javafx.fxml;
    exports com.example.interpretor;
}