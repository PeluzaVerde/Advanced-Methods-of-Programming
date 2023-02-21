module com.example.manpusc2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;


    opens com.example.manpusc2 to javafx.fxml;
    exports com.example.manpusc2;
    //opens View.Gui to javafx.fxml;
    //exports View.Gui;

}