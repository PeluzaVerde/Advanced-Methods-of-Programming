module com.example.manpusc {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jdi;
    requires java.sql;


    opens com.example.manpusc to javafx.fxml;
    exports com.example.manpusc;
    //opens View.Gui to javafx.fxml;
    //exports View.Gui;

}