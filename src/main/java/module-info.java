module com.sadman.fitness_tracker_application {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sadman.fitness_tracker_application to javafx.fxml;
    exports com.sadman.fitness_tracker_application;
}