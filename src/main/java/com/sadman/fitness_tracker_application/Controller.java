import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Controller {
    @FXML
    private TextField workoutTypeField, durationField;
    @FXML
    private DatePicker workoutDatePicker;
    @FXML
    private TableView<WorkoutSummary> summaryTableView;
    @FXML
    private TableColumn<WorkoutSummary, String> workoutTypeColumn;
    @FXML
    private TableColumn<WorkoutSummary, Integer> totalDurationColumn;

    public void initialize() {
        workoutTypeColumn.setCellValueFactory(new PropertyValueFactory<>("workoutType"));
        totalDurationColumn.setCellValueFactory(new PropertyValueFactory<>("totalDuration"));
    }

    public void onSubmitButtonClick() {
        String workoutType = workoutTypeField.getText();
        int duration = Integer.parseInt(durationField.getText());
        LocalDate workoutDate = workoutDatePicker.getValue();

        try {
            WorkoutDAO.addWorkout(workoutType, duration, java.sql.Date.valueOf(workoutDate));
            System.out.println("Workout logged successfully!");
            workoutTypeField.clear();
            durationField.clear();
            workoutDatePicker.setValue(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onViewSummaryClick() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(7); // Weekly summary example

        try {
            ResultSet rs = WorkoutDAO.getSummary(startDate, today);
            summaryTableView.getItems().clear(); // Clear previous summary

            while (rs.next()) {
                String type = rs.getString("workout_type");
                int totalDuration = rs.getInt("total_duration");
                summaryTableView.getItems().add(new WorkoutSummary(type, totalDuration));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
