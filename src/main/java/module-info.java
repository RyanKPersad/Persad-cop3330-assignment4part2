module ucf.todolistapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens ucf.todolistapp to javafx.fxml;
    exports ucf.todolistapp;
}