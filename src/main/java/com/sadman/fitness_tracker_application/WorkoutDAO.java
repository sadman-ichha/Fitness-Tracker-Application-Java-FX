package com.sadman.fitness_tracker_application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

public class WorkoutDAO {
    public static void addWorkout(String type, int duration, Date date) throws SQLException {
        String query = "INSERT INTO workout (workout_type, duration, workout_date) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, type);
            stmt.setInt(2, duration);
            stmt.setDate(3, date);
            stmt.executeUpdate();
        }
    }

    public static ResultSet getSummary(LocalDate startDate, LocalDate endDate) throws SQLException {
        String query = "SELECT workout_type, SUM(duration) AS total_duration FROM workout WHERE workout_date BETWEEN ? AND ? GROUP BY workout_type";
        Connection connection = DatabaseUtil.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setDate(1, Date.valueOf(startDate));
        stmt.setDate(2, Date.valueOf(endDate));
        return stmt.executeQuery();
    }
}
