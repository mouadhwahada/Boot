package tn.spring.pispring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer dayNumber;
    private LocalDate date;
    private Boolean completed;
    private Integer totalDuration;

    @ManyToOne
    @JoinColumn(name="workout_id")
    private Workoutprogram workoutProgram;
    @JsonIgnore
    @OneToMany(mappedBy = "exerciseDay",cascade = CascadeType.ALL)

    private List<Exercise> excercises;
}
