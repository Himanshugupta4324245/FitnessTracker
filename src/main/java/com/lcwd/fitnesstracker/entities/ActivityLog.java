package com.lcwd.fitnesstracker.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "activity_logs")
public class ActivityLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Activity name is mandatory")
    private String activityName;

    @NotNull(message = "Date is mandatory")
    private LocalDate date;

    @NotNull(message = "Duration is mandatory")
    private Integer durationInMinutes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /*
    My compiler now Support  Lombok Don't know why I tried to install plugins also still the issue persistes
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Activity name is mandatory") String getActivityName() {
        return activityName;
    }

    public void setActivityName(@NotBlank(message = "Activity name is mandatory") String activityName) {
        this.activityName = activityName;
    }

    public @NotNull(message = "Date is mandatory") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Date is mandatory") LocalDate date) {
        this.date = date;
    }

    public @NotNull(message = "Duration is mandatory") Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(@NotNull(message = "Duration is mandatory") Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
