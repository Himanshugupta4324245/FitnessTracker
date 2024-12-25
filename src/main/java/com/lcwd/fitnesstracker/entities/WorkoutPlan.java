package com.lcwd.fitnesstracker.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "workout_plans")
public class WorkoutPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Plan name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

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

    public @NotBlank(message = "Plan name is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Plan name is mandatory") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Description is mandatory") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is mandatory") String description) {
        this.description = description;
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
