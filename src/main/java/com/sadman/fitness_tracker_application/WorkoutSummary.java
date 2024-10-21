public class WorkoutSummary {
    private String workoutType;
    private int totalDuration;

    public WorkoutSummary(String workoutType, int totalDuration) {
        this.workoutType = workoutType;
        this.totalDuration = totalDuration;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public int getTotalDuration() {
        return totalDuration;
    }
}
